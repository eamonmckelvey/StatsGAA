package com.example.statsgaa;


public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String foreignID;


    public User() {
    }

    public int getId() {

        return id;
    }

    public void setId() {

        this.id = id;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getForeignID() {
        return foreignID;
    }

    public void setForeignID(String foreignID) {
        this.foreignID = foreignID;
    }
}



