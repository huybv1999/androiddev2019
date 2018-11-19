
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
 * Command: /deop <nickname>
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class DeopHandler extends BaseHandler
{
    /**
     * Execute /deop
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (conversation.getType() != Conversation.TYPE_CHANNEL) {
            throw new CommandException(service.getString(R.string.only_usable_from_channel));
        }

        if (params.length == 2) {
            service.getConnection(server.getId()).deOp(conversation.getName(), params[1]);
        } else {
            throw new CommandException(service.getString(R.string.invalid_number_of_params));
        }
    }

    /**
     * Usage of /deop
     */
    @Override
    public String getUsage()
    {
        return "/deop <nickname>";
    }

    /**
     * Description of /deop
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_deop);
    }
}
