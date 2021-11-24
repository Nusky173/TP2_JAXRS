package fr.istic.taa.jaxrs.Model;

public class LoginModel {

    public String login;
    public String password;

    public LoginModel() {
        super();
    }

    public LoginModel(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "login='" + this.login + '\'' + ',' +
                "password='" + this.password + '\'' +
                '}';
    }
}
