package com.example.bankingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int EDIT_USER_REQUEST = 1;

    private UsersViewModel usersViewModel;

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
                Intent intent = new Intent(MainActivity.this,TransferMoney.class);
                intent.putExtra(TransferMoney.EXTRA_ID,users.getId());
                intent.putExtra(TransferMoney.EXTRA_NAME,users.getName());
                intent.putExtra(TransferMoney.EXTRA_EMAIL,users.getEmail());
                intent.putExtra(TransferMoney.EXTRA_ACCOUNT_NUMBER,users.getAccount_number());
                intent.putExtra(TransferMoney.EXTRA_CURRENT_AMOUNT,users.getCurrent_balance());
                startActivityForResult(intent, EDIT_USER_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_USER_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(TransferMoney.EXTRA_ID, -1);

            if(id == -1){
                Toast.makeText(this, "User can't be updated", Toast.LENGTH_SHORT);
                return;
            }

            String name = data.getStringExtra(TransferMoney.EXTRA_NAME);
            String email = data.getStringExtra(TransferMoney.EXTRA_EMAIL);
            String account_number = data.getStringExtra(TransferMoney.EXTRA_ACCOUNT_NUMBER);
            int current_balance = data.getIntExtra(TransferMoney.EXTRA_CURRENT_AMOUNT, 1);

            Users users = new Users(name,email,account_number,current_balance);
            users.setId(id);
            usersViewModel.update(users);

            Toast.makeText(this,"Money Transferred",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Money not Transferred",Toast.LENGTH_SHORT).show();
        }
    }
}