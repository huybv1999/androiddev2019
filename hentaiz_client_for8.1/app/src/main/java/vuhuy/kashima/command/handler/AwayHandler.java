
package vuhuy.kashima.command.handler;

import vuhuy.kashima.R;
import vuhuy.kashima.command.BaseHandler;
import vuhuy.kashima.exception.CommandException;
import vuhuy.kashima.irc.IRCService;
import vuhuy.kashima.model.Broadcast;
import vuhuy.kashima.model.Conversation;
import vuhuy.kashima.model.Message;
import vuhuy.kashima.model.Server;


import android.content.Context;

public class AwayHandler extends BaseHandler
{
    /**
     * Execute /away
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        service.getConnection(server.getId()).sendRawLineViaQueue("AWAY " + BaseHandler.mergeParams(params));
    }

    /**
     * Get description of /away
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_away);
    }

    /**
     * Get usage of /away
     */
    @Override
    public String getUsage()
    {
        return "/away [<reason>]";
    }
}
