
package vuhuy.kashima.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import vuhuy.kashima.listener.MessageClickListener;
import vuhuy.kashima.model.Conversation;
import vuhuy.kashima.model.Server;
import vuhuy.kashima.view.MessageListView;

import java.util.HashMap;
import java.util.LinkedList;


public class ConversationPagerAdapter extends PagerAdapter
{
    private final Server server;
    private LinkedList<ConversationInfo> conversations;
    private final HashMap<Integer, View> views;

    /**
     * Container class to remember conversation and view association.
     */
    public class ConversationInfo {
        public Conversation conv;
        public MessageListAdapter adapter;
        public MessageListView view;

        public ConversationInfo(Conversation conv) {
            this.conv = conv;
            this.adapter = null;
            this.view = null;
        }
    }

    /**
     * Create a new {@link ConversationPagerAdapter} instance.
     */
    public ConversationPagerAdapter(Context context, Server server) {
        this.server = server;

        conversations = new LinkedList<ConversationInfo>();
        views = new HashMap<Integer, View>();
    }

    /**
     * Add a conversation to the adapter.
     *
     * @param conversation
     */
    public void addConversation(Conversation conversation) {
        conversations.add(new ConversationInfo(conversation));

        notifyDataSetChanged();
    }

    /**
     * Remove the conversation at the given position from the adapter.
     *
     * @param position
     */
    public void removeConversation(int position) {
        conversations.remove(position);

        notifyDataSetChanged();
    }

    /**
     * Get position of given item.
     */
    @Override
    public int getItemPosition(Object object)
    {
        if (views.containsKey(object)) {
            return POSITION_UNCHANGED;
        }

        return POSITION_NONE;
    }

    /**
     * Get item at position
     */
    public Conversation getItem(int position)
    {
        ConversationInfo convInfo = getItemInfo(position);
        if (convInfo != null) {
            return convInfo.conv;
        } else {
            return null;
        }
    }

    /**
     * Get the adapter of the {@link MessageListView} at the given position.
     *
     * @param position
     * @return
     */
    public MessageListAdapter getItemAdapter(int position)
    {
        ConversationInfo convInfo = getItemInfo(position);
        if (convInfo != null) {
            return convInfo.adapter;
        } else {
            return null;
        }
    }

    /**
     * Get the adapter of the {@link MessageListView} for the conversation
     * with the given name.
     *
     * @param name
     * @return
     */
    public MessageListAdapter getItemAdapter(String name)
    {
        return getItemAdapter(getPositionByName(name));
    }

    /**
     * Get ConversationInfo on item at position
     *
     * @param position
     */
    private ConversationInfo getItemInfo(int position) {
        if (position >= 0 && position < conversations.size()) {
            return conversations.get(position);
        }
        return null;
    }

    /**
     * Get an item by the channel's name
     *
     * @return The item
     */
    public int getPositionByName(String name)
    {
        // Optimization - cache field lookups
        int mSize = conversations.size();
        LinkedList<ConversationInfo> mItems = this.conversations;

        for (int i = 0; i <  mSize; i++) {
            if (mItems.get(i).conv.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Remove all conversations.
     */
    public void clearConversations()
    {
        conversations = new LinkedList<ConversationInfo>();
    }

    /**
     * Get number of conversations from this adapter.
     */
    @Override
    public int getCount()
    {
        return conversations.size();
    }

    /**
     * Determines whether a page View is associated with a specific key object.
     */
    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    /**
     * Create a view object for the conversation at the given position.
     */
    @Override
    public Object instantiateItem(View collection, int position) {
        // ConversationInfo convInfo = getItemInfo(position);
        ConversationInfo convInfo = conversations.get(position);
        View view;

        if (convInfo.view != null) {
            view = convInfo.view;
        } else {
            view = renderConversation(convInfo, collection);
        }

        views.put(position, view);
        ((ViewPager) collection).addView(view);

        return view;
    }

    /**
     * Render the given conversation and return the new view.
     *
     * @param convInfo
     * @param parent
     * @return
     */
    private MessageListView renderConversation(ConversationInfo convInfo, View parent)
    {
        MessageListView list = new MessageListView(parent.getContext());
        convInfo.view = list;
        list.setOnItemClickListener(MessageClickListener.getInstance());

        MessageListAdapter adapter = convInfo.adapter;

        if (adapter == null) {
            adapter = new MessageListAdapter(convInfo.conv, parent.getContext());
            convInfo.adapter = adapter;
        }


        list.setAdapter(adapter);
        list.setSelection(adapter.getCount() - 1); // scroll to bottom

        return list;
    }

    /**
     * Remove the given view from the adapter and collection.
     */
    @Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((View) view);
        views.remove(position);
    }

    /**
     * Get the title for the given position.
     */
    @Override
    public String getPageTitle(int position)
    {
        Conversation conversation = getItem(position);

        if (conversation.getType() == Conversation.TYPE_SERVER) {
            return server.getTitle();
        } else {
            return conversation.getName();
        }
    }
}
