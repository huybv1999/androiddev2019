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
