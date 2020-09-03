package by.balashevich.finalproject.model.dao;

public enum UserTableColumn {
    USERID("userid"),
    LOGIN("login"),
    PASSWORD("password"),
    NAME("name");

    private String columnName;

    UserTableColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName(){
        return columnName;
    }
}
