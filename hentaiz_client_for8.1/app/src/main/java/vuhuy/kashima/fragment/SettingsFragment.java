package vuhuy.kashima.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import vuhuy.kashima.R;
import vuhuy.kashima.activity.HuyActivity;

/**
 * Fragment displaying all settings.
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String TRANSACTION_TAG = "fragment_settings";

    private HuyActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof HuyActivity)) {
            throw new IllegalArgumentException("Activity has to implement HuyActivity interface");
        }

        this.activity = (HuyActivity) context;
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();

        activity.setToolbarTitle(getString(R.string.navigation_settings));
    }

}
