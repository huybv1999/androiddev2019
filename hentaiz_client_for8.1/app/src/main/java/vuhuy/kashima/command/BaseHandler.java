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
package vuhuy.kashima.command;

import vuhuy.kashima.exception.CommandException;
import vuhuy.kashima.irc.IRCService;
import vuhuy.kashima.model.Conversation;
import vuhuy.kashima.model.Server;

import android.content.Context;

import vuhuy.kashima.exception.CommandException;

/**
 * Base class for commands
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public abstract class BaseHandler
{
    /**
     * Execute the command
     * 
     * @param params The params given (0 is the command itself)
     * @param server The server object
     * @paramchannel The channel object or null if no channel is selected
     * @param service The service with all server connections
     * @throws CommandException if command couldn't be executed
     */
    public abstract void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException;

    /**
     * Get the usage description for this command
     * 
     * @return The usage description
     */
    public abstract String getUsage();

    /**
     * Get the description for this command
     * 
     * @param context The current context. Needed for getting string resources
     * @return
     */
    public abstract String getDescription(Context context);

    /**
     * Merge params to a string
     * 
     * @params params The params to merge
     */
    public static String mergeParams(String[] params)
    {
        return mergeParams(params, 1);
    }

    /**
     * Merge params to a string
     * 
     * @param params The params to merge
     * @param position Start at given param
     */
    public static String mergeParams(String[] params, int position)
    {
        StringBuffer buffer = new StringBuffer();

        for (; position < params.length; position++) {
            buffer.append(params[position]);
            buffer.append(" ");
        }

        return buffer.toString().trim();
    }
}
