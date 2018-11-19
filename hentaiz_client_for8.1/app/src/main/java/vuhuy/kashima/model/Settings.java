
package vuhuy.kashima.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import vuhuy.kashima.R;


public class Settings
{
    private SwitchPreference mySwitch;
    private final SharedPreferences preferences;
    private final Resources resources;

    /**
     * Create a new Settings instance
     *
     * @param context
     */
    public Settings(Context context)
    {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.resources = context.getApplicationContext().getResources();
    }

    /**
     * Prefix all messages with a timestamp?
     *
     * @return
     */
    public boolean showTimestamp()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_show_timestamp),
            Boolean.parseBoolean(resources.getString(R.string.default_show_timestamp))
        );
    }



    public boolean showColorsNick()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_show_colors_nick),
            Boolean.parseBoolean(resources.getString(R.string.default_show_colors_nick))
        );
    }

    /**
     * Use 24 hour format for timestamps?
     *
     * @return
     */
    public boolean use24hFormat()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_24h_format),
            Boolean.parseBoolean(resources.getString(R.string.default_24h_format))
        );
    }

    /**
     * Include seconds in timestamps?
     *
     * @return
     */
    public boolean includeSeconds()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_include_seconds),
            Boolean.parseBoolean(resources.getString(R.string.default_include_seconds))
        );
    }


    /**
     * Is reconnect on disconnect enabled?
     *
     * @return
     */
    public boolean isReconnectEnabled()
    {
        return preferences.getBoolean(
                resources.getString(R.string.key_reconnect),
                Boolean.parseBoolean(resources.getString(R.string.default_reconnect))
        );
    }

    /**
     * Get the reconnect interval
     *
     * @return The reconnect interval in minutes
     */
    public int getReconnectInterval()
    {
        return Integer.parseInt(preferences.getString(
                resources.getString(R.string.key_reconnect_interval),
                resources.getString(R.string.default_reconnect_interval)
        ));
    }

    /**
     * Ignore the automatic MOTD?
     *
     * @return
     */
    public boolean isIgnoreMOTDEnabled()
    {
        return preferences.getBoolean(
                resources.getString(R.string.key_ignore_motd),
                Boolean.parseBoolean(resources.getString(R.string.default_ignore_motd))
        );
    }

    /**
     * Get the quit message
     *
     * @return The message to display when the user disconnects
     */
    public String getQuitMessage()
    {
        return preferences.getString(
                resources.getString(R.string.key_quitmessage),
                resources.getString(R.string.default_quitmessage)
        );
    }

    /**
     * Get the font size
     *
     * @return The font size for conversation messages
     */
    public int getFontSize()
    {
        return Integer.parseInt(preferences.getString(
                resources.getString(R.string.key_fontsize),
                resources.getString(R.string.default_fontsize)
        ));
    }

    /**
     * Play notification sound on highlight?
     *
     * @return True if sound should be played on highlight, false otherwise
     */
    public boolean isSoundHighlightEnabled()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_sound_highlight),
            Boolean.parseBoolean(resources.getString(R.string.default_sound_highlight))
        );
    }

    /**
     * Vibrate on highlight?
     *
     * @return True if vibrate on highlight is enabled, false otherwise
     */
    public boolean isVibrateHighlightEnabled()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_vibrate_highlight),
            Boolean.parseBoolean(resources.getString(R.string.default_vibrate_highlight))
        );
    }

    /**
     * LED light notification on highlight?
     *
     * @return True if LED light on highlight is enabled, false otherwise
     */
    public boolean isLedHighlightEnabled()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_led_highlight),
            Boolean.parseBoolean(resources.getString(R.string.default_led_highlight))
        );
    }

    /**
     * Should join, part and quit messages be displayed?
     *
     * @return True if joins, parts and quits should be displayed, false otherwise
     */
    public boolean showJoinPartAndQuit()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_show_joinpartquit),
            Boolean.parseBoolean(resources.getString(R.string.default_show_joinpartquit))
        );
    }

    /**
     * Should notices be shown in the server window instead in the focused window?
     *
     * @return True if notices should be shown in the server window
     */
    public boolean showNoticeInServerWindow()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_notice_server_window),
            Boolean.parseBoolean(resources.getString(R.string.default_notice_server_window))
        );
    }

    /**
     * Render messages with color and style codes.
     *
     * @return True if colors should be rendered, false if they should be removed.
     */
    public boolean showMircColors()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_mirc_colors),
            Boolean.parseBoolean(resources.getString(R.string.default_mirc_colors))
        );
    }

    /**
     * Render messages with graphical smilies.
     *
     * @return True if text smilies should be rendered as graphical smilies, false otherwise.
     */
    public boolean showGraphicalSmilies()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_graphical_smilies),
            Boolean.parseBoolean(resources.getString(R.string.default_graphical_smilies))
        );
    }

    /**
     * Whether message text should be autocorrected.
     */
    public boolean autoCorrectText()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_autocorrect_text),
            Boolean.parseBoolean(resources.getString(R.string.default_autocorrect_text))
        );
    }

    /**
     * Should IRC traffic be logged to the verbose log?
     * @return
     */

    /**
     * Whether sentences in messages should be automatically capitalized.
     */
    public boolean autoCapSentences()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_autocap_sentences),
            Boolean.parseBoolean(resources.getString(R.string.default_autocap_sentences))
        );
    }

    /**
     * Whether the fullscreen keyboard should be used in landscape mode.
     */
    public boolean imeExtract()
    {
        return preferences.getBoolean(
            resources.getString(R.string.key_ime_extract),
            Boolean.parseBoolean(resources.getString(R.string.default_ime_extract))
        );
    }

    /**
     * Get the conversation history size.
     *
     * @return The conversation history size
     */
    public int getHistorySize()
    {
        try {
            return Integer.parseInt(preferences.getString(
                resources.getString(R.string.key_history_size),
                resources.getString(R.string.default_history_size)
            ));
        } catch (NumberFormatException e) {
            return Integer.parseInt(resources.getString(R.string.default_history_size));
        }
    }
    public boolean ChangeTheme()
    {
        return preferences.getBoolean(resources.getString(R.string.key_theme),
                Boolean.parseBoolean(resources.getString(R.string.default_theme))
        );
    }

}
