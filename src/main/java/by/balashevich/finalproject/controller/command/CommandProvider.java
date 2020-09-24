package by.balashevich.finalproject.controller.command;

public class CommandProvider {
    public static ActionCommand defineCommand(String commandName){
        return CommandType.valueOf(commandName.toUpperCase()).getCommand(); //todo may be add logic of Empty command
    }

    private CommandProvider(){}
}
