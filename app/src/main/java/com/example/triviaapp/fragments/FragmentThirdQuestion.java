package com.example.triviaapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.triviaapp.R;
import com.example.triviaapp.database.DatabaseClass;
import com.example.triviaapp.database.SharedPrefManager;

public class FragmentThirdQuestion extends Fragment {
    Context context;

//    String dateTime;
//    String answer1;
//    String answer2;
    String answer3 = "";

    CheckBox option1;
    CheckBox option2;
    CheckBox option3;
    CheckBox option4;
    Button submit;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate layout
        View view = inflater.inflate(R.layout.fragment_third_question, container, false);

        //get data passed by previous fragment
//        Bundle args = getArguments();
//        dateTime = args.getString("dateTime");
//        answer1 = args.getString("answer1");
//        answer2 = args.getString("answer2");

        //find the view items
        option1 = view.findViewById(R.id.checkbox1);
        option2 = view.findViewById(R.id.checkbox2);
        option3 = view.findViewById(R.id.checkbox3);
        option4 = view.findViewById(R.id.checkbox4);
        submit = view.findViewById(R.id.submitThirdAnswer);

        //onClick listener on submit button to submit the answer, and moves to next fragment
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option1.isChecked()) {
                    answer3 = answer3 + "White, ";
                }
                if (option2.isChecked()) {
                    answer3 = answer3 + "Yellow, ";
                }
                if (option3.isChecked()) {
                    answer3 = answer3 + "Orange, ";
                }
                if (option4.isChecked()) {
                    answer3 = answer3 + "Green";
                }

                //check if user has selected options
                if (!answer3.isEmpty()){
                    Log.d("checked", answer3);

                    //call addToDb()
                    addToDb(answer3);
                } else{
                    Toast.makeText(context, "Please select at-least one option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // returns view to be rendered
        return view;
    }

    private void addToDb(String answer3) {
//        Log.d("answers", dateTime + " " + answer1 + " " + answer2 + " " + answer3);

        //creating object of DatabaseClass class
        DatabaseClass db = new DatabaseClass(context);

//        storing answer3 locally
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        sharedPrefManager.putPref("answer3", answer3);
        //calling addGameResult of database DatabaseClass class
        //calling database class to add result to db
        db.addGameResult(sharedPrefManager.getPref("dateTime"),
                sharedPrefManager.getPref("answer1"),
                sharedPrefManager.getPref("answer2"),
                sharedPrefManager.getPref("answer3"));

        //move to next fragment
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentSummary = new FragmentSummary();

        //passing data to next fragment
//        Bundle args = new Bundle();
//        args.putString("dateTime", dateTime);
//        args.putString("answer1", answer1);
//        args.putString("answer2", answer2);
//        args.putString("answer3", answer3);
//        fragmentSummary.setArguments(args);

        //take to the next page
        fragmentTransaction.replace(R.id.fragment_container, fragmentSummary).commit();
    }
}
