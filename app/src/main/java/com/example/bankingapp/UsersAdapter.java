package com.example.bankingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {
    private List<Users> users = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_item, parent, false);

        return new UsersHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        Users currentUser = users.get(position);
        holder.textViewName.setText(currentUser.getName());
        holder.textViewEmail.setText(currentUser.getEmail());
        holder.textViewAccountNumber.setText(currentUser.getAccount_number());
        holder.textViewCurrentBalance.setText(String.valueOf(currentUser.getCurrent_balance()));
        holder.textViewSymbol.setText("Rs.");
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<Users> users) {
        this.users = users;
        notifyDataSetChanged();
    }


    class UsersHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewEmail;
        private TextView textViewCurrentBalance;
        private TextView textViewAccountNumber;
        private TextView textViewSymbol;

        public UsersHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
            textViewCurrentBalance = itemView.findViewById(R.id.text_view_current_balance);
            textViewAccountNumber = itemView.findViewById(R.id.text_view_account_number);
            textViewSymbol = itemView.findViewById(R.id.text_view_symbol);

            //  Setting up on Click Listener for list items.
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(users.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Users users);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
