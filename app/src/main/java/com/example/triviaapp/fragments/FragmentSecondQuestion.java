package com.example.triviaapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.triviaapp.R;

public class FragmentSecondQuestion extends Fragment {
    Context context;
    private RadioGroup radio_group;
    private RadioButton radioButton;
    private Button submit;
    int answerIdSelected = 0;
    String dateTime;
    String answer1;
    String answer2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate layout
        View view = inflater.inflate(R.layout.fragment_second_question, container, false);

        //get data passed by previous fragment
        Bundle args = getArguments();
        assert args != null;
        dateTime = args.getString("dateTime");
        answer1 = args.getString("firstAnswer");

        //find the view items
        radio_group = view.findViewById(R.id.radio_group);
        submit = view.findViewById(R.id.submitSecondAnswer);

        //onClick listener on submit button to submit the answer, and go to next question
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get id of checked radio button's id
                answerIdSelected = radio_group.getCheckedRadioButtonId();
                try {
                    //find checked radio button by id
                    radioButton = view.findViewById(answerIdSelected);

                    //get text of checked radio button
                    answer2 = radioButton.getText().toString();

                    Log.d("answer 2", answer2);

                    //call addToDb()
                    addToDb(dateTime, answer1, answer2);
                } catch (Exception e) {
                    Toast.makeText(context, "Please select your answer!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // returns view to be rendered
        return view;
    }

    private void addToDb(String dateTime, String answer1, String answer2) {
        Log.d("answer", answer1 + " " + answer2 + " " + dateTime);

        //move to next fragment
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentThirdQuestion = new FragmentThirdQuestion();

        //passing data to next fragment
        Bundle args = new Bundle();
        args.putString("dateTime", dateTime);
        args.putString("answer1", answer1);
        args.putString("answer2", answer2);
        fragmentThirdQuestion.setArguments(args);

        //take to the next page
        fragmentTransaction.replace(R.id.fragment_container, fragmentThirdQuestion).commit();

    }
}
