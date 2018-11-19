
package vuhuy.kashima;

import java.util.ArrayList;
import java.util.List;

import vuhuy.kashima.db.Database;
import vuhuy.kashima.model.Server;

import android.content.Context;
import android.util.SparseArray;

/**
 * Global Master Class :)
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class Huy
{
    private static Huy instance;

    private SparseArray<Server> servers;
    private boolean serversLoaded = false;

    /**
     * Private constructor, you may want to use static getInstance()
     */
    private Huy()
    {
        servers = new SparseArray<Server>();
    }

    /**
     * Load servers from database
     * 
     * @param context
     */
    public void loadServers(Context context)
    {
        if (!serversLoaded) {
            Database db = new Database(context);
            servers = db.getServers();
            db.close();

            serversLoaded = true;
        }
    }

    /**
     * Get global Huy instance
     * 
     * @return the global Huy instance
     */
    public static Huy getInstance()
    {
        if (instance == null) {
            instance = new Huy();
        }

        return instance;
    }

    /**
     * Get server by id
     * 
     * @return Server object with given unique id
     */
    public Server getServerById(int serverId)
    {
        return servers.get(serverId);
    }

    /**
     * Remove server with given unique id from list
     * 
     * @param serverId
     */
    public void removeServerById(int serverId)
    {
        servers.remove(serverId);
    }

    /**
     * Add server to list
     */
    public void addServer(Server server)
    {
        servers.put(server.getId(), server);
    }

    /**
     * Update a server in list
     */
    public void updateServer(Server server)
    {
        servers.put(server.getId(), server);
    }

    /**
     * Get list of servers
     * 
     * @return list of servers
     */
    public List<Server> getServers()
    {
        List<Server> servers = new ArrayList<>(this.servers.size());

        for (int i = 0; i < this.servers.size(); i++) {
            servers.add(this.servers.valueAt(i));
        }

        return servers;
    }
}
