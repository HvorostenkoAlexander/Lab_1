package model;

/**
 * Created by User on 30.08.2016.
 */
public abstract  class AbstractUsers {
    int id;
    String login;
    String password;

    public String getLogin() {
        return login;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    public AbstractUsers(int id, String Login, String Password) {
        this.id = id;
        this.login = Login;
        this.password = Password;
    }
    public AbstractUsers() {

    }
}
