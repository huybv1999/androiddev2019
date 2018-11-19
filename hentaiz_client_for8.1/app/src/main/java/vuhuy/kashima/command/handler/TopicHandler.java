
package vuhuy.kashima.command.handler;

import vuhuy.kashima.R;
import vuhuy.kashima.command.BaseHandler;
import vuhuy.kashima.exception.CommandException;
import vuhuy.kashima.irc.IRCService;
import vuhuy.kashima.model.Broadcast;
import vuhuy.kashima.model.Channel;
import vuhuy.kashima.model.Conversation;
import vuhuy.kashima.model.Message;
import vuhuy.kashima.model.Server;


import android.content.Context;


public class TopicHandler extends BaseHandler
{
    /**
     * Execute /topic
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (conversation.getType() != Conversation.TYPE_CHANNEL) {
            throw new CommandException(service.getString(R.string.only_usable_from_channel));
        }

        Channel channel = (Channel) conversation;

        if (params.length == 1) {
            // Show topic
            service.getConnection(server.getId()).onTopic(channel.getName(), channel.getTopic(), "", 0, false);
        } else if (params.length > 1) {
            // Change topic
            service.getConnection(server.getId()).setTopic(channel.getName(), BaseHandler.mergeParams(params));
        }
    }

    /**
     * Usage of /topic
     */
    @Override
    public String getUsage()
    {
        return "/topic [<topic>]";
    }

    /**
     * Description of /topic
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_topic);
    }
}
