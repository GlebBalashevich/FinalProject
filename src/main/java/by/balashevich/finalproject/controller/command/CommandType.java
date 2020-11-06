package by.balashevich.finalproject.controller.command;

import by.balashevich.finalproject.controller.command.impl.*;
import by.balashevich.finalproject.controller.command.impl.pagecommand.*;

/**
 * The enum Command type.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public enum CommandType {
    MOVE_LOGIN_PAGE(new LoginPageCommand()),
    MOVE_REGISTER_PAGE(new RegisterPageCommand()),
    MOVE_HOME_PAGE(new HomePageCommand()),
    MOVE_CARS_PAGE(new CarsPageCommand()),
    MOVE_CAR_CARD_PAGE(new CarCardPageCommand()),
    MOVE_USERS_PAGE(new UsersPageCommand()),
    MOVE_ORDERS_PAGE(new OrdersPageCommand()),
    MOVE_CREATE_CAR_PAGE(new CreateCarPageCommand()),
    MOVE_PAYMENT_PAGE(new PaymentPageCommand()),
    LOG_IN_USER(new LoginCommand()),
    LOG_OUT_USER(new LogoutCommand()),
    REGISTER_CLIENT(new RegisterClientCommand()),
    SWITCH_LOCALE(new SwitchLocaleCommand()),
    ACTIVATE_CLIENT(new ActivateClientCommand()),
    FIND_AVAILABLE_CARS(new FindAvailableCarsCommand()),
    FILTER_CARS(new FilterCarsCommand()),
    ORDER_CAR(new OrderCarCommand()),
    UPDATE_CAR_PROPERTY(new UpdateCarPropertyCommand()),
    ADD_CAR(new AddCarCommand()),
    FILTER_ORDERS(new FilterOrdersCommand()),
    FILTER_USERS(new FilterUsersCommand()),
    DECLINE_ORDER(new DeclineOrderCommand()),
    MAKE_ORDER_PAYMENT(new MakeOrderPaymentCommand()),
    UPDATE_CLIENT_STATUS(new UpdateClientStatusCommand()),
    UPDATE_ORDER_STATUS(new UpdateOrderStatusCommand()),
    PAGINATION(new PaginationCommand()),
    EMPTY(new EmptyCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public ActionCommand getCommand() {
        return command;
    }
}
