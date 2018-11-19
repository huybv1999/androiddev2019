
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

import vuhuy.kashima.exception.CommandException;

/**
 * Command: /back
 * 
 * Turn off the away status
 * 
 * @author Francesco Lavra <francescola...@interfree.it>
 */
public class BackHandler extends BaseHandler
{
    /**
     * Execute /back
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        service.getConnection(server.getId()).sendRawLineViaQueue("AWAY");
    }

    /**
     * Get description of /back
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_back);
    }

    /**
     * Get usage of /back
     */
    @Override
    public String getUsage()
    {
        return "/back";
    }
}
