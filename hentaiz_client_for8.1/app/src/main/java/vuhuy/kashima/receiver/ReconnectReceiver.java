
package vuhuy.kashima.receiver;

import vuhuy.kashima.irc.IRCService;
import vuhuy.kashima.model.Broadcast;
import vuhuy.kashima.model.Server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * A receiver to listen for alarms and start a reconnect attempt
 * 
 * @author Steven Luo <steven+android@steven676.net>
 */
public class ReconnectReceiver extends BroadcastReceiver
{
    private IRCService service;
    private Server server;

    /**
     * Create a new reconnect receiver
     * 
     * @param server The server to reconnect to
     */
    public ReconnectReceiver(IRCService service, Server server)
    {
        this.service = service;
        this.server = server;
    }

    /**
     * On receive broadcast
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (!intent.getAction().equals(Broadcast.SERVER_RECONNECT + server.getId())) {
            return;
        }
        service.connect(server);
    }
}
