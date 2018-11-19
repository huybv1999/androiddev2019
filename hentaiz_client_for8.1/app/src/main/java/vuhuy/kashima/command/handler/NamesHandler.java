
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

import vuhuy.kashima.protocol.User;


public class NamesHandler extends BaseHandler
{
    /**
     * Execute /names
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (conversation.getType() != Conversation.TYPE_CHANNEL) {
            throw new CommandException(service.getString(R.string.only_usable_from_channel));
        }

        StringBuffer userList = new StringBuffer(service.getString(R.string.message_users_on_chan, conversation.getName()));

        User[] mUsers = service.getConnection(server.getId()).getUsers(conversation.getName());
        int mSize = mUsers.length;
        for (int i = 0; i < mSize; i++) {
            userList.append(" ");
            userList.append(mUsers[i].getPrefix());
            userList.append(mUsers[i].getNick());
        }

        Message message = new Message(userList.toString());
        message.setColor(Message.COLOR_YELLOW);
        conversation.addMessage(message);

        Intent intent = Broadcast.createConversationIntent(
            Broadcast.CONVERSATION_MESSAGE,
            server.getId(),
            conversation.getName()
        );
        service.sendBroadcast(intent);
    }

    /**
     * Usage of /names
     */
    @Override
    public String getUsage()
    {
        return "/names";
    }

    /**
     * Description of /names
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_names);
    }
}
