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
package vuhuy.kashima.db;

import android.provider.BaseColumns;

/**
 * Constants for the identity table
 * 
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class IdentityConstants implements BaseColumns
{
    public static final String TABLE_NAME = "identities";

    // fields
    public static final String NICKNAME = "nickname";
    public static final String IDENT     = "ident";
    public static final String REALNAME = "realname";

    /**
     * All fields of the table
     */
    public static final String[] ALL = {
        _ID,
        NICKNAME,
        IDENT,
        REALNAME,
    };
}
