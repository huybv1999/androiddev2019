/*
Huy - Yet Another Android IRC Client

Copyright 2009-2013 Sebastian Kaspari

This file is part of Huy.

Huy is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Huy is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Huy.  If not, see <http://www.gnu.org/licenses/>.
 */
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

import vuhuy.kashima.exception.CommandException;

/**
 * Command: /topic [<topic>]
 * 
 * Show the current topic or change the topic if a new topic is provided
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
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