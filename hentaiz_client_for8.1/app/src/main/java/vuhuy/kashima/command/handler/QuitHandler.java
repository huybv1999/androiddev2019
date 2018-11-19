

package vuhuy.kashima.command.handler;

import vuhuy.kashima.R;
import vuhuy.kashima.command.BaseHandler;
import vuhuy.kashima.exception.CommandException;
import vuhuy.kashima.irc.IRCService;
import vuhuy.kashima.model.Conversation;
import vuhuy.kashima.model.Server;


import android.content.Context;


public class QuitHandler extends BaseHandler
{
    /**
     * Execute /quit
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length == 1) {
            service.getConnection(server.getId()).quitServer();
        } else {
            service.getConnection(server.getId()).quitServer(BaseHandler.mergeParams(params));
        }
    }

    /**
     * Usage of /quit
     */
    @Override
    public String getUsage()
    {
        return "/quit [<reason>]";
    }

    /**
     * Description of /quit
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_quit);
    }
}
