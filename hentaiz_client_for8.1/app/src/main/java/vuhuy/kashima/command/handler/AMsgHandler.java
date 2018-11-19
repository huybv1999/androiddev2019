
package vuhuy.kashima.command.handler;

import java.util.Collection;

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


public class AMsgHandler extends BaseHandler
{
    /**
     * Execute /amsg
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length > 1) {
            String text = BaseHandler.mergeParams(params);

            Collection<Conversation> mConversations = server.getConversations();

            for (Conversation currentConversation : mConversations) {
                if (currentConversation.getType() == Conversation.TYPE_CHANNEL) {
                    Message message = new Message("<" + service.getConnection(server.getId()).getNick() + "> " + text);
                    currentConversation.addMessage(message);

                    Intent intent = Broadcast.createConversationIntent(
                        Broadcast.CONVERSATION_MESSAGE,
                        server.getId(),
                        currentConversation.getName()
                    );

                    service.sendBroadcast(intent);

                    service.getConnection(server.getId()).sendMessage(currentConversation.getName(), text);
                }
            }
        } else {
            throw new CommandException(service.getString(R.string.invalid_number_of_params));
        }
    }

    /**
     * Usage of /amsg
     */
    @Override
    public String getUsage()
    {
        return "/amsg <message>";
    }

    /**
     * Description of /amsg
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_amsg);
    }
}
