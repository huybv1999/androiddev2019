
package vuhuy.kashima.activity;

import vuhuy.kashima.R;
import vuhuy.kashima.model.Extra;
import vuhuy.kashima.model.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;
import android.widget.TextView;

/**
 * Activity for single message view
 * 
 * @author Sebastian Kaspari <sebastian@yaaic.org>
 */
public class MessageActivity extends Activity
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

        if (settings.ChangeTheme())
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message);

        ((TextView) findViewById(R.id.message)).setText(
            getIntent().getExtras().getString(Extra.MESSAGE)
        );
    }
}
