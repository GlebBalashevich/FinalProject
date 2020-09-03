package by.balashevich.finalproject.controller;

public class CommandProvider {
    public ActionCommand defineCommand(String commandName){
        return CommandType.valueOf(commandName.toUpperCase()).getCommand(); //todo may be add logic of Empty command
    }
}
