package com.example.bankingapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class Users {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String email;
    private int current_balance;
    private String account_number;

    public Users(String name, String email, String account_number, int current_balance) {
        this.name = name;
        this.email = email;
        this.account_number = account_number;
        this.current_balance = current_balance;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public int getCurrent_balance(){
        return current_balance;
    }

    public String getAccount_number() {
        return account_number;
    }
}
