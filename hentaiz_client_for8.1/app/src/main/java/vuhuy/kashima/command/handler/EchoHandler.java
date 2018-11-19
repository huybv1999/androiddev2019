
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


public class EchoHandler extends BaseHandler
{
    /**
     * Execute /echo
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length > 1) {
            Message message = new Message(BaseHandler.mergeParams(params));
            conversation.addMessage(message);

            Intent intent = Broadcast.createConversationIntent(
                Broadcast.CONVERSATION_MESSAGE,
                server.getId(),
                conversation.getName()
            );
            service.sendBroadcast(intent);
        } else {
            throw new CommandException(service.getString(R.string.text_missing));
        }
    }

    /**
     * Usage of /echo
     */
    @Override
    public String getUsage()
    {
        return "/echo <text>";
    }

    /**
     * Description of /echo
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_echo);
    }
}
