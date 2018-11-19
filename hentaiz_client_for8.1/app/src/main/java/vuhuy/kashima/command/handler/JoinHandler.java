
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
 * Command: /join <channel> [<key>]
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class JoinHandler extends BaseHandler
{
    /**
     * Execute /join
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length == 2) {
            service.getConnection(server.getId()).joinChannel(params[1]);
        } else if (params.length == 3) {
            service.getConnection(server.getId()).joinChannel(params[1], params[2]);
        } else {
            throw new CommandException(service.getString(R.string.invalid_number_of_params));
        }
    }

    /**
     * Usage of /join
     */
    @Override
    public String getUsage()
    {
        return "/join <channel> [<key>]";
    }

    /**
     * Description of /join
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_join);
    }
}
