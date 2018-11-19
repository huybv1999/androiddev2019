
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


public class MeHandler extends BaseHandler
{
    /**
     * Execute /me
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (conversation.getType() == Conversation.TYPE_SERVER) {
            throw new CommandException(service.getString(R.string.only_usable_from_channel_or_query));
        }

        if (params.length > 1) {
            String action = BaseHandler.mergeParams(params);
            String nickname = service.getConnection(server.getId()).getNick();

            Message message = new Message(nickname + " " + action);
            message.setIcon(R.drawable.action);
            server.getConversation(conversation.getName()).addMessage(message);

            Intent intent = Broadcast.createConversationIntent(
                Broadcast.CONVERSATION_MESSAGE,
                server.getId(),
                conversation.getName()
            );
            service.sendBroadcast(intent);

            service.getConnection(server.getId()).sendAction(conversation.getName(), action);
        } else {
            throw new CommandException(service.getString(R.string.text_missing));
        }
    }

    /**
     * Usage of /me
     */
    @Override
    public String getUsage()
    {
        return "/me <text>";
    }

    /**
     * Description of /me
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_me);
    }
}
