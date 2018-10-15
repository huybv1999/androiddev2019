package weather.usth.edu.vn.labworkmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ForecastFragment extends Fragment {
    public ForecastFragment() {
        super();
    }
    @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = new View(getContext());
        //View v = super.onCreateView(inflater, container, savedInstanceState);
        v.setBackgroundColor(0xFF00FF00);
        return v;
    }
}
