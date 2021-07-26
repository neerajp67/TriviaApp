package com.example.triviaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.triviaapp.R;
import com.example.triviaapp.fragments.FragmentFirstQuestion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first fragment to be rendered -  FragmentFirstQuestion
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFirstQuestion()).commit();
        }
    }
}