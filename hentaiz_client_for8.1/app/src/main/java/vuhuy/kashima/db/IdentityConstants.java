
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
