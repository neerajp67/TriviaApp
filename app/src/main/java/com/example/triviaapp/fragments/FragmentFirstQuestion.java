package com.example.triviaapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.triviaapp.R;
import com.example.triviaapp.database.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FragmentFirstQuestion extends Fragment {
    Context context;

    private EditText firstAnswerEditText;
    private Button submit;

    String firstAnswer;
    String dateTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate layout
        View view = inflater.inflate(R.layout.fragment_first_question, container, false);

        //get current dateTime
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        dateTime = dateFormat.format(date);

        //find the view items
        firstAnswerEditText = view.findViewById(R.id.firstAnswerEditText);
        submit = view.findViewById(R.id.submitFirstAnswer);

        //onClick listener on submit button to submit the answer, and got to next question
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get user input in a string
                firstAnswer = firstAnswerEditText.getText().toString();

                //check if user input is empty
                if (!TextUtils.isEmpty(firstAnswer)) {
                    Log.i("name", firstAnswer);
                    //calling addToDb
                    addToDb(firstAnswer, dateTime);
                } else {
                    //set error on Edittext if input is empty
                    firstAnswerEditText.setError("Answer can't be empty!");
                }
            }
        });

        // returns view to be rendered
        return view;
    }

    private void addToDb(String firstAnswer, String dateTime) {
        Log.d("addToDb", firstAnswer + " " + dateTime);

        //move to next fragment

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentSecondQuestion = new FragmentSecondQuestion();

        //passing data to next fragment
//        Bundle args = new Bundle();
//        args.putString("dateTime", dateTime);
//        args.putString("firstAnswer", firstAnswer);
//        fragmentSecondQuestion.setArguments(args);

//        storing current date and answer1 locally
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        sharedPrefManager.putPref("dateTime", dateTime);
        sharedPrefManager.putPref("answer1", firstAnswer);

        //take to the next page
        fragmentTransaction.replace(R.id.fragment_container, fragmentSecondQuestion).commit();
    }
}
