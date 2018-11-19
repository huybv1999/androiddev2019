
package vuhuy.kashima.activity;

import android.support.v7.widget.Toolbar;

import vuhuy.kashima.irc.IRCBinder;
import vuhuy.kashima.model.Server;

/**
 * Interface for fragments accessing functionality of the main activity.
 */
public interface HuyActivity {
    IRCBinder getBinder();

    Toolbar getToolbar();

    void setToolbarTitle(String title);

    void onServerSelected(Server server);
}
