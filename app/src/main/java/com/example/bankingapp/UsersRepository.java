package com.example.bankingapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UsersRepository {

    private UsersDao usersDao;
    private LiveData<List<Users>> allUsers;

    public UsersRepository(Application application){

        UsersDatabase database = UsersDatabase.getInstance(application);
        usersDao = database.usersDao();
        allUsers = usersDao.getAllUsers();
    }

    public void update(Users users){
        new UpdateUsersAsyncTask(usersDao).execute(users);
    }

    public LiveData<List<Users>>  getAllUsers(){
        return allUsers;
    }

    private static class UpdateUsersAsyncTask extends AsyncTask<Users, Void, Void> {
        private UsersDao usersDao;

        private UpdateUsersAsyncTask(UsersDao usersDao) {
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.update(users[0]);
            return null;
        }
    }
}
