package ircclient.kashima.vuhuy.myircclient;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.Activity;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navView;

    public String[] channels = {"#Global"};
    public SharedPreferences preferences;
    public boolean ranBefore;
    EditText mEdit;
    Button mButton;
    TextView mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences = getSharedPreferences("first_time", Context.MODE_PRIVATE);
        ranBefore = preferences.getBoolean("RanBefore", false);
        if (ranBefore == false) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.container);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();



        navView = (NavigationView) findViewById(R.id.menu_drawer);
        if (navView != null) {
            setupDrawerContent(navView);
        }
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            if(mToggle.onOptionsItemSelected(item)) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        private void setupDrawerContent(NavigationView navView) {
            navView.setNavigationItemSelectedListener(this);
        }

        public boolean onNavigationItemSelected(MenuItem item) {
            item.setCheckable(true);
            item.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
        }

    private void addNewChannelInNavDrawer(String newChannelName) {
        Menu menu = navView.getMenu();
        MenuItem newChannel = menu.add(R.id.menu_top, Menu.NONE, 0, newChannelName);
        newChannel.setCheckable(true);
        newChannel.setChecked(true);
    }



    public void createAndShowNewChannelDialog(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter a name:");

        final EditText editText = new EditText(this);
        builder.setView(editText);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String newChannelName = "# " + editText.getText().toString();
                addNewChannelInNavDrawer(newChannelName);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
public void sendText(View v){
        LinearLayout linearlayout = findViewById(R.id.get_messenges);
        mEdit = findViewById(R.id.senderbox);
        String content = "Kashima     " + mEdit.getText().toString();
        TextView t1 = new TextView(this);
        t1.setTextColor(ContextCompat.getColor(this, R.color.text_color));
        t1.setText(content);
        linearlayout.addView(t1);

}

    public void createAndShowLogOutDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_logout1, null));

        builder.setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                createAndShowLogOutDialog2();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void createAndShowLogOutDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_logout2, null));

        builder.setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                createAndShowLogOutDialoge3();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void createAndShowLogOutDialoge3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_logout3, null));

        builder.setPositiveButton(R.string.OK_FINE, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                createAndShowLogOutDialoge4();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void createAndShowLogOutDialoge4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_logout4, null));

        builder.setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                GoBackToLogIn();
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void GoBackToLogIn(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
        SharedPreferences preferences = getSharedPreferences("first_time", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("RanBefore", false);
        editor.apply();
    }

    public void OpenSettings(View view){
        Intent i = new Intent(this, SettingActivity.class);
        startActivity(i);
    }



    public void GoToAbout(MenuItem item){
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
        finish();
    }



    }



