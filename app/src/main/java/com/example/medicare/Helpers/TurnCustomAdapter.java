package com.example.medicare.Helpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Models.TurnModel;
import com.example.medicare.R;
import com.example.medicare.TurnDetail;

import java.io.Serializable;
import java.util.ArrayList;

public class TurnCustomAdapter extends RecyclerView.Adapter<TurnCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<TurnModel> turns;

    public TurnCustomAdapter(Context context, ArrayList<TurnModel> turns) {
        this.context = context;
        this.turns = turns;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.turnId.setText(String.valueOf(turns.get(position).id));
        holder.turnDate.setText(String.valueOf(turns.get(position).date));
        holder.turnSpeciality.setText(String.valueOf(turns.get(position).medicalSpeciality));
        holder.turnTime.setText(String.valueOf(turns.get(position).time));
        holder.turnRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TurnDetail.class);
                TurnModel turn = turns.get(position);
                intent.putExtra("turn_id", turn.id);
                intent.putExtra("turn_id_user", turn.userId);
                intent.putExtra("turn_date", turn.date);
                intent.putExtra("turn_speciality", turn.medicalSpeciality);
                intent.putExtra("turn_time", turn.time);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return turns.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView turnId, turnDate, turnSpeciality, turnTime;
        LinearLayout turnRowLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            turnId = itemView.findViewById(R.id.turn_id_text);
            turnDate = itemView.findViewById(R.id.turn_id_date);
            turnSpeciality = itemView.findViewById(R.id.turn_speciality_txt);
            turnTime = itemView.findViewById(R.id.turn_time_txt);
            turnRowLayout = itemView.findViewById(R.id.turn_row);
        }
    }
}
