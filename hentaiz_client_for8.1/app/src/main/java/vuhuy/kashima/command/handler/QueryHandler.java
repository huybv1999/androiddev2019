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
import vuhuy.kashima.model.Conversation;
import vuhuy.kashima.model.Query;
import vuhuy.kashima.model.Server;


import android.content.Context;
import android.content.Intent;

import vuhuy.kashima.exception.CommandException;

/**
 * Command: /query <nickname>
 * 
 * Opens a private chat with the given user
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class QueryHandler extends BaseHandler
{
    /**
     * Execute /query
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length == 2) {
            // Simple validation
            if (params[1].startsWith("#")) {
                throw new CommandException(service.getString(R.string.query_to_channel));
            }

            Conversation query = server.getConversation(params[1]);

            if (query != null) {
                throw new CommandException(service.getString(R.string.query_exists));
            }

            query = new Query(params[1]);
            query.setHistorySize(service.getSettings().getHistorySize());
            server.addConversation(query);

            Intent intent = Broadcast.createConversationIntent(
                Broadcast.CONVERSATION_NEW,
                server.getId(),
                query.getName()
            );
            service.sendBroadcast(intent);
        } else {
            throw new CommandException(service.getString(R.string.invalid_number_of_params));
        }
    }

    /**
     * Usage of /query
     */
    @Override
    public String getUsage()
    {
        return "/query <nickname>";
    }

    /**
     * Description of /query
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_query);
    }
}
