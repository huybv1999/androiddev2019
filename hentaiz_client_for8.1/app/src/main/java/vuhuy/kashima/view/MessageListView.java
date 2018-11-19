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
