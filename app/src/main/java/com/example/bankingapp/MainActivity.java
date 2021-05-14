package com.example.bankingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int EDIT_USER_REQUEST = 1;

    private UsersViewModel usersViewModel;

    String userName;
    String userEmail;
    String userAccount;
    int userCurrentBalance;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final UsersAdapter adapter = new UsersAdapter();
        recyclerView.setAdapter(adapter);

        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        usersViewModel.getAllUsers().observe(this,new Observer<List<Users>>(){

            @Override
            public void onChanged(List<Users> users){
                //update RecyclerView
                adapter.setUsers(users);
            }
        });

        // Clicked on a user
        adapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Users users) {
                userName = users.getName();
                userEmail = users.getEmail();
                userAccount = users.getAccount_number();
                userCurrentBalance = users.getCurrent_balance();
                id = users.getId();


                Intent intent = new Intent(MainActivity.this,TransferMoney.class);
                intent.putExtra(TransferMoney.EXTRA_ID,id);
                intent.putExtra(TransferMoney.EXTRA_NAME,userName);
                intent.putExtra("currentEmail",userEmail);
                intent.putExtra(TransferMoney.EXTRA_ACCOUNT_NUMBER,userAccount);
                intent.putExtra("currentBalance",userCurrentBalance);
                startActivityForResult(intent, EDIT_USER_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_USER_REQUEST && resultCode == Activity.RESULT_OK)
        {

            assert data != null;
            int current_balance = data.getIntExtra(TransferMoney.EXTRA_CURRENT_AMOUNT, 1);

            if(id == -1)
            {
                Toast.makeText(this, "User can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            Users users = new Users(userName,userEmail,userAccount,current_balance);
            users.setId(id);
            usersViewModel.update(users);
            Toast.makeText(this,"Money Transferred Successfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Money not Transferred",Toast.LENGTH_SHORT).show();

        }
    }
}