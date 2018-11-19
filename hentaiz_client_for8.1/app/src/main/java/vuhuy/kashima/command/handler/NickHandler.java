
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
 * Command: /nick <nickname>
 */

public class NickHandler extends BaseHandler
{
    /**
     * Execute /nick
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length == 2) {
            service.getConnection(server.getId()).changeNick(params[1]);
        } else {
            throw new CommandException(service.getString(R.string.invalid_number_of_params));
        }
    }

    /**
     * Usage of /nick
     */
    @Override
    public String getUsage()
    {
        return "/nick <nickname>";
    }

    /**
     * Description of /nick
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_nick);
    }
}
