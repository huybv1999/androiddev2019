/*
Huy - Yet Another Android IRC Client

Copyright 2009-2013 Sebastian Kaspari

This file is part of Huy.

Huy is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Huy is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Huy.  If not, see <http://www.gnu.org/licenses/>.
 */
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
