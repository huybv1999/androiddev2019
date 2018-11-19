
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
import android.content.Intent;


public class CloseHandler extends BaseHandler
{
    /**
     * Execute /close
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (conversation.getType() == Conversation.TYPE_SERVER) {
            throw new CommandException(service.getString(R.string.close_server_window));
        }

        if (params.length == 1) {
            if (conversation.getType() == Conversation.TYPE_CHANNEL) {
                service.getConnection(server.getId()).partChannel(conversation.getName());
            }
            if (conversation.getType() == Conversation.TYPE_QUERY) {
                server.removeConversation(conversation.getName());

                Intent intent = Broadcast.createConversationIntent(
                    Broadcast.CONVERSATION_REMOVE,
                    server.getId(),
                    conversation.getName()
                );
                service.sendBroadcast(intent);
            }
        }
    }

    /**
     * Usage of /close
     */
    @Override
    public String getUsage()
    {
        return "/close";
    }

    /**
     * Description of /close
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_close);
    }
}
