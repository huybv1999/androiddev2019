
package vuhuy.kashima.activity;

import vuhuy.kashima.R;
import vuhuy.kashima.model.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Small dialog to show an edittext for joining channels
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 *
 */
public class JoinActivity extends Activity implements OnClickListener
{
    /**
     * On create
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Settings settings = new Settings(this);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.lighttheme);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.join);

        if (settings.ChangeTheme())
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        ((Button) findViewById(R.id.join)).setOnClickListener(this);

        ((EditText) findViewById(R.id.channel)).setSelection(1);
    }

    /**
     * On click
     */
    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("channel", ((EditText) findViewById(R.id.channel)).getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
