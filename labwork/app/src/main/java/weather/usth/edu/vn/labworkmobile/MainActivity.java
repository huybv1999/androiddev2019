package weather.usth.edu.vn.labworkmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mtextView = (TextView) findViewById(R.id.text_message);
        mtextView.setText("Hello World");
    }
}
