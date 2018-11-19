
package vuhuy.kashima;

import android.app.Application;

/**
 * Application implementation for Huy.
 */
public class HuyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Huy.getInstance().loadServers(this);
    }
}
