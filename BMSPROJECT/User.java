package BMSPROJECT;

public class User {
    private String UserName;
    private String Password;

    public User(String username,String password){
        this.UserName=username;
        this.Password=password;

    }
    public String getUsername() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}

