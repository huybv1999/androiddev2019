
package vuhuy.kashima.view;

import vuhuy.kashima.adapter.MessageListAdapter;
import vuhuy.kashima.listener.MessageClickListener;

import android.content.Context;
import android.widget.ListView;

import vuhuy.kashima.adapter.MessageListAdapter;
import vuhuy.kashima.listener.MessageClickListener;

/**
 * A customized ListView for Messages
 *
 * @author Sebastian Kaspari <sebastian@kashima.org>
 */
public class MessageListView extends ListView
{
    /**
     * Create a new MessageListView
     *
     * @param context
     */
    public MessageListView(Context context)
    {
        super(context);

        setOnItemClickListener(MessageClickListener.getInstance());

        setDivider(null);

        setCacheColorHint(0xFFFFFFFF);
        setVerticalFadingEdgeEnabled(false);
        setScrollBarStyle(SCROLLBARS_OUTSIDE_INSET);

        // Scale padding by screen density
        float density = context.getResources().getDisplayMetrics().density;
        int padding = (int) (5 * density);
        setPadding(padding, padding, padding, padding);

        setTranscriptMode(TRANSCRIPT_MODE_NORMAL);
    }

    /**
     * Get the adapter of this MessageListView
     * (Helper to avoid casting)
     *
     * @return The MessageListAdapter
     */
    @Override
    public MessageListAdapter getAdapter()
    {
        return (MessageListAdapter) super.getAdapter();
    }
}
