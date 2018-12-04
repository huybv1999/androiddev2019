package weather.usth.edu.vn.labworkmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    Handler handler;

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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Log.i("Weather","CREATED *****");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        final URL temp;
        final HttpURLConnection tempurl;
        final InputStream tempis;
        switch (item.getItemId()) {
            case R.id.refresh:
                AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {
                    URL url = null;
                    HttpURLConnection connection = null;
                    InputStream is = null;
                    @Override
                    protected void onPreExecute() {

                        try {
                            url = new URL("https://www.usth.edu.vn/uploads/logo_1.png");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        // do some preparation here, if needed
                    }
                    @Override
                    protected Bitmap doInBackground(String... params) {
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        return null;

                        try {
                            connection = (HttpURLConnection) url.openConnection();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            connection.setRequestMethod("GET");
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        }
                        connection.setDoInput(true);

                        int response = 0;
                        try {
                            response = connection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("USTHWeather", "The response is: " + response);
                        try {
                            is = connection.getInputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return BitmapFactory.decodeStream(is);

                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
// This method is called in the main thread, so it's possible
// to update UI to reflect the worker thread progress here.
// In a network access task, this should update a progress bar
// to reflect how many percent of data has been retrieved

                    }
                    @Override
                    protected void onPostExecute(Bitmap bitmap) {
// This method is called in the main thread. After #doInBackground returns
// the bitmap data, we simply set it to an ImageView using ImageView.setImageBitmap()

                        ImageView logo = (ImageView) findViewById(R.id.logo);
                        logo.setImageBitmap(bitmap);
                        connection.disconnect();
//                        String content = "ALO";
//                        int duration = Toast.LENGTH_SHORT;
//                        Toast toast = Toast.makeText(WeatherActivity.this, content, duration);
//                        toast.show();
                    }

                };
                task.execute("https://www.usth.edu.vn/uploads/logo_1.png");

                return true;
            case  R.id.add:
                return true;
            default:
                super.onOptionsItemSelected(item);
                return true;
        }
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

//        InputStream is = this.getResources().openRawResource(R.raw.touhou);
  //      try {
  //        Log.i("File","TRY!");
   //        File file = new File(Environment
    //              .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "test.mp3");
    //        FileOutputStream os = new FileOutputStream(file);
    //       byte[] buf = new byte[8192];
    //        int c = 0;
   //         while ((c = is.read(buf)) > 0) {
   //             os.write(buf, 0, c);
   //         }
   //         os.close();

   //       Log.i("File","Written!");
  //     } catch (Exception e){
  //         e.printStackTrace();
   //    }
   //     MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.eurobeat);
     //   mediaPlayer.start();



    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: This is a log message.");
    }



}
