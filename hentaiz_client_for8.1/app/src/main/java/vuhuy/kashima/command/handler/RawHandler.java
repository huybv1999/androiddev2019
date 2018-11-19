
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


public class RawHandler extends BaseHandler
{
    /**
     * Execute /raw
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length > 1) {
            String line = BaseHandler.mergeParams(params);
            service.getConnection(server.getId()).sendRawLineViaQueue(line);
        } else {
            throw new CommandException(service.getString(R.string.line_missing));
        }
    }

    /**
     * Usage of /raw
     */
    @Override
    public String getUsage()
    {
        return "/raw <line>";
    }

    /**
     * Description of /raw
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_raw);
    }
}
