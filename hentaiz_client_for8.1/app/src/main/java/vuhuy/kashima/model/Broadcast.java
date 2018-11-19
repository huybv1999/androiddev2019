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
along with Huy.  If not, see <g>.
 */
package vuhuy.kashima.model;

import android.content.Intent;

/**
 * Constants and helpers for Broadcasts
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public abstract class Broadcast
{
    public static final String SERVER_UPDATE         = "org.kashima.server.status";
    public static final String SERVER_RECONNECT      = "org.kashima.server.reconnect.";

    public static final String CONVERSATION_MESSAGE    = "org.kashima.conversation.message";
    public static final String CONVERSATION_NEW        = "org.kashima.conversation.new";
    public static final String CONVERSATION_REMOVE    = "org.kashima.conversation.remove";
    public static final String CONVERSATION_TOPIC    = "org.kashima.conversation.topic";

    /**
     * Create an Intent for conversation broadcasting
     * 
     * @param broadcastType The type of the broadcast, some constant of Broadcast.*
     * @param serverId The id of the server
     * @param conversationName The unique name of the conversation
     * @return  The created Intent
     */
    public static Intent createConversationIntent(String broadcastType, int serverId, String conversationName)
    {
        Intent intent = new Intent(broadcastType);

        intent.putExtra(Extra.SERVER, serverId);
        intent.putExtra(Extra.CONVERSATION, conversationName);

        return intent;
    }

    /**
     * Create an Intent for server broadcasting
     * 
     * @param broadcastType The typo of the broadcast, some constant of Broadcast.*
     * @param serverId The id of the server
     * @return The created Intent
     */
    public static Intent createServerIntent(String broadcastType, int serverId)
    {
        Intent intent = new Intent(broadcastType);

        intent.putExtra(Extra.SERVER, serverId);

        return intent;
    }
}
