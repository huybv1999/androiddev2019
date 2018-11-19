
package vuhuy.kashima.command.handler;

import java.util.HashMap;
import java.util.Set;

import vuhuy.kashima.R;
import vuhuy.kashima.command.BaseHandler;
import vuhuy.kashima.command.CommandParser;
import vuhuy.kashima.exception.CommandException;
import vuhuy.kashima.irc.IRCService;
import vuhuy.kashima.model.Broadcast;
import vuhuy.kashima.model.Conversation;
import vuhuy.kashima.model.Message;
import vuhuy.kashima.model.Server;


import android.content.Context;
import android.content.Intent;


public class HelpHandler extends BaseHandler
{
    /**
     * Execute /help
     */
    @Override
    public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
    {
        if (params.length == 2) {
            showCommandDetails(service, server, conversation, params[1]);
        } else if (params.length == 1) {
            showAllCommands(service, server, conversation);
        } else {
            throw new CommandException(service.getString(R.string.invalid_number_of_params));
        }
    }

    /**
     * Show all available commands
     * 
     * @param conversation
     * @param server
     * @param service
     */
    private void showAllCommands(IRCService service, Server server, Conversation conversation)
    {
        CommandParser cp = CommandParser.getInstance();

        StringBuffer commandList = new StringBuffer(service.getString(R.string.available_commands));
        commandList.append("\n");

        HashMap<String, BaseHandler> commands = cp.getCommands();
        HashMap<String, String> aliases = cp.getAliases();

        Set<String> commandKeys = commands.keySet();
        Set<String> aliasesKeys = aliases.keySet();

        for (Object command : commandKeys) {
            String alias = "";
            for (Object aliasCommand : aliasesKeys) {
                if (command.equals(aliases.get(aliasCommand))) {
                    alias = " " + service.getString(R.string.logical_or) + " /" + aliasCommand;
                    break;
                }
            }
            commandList.append("/" + command.toString() + alias + " - "+commands.get(command).getDescription(service) + "\n");
        }

        Message message = new Message(commandList.toString());
        message.setColor(Message.COLOR_YELLOW);
        conversation.addMessage(message);

        Intent intent = Broadcast.createConversationIntent(
            Broadcast.CONVERSATION_MESSAGE,
            server.getId(),
            conversation.getName()
        );

        service.sendBroadcast(intent);
    }

    /**
     * Show details of a single command
     * 
     * @param conversation
     * @param server
     * @param service
     * @param command
     * @throws CommandException
     */
    private void showCommandDetails(IRCService service, Server server, Conversation conversation, String command) throws CommandException
    {
        CommandParser cp = CommandParser.getInstance();
        HashMap<String, BaseHandler> commands = cp.getCommands();

        if (commands.containsKey(command)) {
            // XXX:I18N - String building salad :)
            Message message = new Message("Help of /" + command + "\n" + commands.get(command).getUsage() + "\n" + commands.get(command).getDescription(service) + "\n");
            message.setColor(Message.COLOR_YELLOW);
            conversation.addMessage(message);

            Intent intent = Broadcast.createConversationIntent(
                Broadcast.CONVERSATION_MESSAGE,
                server.getId(),
                conversation.getName()
            );

            service.sendBroadcast(intent);
        } else {
            throw new CommandException(service.getString(R.string.unknown_command, command));
        }
    }

    /**
     * Usage of /help
     */
    @Override
    public String getUsage()
    {
        return "/help [<command>]";
    }

    /**
     * Description of /help
     */
    @Override
    public String getDescription(Context context)
    {
        return context.getString(R.string.command_desc_help);
    }
}
