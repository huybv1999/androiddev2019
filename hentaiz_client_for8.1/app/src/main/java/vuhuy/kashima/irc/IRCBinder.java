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
package vuhuy.kashima.irc;

import vuhuy.kashima.model.Server;

import android.os.Binder;

import vuhuy.kashima.model.Server;

/**
 * Binder for service communication
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class IRCBinder extends Binder
{
    private final IRCService service;

    /**
     * Create a new binder for given service
     *
     * @param service
     */
    public IRCBinder(IRCService service)
    {
        super();

        this.service = service;
    }

    /**
     * Connect to given server
     * 
     * @param server
     */
    public void connect(final Server server)
    {
        service.connect(server);
    }

    /**
     * Get service associated with this binder
     *
     * @return
     */
    public IRCService getService()
    {
        return service;
    }
}
