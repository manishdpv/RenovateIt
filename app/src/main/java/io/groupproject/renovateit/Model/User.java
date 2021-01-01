package io.groupproject.renovateit.Model;

public class User {
    private String Name;
    private String Password;
    private String Phone;

    public User(String name, String password) {
        Name = name;
        Password = password;
        //Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public User() {
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {

        Name = name;
    }

    public String getPassword() {

        return Password;
    }

    public void setPassword(String password) {

        Password = password;
    }
}
