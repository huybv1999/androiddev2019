package h.vuhuy.myownirc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

/* Created by Huy and ConcernGuy
   huybv1998@gmail.com
   IRC Client for Android
 */

public class ConnectActivity extends AppCompatActivity {


    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_connect);
    }

    public void CreateChatActivity(View v) {
        EditText host = findViewById(R.id.host);
        EditText port = findViewById(R.id.port);
        EditText nick = findViewById(R.id.nickname);
        EditText password = findViewById(R.id.password);
        CheckBox auth_nickserv = findViewById(R.id.auth_nickserv);
        EditText autojoin = findViewById(R.id.autojoin);
        EditText script = findViewById(R.id.script);
        if( TextUtils.isEmpty(nick.getText()) || TextUtils.isEmpty(host.getText()) || TextUtils.isEmpty(port.getText())) {
            /**
             *  Require to fill in before connect
             **/

            nick.setError("Nick name is required!");
            host.setError("Host name is required!");
            port.setError("Port number is required");
        }
        else {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            intent.putExtra("host", host.getText().toString());
            intent.putExtra("port", port.getText().toString());
            intent.putExtra("nick", nick.getText().toString());
            intent.putExtra("password", password.getText().toString());
            intent.putExtra("auth_nickserv", auth_nickserv.isChecked() ? "1" : "");
            intent.putExtra("autojoin", autojoin.getText().toString());
            intent.putExtra("script", script.getText().toString());

            startActivity(intent);
            SharedPreferences preferences = getSharedPreferences("first_time", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }



    }


}
