
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


public class WhoisHandler extends BaseHandler
{
    /**
     * Execute /whois
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length != 2) {
            throw new CommandException(service.getString(R.string.invalid_number_of_params));
        }

        service.getConnection(server.getId()).sendRawLineViaQueue("WHOIS " + params[1]);
    }

    /**
     * Get description of /whois
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_whois);
    }

    /**
     * Get usage of /whois
     */
    @Override
    public String getUsage()
    {
        return "/whois <nickname>";
    }
}
