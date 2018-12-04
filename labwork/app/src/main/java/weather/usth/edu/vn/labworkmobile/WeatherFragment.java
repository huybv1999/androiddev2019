package weather.usth.edu.vn.labworkmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeatherFragment extends Fragment {
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  View v = new View(getContext());
        return inflater.inflate(R.layout.fragment_weather,container,false);



    }
}
