package weather.usth.edu.vn.labworkmobile;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i(TAG, "onCreate: This is a log message.");
        // ForecastFragment firstFragment = new ForecastFragment();
        // Add the fragment to the 'container' FrameLayout
        // getSupportFragmentManager().beginTransaction().add(
             // R.id.container, firstFragment).commit();
        PagerAdapter adapter = new HomeFragmentPageAdapter(
                getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        TabLayout tableLayout = (TabLayout) findViewById(R.id.container2);
        tableLayout.setupWithViewPager(pager);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: This is a log message.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: This is a log message.");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mediaPlayer.reset();
        Log.i(TAG, "onResume: This is a log message.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: This is a log message.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: This is a log message.");
    }



}
