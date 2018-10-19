package weather.usth.edu.vn.labworkmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ForecastFragment extends Fragment {
    public ForecastFragment() {
        super();
    }
    @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView day = new TextView(getActivity());
        day.setText("Thursday");
        ImageView weather = new ImageView(getContext());
        weather.setImageResource(R.drawable.ic_action_name);
        View v = new View(getContext());
        //View v = super.onCreateView(inflater, container, savedInstanceState);
        // v.setBackgroundColor(0xFF00FF00);
        LinearLayout rootViewGroup = new LinearLayout(getActivity());
        rootViewGroup.setOrientation(LinearLayout.VERTICAL);
        rootViewGroup.addView(day);
        rootViewGroup.addView(weather);
        rootViewGroup.addView(v);
        return rootViewGroup;



    }

}
