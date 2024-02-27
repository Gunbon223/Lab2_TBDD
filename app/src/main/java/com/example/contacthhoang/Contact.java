package com.example.contacthhoang;

public class Contact {
    private int id;
    private String name;
    private String phone;
    private boolean status;
    private static int autoId;
    public Contact(int id, String name, String phone){
        this.id=++autoId;
        this.name=name;
        this.phone=phone;
        this.status=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
