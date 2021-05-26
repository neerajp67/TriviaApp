package com.example.triviaapp.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.R;
import com.example.triviaapp.models.Model;

import java.time.Month;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    List<Model> historyList;

    //constructor
    public Adapter(List<Model> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //get recycler view layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recycler_layout, parent, false);

        //return view
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        //bind view item with data
        context = holder.itemView.getContext();
        final Model model = historyList.get(position);

        String dateTime = model.getDateTime();
        Log.d("date adapter", dateTime);
        String date = dateTime.substring(0,2);
        String timeT;
        String showTime;

        switch (date) {
            case "01":
                date = date + "st ";
                break;
            case "02":
                date = date + "nd ";
                break;
            case "03":
                date = date + "rd ";
                break;
            default:
                date = date + "th ";
                break;
        }

        int time = Integer.parseInt(dateTime.substring(11, 13));
        String timeMin = dateTime.substring(13, 16);
        if (time <=11){
            timeT = time + timeMin +" AM";
        } else{
            timeT = time + timeMin +" PM";
        }
        int monthNo = Integer.parseInt(dateTime.substring(3,5));
        Month month;
        if(Build.VERSION.SDK_INT >=26 ) {
            month = Month.of(monthNo);
            showTime = date + month + " " + timeT;

        } else{
            showTime = date + monthNo + " " + timeT;
        }
        holder.dateTime.setText("GAME " + model.getId() + " : " + showTime );
        holder.answer1.setText("Name: " + model.getAnswer1());
        holder.answer2.setText(model.getAnswer2());
        holder.answer3.setText(model.getAnswer3());
    }

    @Override
    public int getItemCount() {
        //return the size of the list (data from database)
        return historyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //declare view items
        TextView dateTime, answer1, answer2, answer3;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //find view items
            dateTime = itemView.findViewById(R.id.game_time);
            answer1 = itemView.findViewById(R.id.answer1);
            answer2 = itemView.findViewById(R.id.answer2);
            answer3 = itemView.findViewById(R.id.answer3);
        }
    }
}
