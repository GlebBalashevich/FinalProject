package by.balashevich.finalproject.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static by.balashevich.finalproject.controller.command.CommandType.*;

/**
 * The enum Command client status access.
 *
 * This enum is described to differentiate the client's access levels to the elements
 * of the application, depending on its status. Today the client has one of the
 * statuses: PENDING, ACTIVE, BLOCKED ({@code Client.Status}). Depending on the status,
 * the web filter {@code ClientStatusSecurityFilter} can pass requests to execute a particular
 * command {@code CommandType} or block access.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public enum CommandClientStatusAccess {
    /**
     * Available commands for client status PENDING
     */
    PENDING(EnumSet.of(MOVE_HOME_PAGE,
            MOVE_CARS_PAGE,
            LOG_OUT_USER,
            SWITCH_LOCALE,
            ACTIVATE_CLIENT,
            FIND_AVAILABLE_CARS,
            PAGINATION)),
    /**
     * Available commands for client status ACTIVE
     */
    ACTIVE(EnumSet.of(MOVE_HOME_PAGE,
            MOVE_CARS_PAGE,
            MOVE_CAR_CARD_PAGE,
            MOVE_ORDERS_PAGE,
            MOVE_PAYMENT_PAGE,
            LOG_OUT_USER,
            SWITCH_LOCALE,
            FIND_AVAILABLE_CARS,
            ORDER_CAR,
            MAKE_ORDER_PAYMENT,
            UPDATE_ORDER_STATUS,
            PAGINATION)),
    /**
     * Available commands for client status BLOCKED
     */
    BLOCKED(EnumSet.of(MOVE_HOME_PAGE,
            LOG_OUT_USER,
            SWITCH_LOCALE));

    private Set<CommandType> accessCommands;

    CommandClientStatusAccess(Set<CommandType> accessCommands) {
        this.accessCommands = accessCommands;
    }

    /**
     * Returns the set of available commands according to the corresponding status value
     *
     * @return the access commands
     */
    public Set<CommandType> getAccessCommands() {
        return this.accessCommands;
    }
}
