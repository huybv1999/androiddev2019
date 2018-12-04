package weather.usth.edu.vn.labworkmobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class HomeFragmentPageAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String location[] = new String[]{"Hanoi, Vietnam", "Paris, France", "Toulouse, France"};
    public HomeFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        return new WeatherAndForecastFragment();
    }

    @Override
    public int getCount() {
        return (location.length);
    }
    @Override
    public CharSequence getPageTitle(int page) {
        // returns a tab title corresponding to the specified page
        return location[page];
    }
}