
package vuhuy.kashima.activity;

import vuhuy.kashima.R;
import vuhuy.kashima.adapter.UserActionListAdapter;
import vuhuy.kashima.model.Extra;
import vuhuy.kashima.model.Settings;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

/**
 * UserActivity
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class UserActivity extends ListActivity
{
    private String nickname;

    /**
     * On create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Settings settings = new Settings(this);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.lighttheme);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.user);
        if (settings.ChangeTheme())
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setListAdapter(new UserActionListAdapter());

        nickname = getIntent().getStringExtra(Extra.USER);
        ((TextView) findViewById(R.id.nickname)).setText(nickname);


    }

    /**
     * On action selected
     */
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id)
    {
        Intent intent = new Intent();
        intent.putExtra(Extra.ACTION, (int) id);
        intent.putExtra(Extra.USER, nickname);
        setResult(RESULT_OK, intent);
        finish();
    }
}
