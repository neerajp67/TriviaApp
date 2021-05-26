package com.example.triviaapp.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.adapter.Adapter;
import com.example.triviaapp.R;
import com.example.triviaapp.database.DatabaseClass;
import com.example.triviaapp.models.Model;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {
    Context context;

    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> historyList;
    DatabaseClass databaseClass;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.history_recyclerView);

        historyList = new ArrayList<>();
        databaseClass = new DatabaseClass(context);

        fetchGameHistory();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new Adapter(historyList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    //read all data from database
    private void fetchGameHistory() {
        Cursor cursor = databaseClass.readAllGameData();
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No Data to Show!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                historyList.add(new Model(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            }
        }
    }
}
