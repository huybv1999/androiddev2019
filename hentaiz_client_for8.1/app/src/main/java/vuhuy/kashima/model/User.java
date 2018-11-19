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
package vuhuy.kashima.model;

/**
 * Helper class for anything regarding users
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class User
{
    public static final int ACTION_REPLY   = 1;
    public static final int ACTION_QUERY   = 2;
    public static final int ACTION_OP      = 3;
    public static final int ACTION_DEOP    = 4;
    public static final int ACTION_VOICE   = 5;
    public static final int ACTION_DEVOICE = 6;
    public static final int ACTION_KICK    = 7;
    public static final int ACTION_BAN     = 8;
}
