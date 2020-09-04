package by.balashevich.finalproject.controller.command;

public enum PageName {
    INDEX("/index.jsp"),
    LOGIN("/jsp/login.jsp"),
    ERROR("/jsp/error.jsp"),
    WELCOME("/jsp/welcome.jsp");  // TODO: 01.09.2020 May be rename Welcome page

    private String path;

    PageName(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
