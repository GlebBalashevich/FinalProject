package by.balashevich.finalproject.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Command provider.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();

    private CommandProvider() {
    }

    /**
     * Define command action command.
     *
     * @param commandName the command name
     * @return the action command
     */
    public static ActionCommand defineCommand(String commandName) {
        ActionCommand currentCommand = CommandType.EMPTY.getCommand();

        if (commandName != null && !commandName.isEmpty()) {
            try {
                currentCommand = CommandType.valueOf(commandName.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "The command is not defined " + commandName);
            }
        }

        return currentCommand;
    }
}
