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
package vuhuy.kashima.receiver;

import vuhuy.kashima.listener.ServerListener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vuhuy.kashima.listener.ServerListener;

/**
 * A server receiver for receiving server updates
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class ServerReceiver extends BroadcastReceiver
{
    private final ServerListener listener;

    /**
     * Create a new server receiver
     * 
     * @param listener
     */
    public ServerReceiver(ServerListener listener)
    {
        this.listener = listener;
    }

    /**
     * On receive broadcast
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        listener.onStatusUpdate();
    }
}
