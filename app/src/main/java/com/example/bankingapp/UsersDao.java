package com.example.bankingapp;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert
    void insert(Users users);

    @Update
    void update(Users users);

    @Query("SELECT * FROM users_table")
    LiveData<List<Users>> getAllUsers();

}
