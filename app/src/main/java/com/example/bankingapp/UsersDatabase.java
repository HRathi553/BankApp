package com.example.bankingapp;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Users.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    private static UsersDatabase instance;

    public abstract UsersDao usersDao();

    public static synchronized UsersDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UsersDatabase.class, "users_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UsersDao usersDao;

        private PopulateDbAsyncTask(UsersDatabase db) {
            usersDao = db.usersDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            usersDao.insert(new Users("Ricky", "ricky0553@gmail.com", "XXXXXXX774", 9999));
            usersDao.insert(new Users("Vicky", "vicky443@gmail.com", "XXXXXXX992", 3421));
            usersDao.insert(new Users("Micky", "micky43@gmail.com", "XXXXXXX456", 6721));
            usersDao.insert(new Users("Nikki", "nikki7453@gmail.com", "XXXXXXX887", 8921));
            usersDao.insert(new Users("Sohan", "sohan946@gmail.com", "XXXXXXX949", 421));
            usersDao.insert(new Users("Rohan", "rohan88@gmail.com", "XXXXXXX124", 5422));
            usersDao.insert(new Users("Mohan", "mohan349@gmail.com", "XXXXXXX698", 4445));
            usersDao.insert(new Users("Reema", "reema952@gmail.com", "XXXXXX353", 2353));
            usersDao.insert(new Users("Seema", "seema634@gmail.com", "XXXXXXX698", 7054));
            usersDao.insert(new Users("Neema", "neema029@gmail.com", "XXXXXXX239", 3496));


            return null;
        }
    }
}
