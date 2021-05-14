package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TransferMoney extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.bankingapp.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.bankingapp.EXTRA_NAME";
    public static final String EXTRA_EMAIL =
            "com.example.bankingapp.EXTRA_EMAIL";
    public static final String EXTRA_ACCOUNT_NUMBER =
            "com.example.bankingapp.EXTRA_ACCOUNT_NUMBER";
    public static final String EXTRA_CURRENT_AMOUNT =
            "com.example.bankingapp.EXTRA_CURRENT_AMOUNT";


    private EditText editTextAddAmount;
    private TextView textViewTransferViewName;
    private TextView textViewTransferViewEmail;
    private TextView textViewTransferViewBalance;
    private TextView textViewTransferViewAccountNumber;
    private Button buttonTransferMoney;

    int current_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);

        textViewTransferViewName = findViewById(R.id.text_transfer_view_name);
        textViewTransferViewEmail = findViewById(R.id.text_transfer_view_email);
        textViewTransferViewBalance = findViewById(R.id.text_transfer_view_balance);
        textViewTransferViewAccountNumber = findViewById(R.id.text_transfer_view_account_number);
        editTextAddAmount = findViewById(R.id.edit_text_add_amount);
        buttonTransferMoney = findViewById(R.id.button_transfer_money);


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
//            setTitle("Edit Note");
            textViewTransferViewName.setText(intent.getStringExtra(EXTRA_NAME));
            textViewTransferViewEmail.setText(intent.getStringExtra("currentEmail"));
            textViewTransferViewAccountNumber.setText(intent.getStringExtra(EXTRA_ACCOUNT_NUMBER));
            current_balance = intent.getIntExtra("currentBalance", 0);
            textViewTransferViewBalance.setText("Rs: "+current_balance);

        }



        buttonTransferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonTransferMoney.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = getIntent().getIntExtra(EXTRA_ID,-1);

                        int added_amount=0;
                        if (!editTextAddAmount.getText().toString().equals(""))
                        {
                            added_amount = Integer.parseInt(editTextAddAmount.getText().toString());
                        }


                        current_balance = current_balance + added_amount;

                        Intent data = new Intent();

                        data.putExtra(EXTRA_CURRENT_AMOUNT,current_balance);

                        if(id != -1)
                        {
                            data.putExtra(EXTRA_ID, id);
                        }



                        setResult(RESULT_OK,data);
                        Toast.makeText(TransferMoney.this, "Send data Successfully"+id , Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}