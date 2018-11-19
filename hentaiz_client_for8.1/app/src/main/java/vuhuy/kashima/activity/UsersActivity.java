
package vuhuy.kashima.activity;

import java.util.Arrays;

import vuhuy.kashima.R;
import vuhuy.kashima.model.Extra;
import vuhuy.kashima.model.Settings;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;


public class UsersActivity extends ListActivity implements OnItemClickListener
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.users);

        if (settings.ChangeTheme())
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        final String[] users = getIntent().getExtras().getStringArray(Extra.USERS);
        getListView().setOnItemClickListener(this);

        // Add sorted list of users in own thread to avoid blocking UI
        // TODO: Move to a background task and show loading indicator while sorting
        Arrays.sort(users, String.CASE_INSENSITIVE_ORDER);
        getListView().setAdapter(new ArrayAdapter<String>(UsersActivity.this, R.layout.useritem, users));


    }

    /**
     * On user selected
     */
    @Override
    public void onItemClick(AdapterView<?> list, View item, int position, long id)
    {
        Intent intent = new Intent();
        intent.putExtra(Extra.USER, (String) getListView().getAdapter().getItem(position));
        setResult(RESULT_OK, intent);
        finish();
    }
}
