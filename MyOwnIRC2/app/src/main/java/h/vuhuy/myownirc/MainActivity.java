package h.vuhuy.myownirc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Created by Huy and ConcernGuy
   huybv1998@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    private ListView logs;
    private List<String> items = new ArrayList<>();


    public SharedPreferences preferences;
    public boolean ranBefore;

    private Connection connection;
    private String channel;
    private SenderFragment sendMessage;



    public void onCreate(Bundle savedInstance) {

        preferences = getSharedPreferences("first_time", Context.MODE_PRIVATE);
        ranBefore = preferences.getBoolean("RanBefore", false);

        if (ranBefore == false) {
            Intent i = new Intent(this, ConnectActivity.class);
            startActivity(i);
            finish();
        }

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        // Allow socket operations in UI thread
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        logs = findViewById(R.id.logs);
        adapter = new LogsAdapter<>(this, items);
        logs.setAdapter(adapter);

        Spinner channel = findViewById(R.id.channel);
        List<String> channels = new ArrayList<>();
        this.channel = "No channel";
        channels.add(this.channel);
        adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item,
                channels);
        adapter2.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        channel.setAdapter(adapter2);
        channel.setOnItemSelectedListener(new AdapterView
                .OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {
                MainActivity.this.channel = adapter2.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        Intent intent = getIntent();
        String host = intent.getStringExtra("host");
        String port = intent.getStringExtra("port");
        String nick = intent.getStringExtra("nick");
        String password = intent.getStringExtra("password");
        String auth_nickserv = intent.getStringExtra("auth_nickserv");
        String autojoin = intent.getStringExtra("autojoin");
        String script = intent.getStringExtra("script");

        EditText message = findViewById(R.id.message);
        message.setHint(nick);

        new ConnectTask().execute(host, port, nick, password,
                auth_nickserv, autojoin, script);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                this.startActivity(intent);
                break;
            case R.id.clear:
                adapter.clear();
                adapter.notifyDataSetChanged();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null)
            connection.stop();
    }

    public void send(View v) {
        EditText message = findViewById(R.id.message);
        parseIn(message.getText().toString());
        message.setText("");
    }


    private void parseIn(String message) {
        String tmp[];

        message = message.trim();
        if (message.isEmpty())
            return;
        message = message.replaceAll("\\s+", " ");
        if (message.charAt(0) != '/') {
            privmsg("", message);
            return;
        }
        if (message.startsWith("/join")) {
            tmp = message.split(" ");
            if (tmp.length >= 2)
                join(tmp[1]);
        } else if (message.startsWith("/part")) {
            tmp = message.split(" ");
            if (tmp.length >= 3)
                part(tmp[1], tmp[2]);
            else if (tmp.length == 2)
                part(tmp[1], "");
            else
                part("", "");
        } else if (message.startsWith("/msg")) {
            tmp = message.split(" ", 3);
            if (tmp.length >= 3)
                privmsg(tmp[1], tmp[2]);
            return;
        } else {
            connection.send(message.substring(1));
        }
        log(message);
    }

    private void join(String channel) {
        if (!channel.isEmpty())
            connection.send("JOIN " + channel);
    }

    private void part(String channel, String message) {
        if (channel.isEmpty() && this.channel.equals("No channel"))
            return;
        if (channel.isEmpty())
            channel = this.channel;
        if (message.isEmpty())
            message = "Leaving to study for final exam.";
        connection.send("PART " + channel + " :" + message);
    }

    private void privmsg(String channel, String message) {
        if (channel.isEmpty() && this.channel.equals("No channel"))
            return;
        if (message.isEmpty())
            return;
        if (channel.isEmpty())
            channel = this.channel;
        connection.send("PRIVMSG " + channel + " :" + message);
        log(String.format("<font color=\"#FF4500\">%s</font>: " +
                "<font color=\"#FF4500\">&lt;</font>" +
                "%s<font color=\"#FF4500\">&gt;</font> " +
                "%s", channel, connection.nick, message));
    }

    private void parseSrv(String message) {
        String user = connection.host;
        String command = message;
        String param;
        String text;
        String tmp[];

        if (command.isEmpty())
            return;

        if (command.charAt(0) == ':') {
            tmp = command.substring(1).split(" ", 2);
            if (tmp.length < 2)
                return;
            user = tmp[0];
            command = tmp[1];
            user = user.split("!", 2)[0];
        }

        tmp = command.split(" ", 2);
        command = tmp[0];
        param = tmp.length == 2 ? tmp[1]: "";

        tmp = param.split(":", 2);
        param = tmp.length >= 1 ? tmp[0].trim() : "";
        text = tmp.length == 2 ? tmp[1] : "";

        text = Html.escapeHtml(text);
        if (command.equals("PONG") || command.equals("MODE"))
            return;
        if (command.equals("353") || command.equals("366"))
            return;
        if (command.equals("PING"))
            connection.send(message.replaceFirst("PING", "PONG"));
        else if (command.equals("PRIVMSG"))
            log(String.format("<font color=\"#A500A5\">%s</font>: " +
                    "<font color=\"#009c00\">&lt;</font>" +
                    "%s<font color=\"#009c00\">&gt;</font> " +
                    "%s", param, user, text));
        else if (command.equals("JOIN")) {
            param = !param.isEmpty() ? param : text;
            if (user.equals(connection.nick)) {
                log("You have joined channel " + param);
                this.channel = param;
                adapter2.add(param);
                adapter2.notifyDataSetChanged();
                ((Spinner) findViewById(R.id.channel))
                        .setSelection(adapter2.getPosition(this.channel));
            } else {
                log(user + " has joined channel <strong>" +
                        "<font color=\"#A500A5\">" + param + "</font></strong>");
            }
        } else if (command.equals("NICK"))
            if (user.equals(connection.nick)) {
                log("You are now known as " + text);
                ((EditText) findViewById(R.id.message)).setHint(text);
                connection.nick = text;
            } else {
                log(user + " is now known as " + text);
            }
        else if (command.equals("NOTICE"))
            log(String.format("<font color=\"#009c9c\">-</font>" +
                    "<font color=\"#FF4500\">%s</font>" +
                    "<font color=\"#FF4500\">-</font> " +
                    "%s", user, text));
        else if (command.equals("PART"))
            if (user.equals(connection.nick)) {
                log("You have left channel " + param);
                if (param.equals(this.channel)) {
                    int pos = adapter2.getPosition(this.channel) - 1;
                    this.channel = adapter2.getItem(pos);
                    ((Spinner) findViewById(R.id.channel)).setSelection(pos);
                }
                adapter2.remove(param);
                adapter2.notifyDataSetChanged();
            } else {
                log(user + " has left channel " + param);
            }
        else if (command.equals("QUIT"))
            ;
        else if (command.equals("TOPIC"))
            log("Topic for channel <font color=\"#009c9c\">" + param +
                    "</font> is <font color=\"#009c9c\">" + text + "</font>");
        else if (command.equals("332"))
            log("Topic for channel <font color=\"#009c9c\">" +
                    param.split(" ", 2)[1] +
                    "</font> is <font color=\"#009c9c\">" + text + "</font>");
        else if (!text.isEmpty())
            log("<font color=\"#555555\">* " + text + "</font>");
    }

    private void log(String message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
    }

    // Class to run socket connection in background
    private class ConnectTask extends AsyncTask<String, String, Connection> {

        @Override
        protected Connection doInBackground(String... params) {
            connection = new Connection(params[0],
                    Integer.parseInt(params[1]),
                    params[2],
                    params[3],
                    !params[4].isEmpty(),
                    params[5].split("[, ]"),
                    params[6],
                    new Connection.MessageCallback() {
                        @Override
                        public void rcv(String message) {
                            publishProgress(message);
                        }
                    });

            try {
                connection.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            parseSrv(values[0]);
        }

        }


    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure about that ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}

