package com.example.mentalmath;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

/*
 *  QuizzesFragment.java
 *  This class defines the functionality of the 'quizzesFragment' layout.
 */

public class QuizzesFragment extends Fragment {
    private TextView txtXP;
    private Button btnEasy;
    private Button btnMedium;
    private Button btnHard;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);// Connect to the layout file 'fragment_quiz.xml'.

        //Linking buttons and views
        txtXP = v.findViewById(R.id.txtXP);
        btnEasy = v.findViewById(R.id.btnEasy);
        btnMedium = v.findViewById(R.id.btnMedium);
        btnHard = v.findViewById(R.id.btnHard);



        //Getting the db
        ExperiencePointsDatabase db = Room.databaseBuilder(getContext(),ExperiencePointsDatabase.class,"MentalMathDatabase")
                .allowMainThreadQueries()
                .build();


        //Getting user XP object
        ExperiencePoints userXP = db.experiencePointsDao().findXPWithUserXP("user");

        //Getting user XP value
        final int currentUserXP = userXP.getXP();

        //Change textView to current XP value
        txtXP.setText(currentUserXP+" ");


        //onClick for buttons
        btnEasy.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
               if(currentUserXP >= 100)
               {
                   Fragment fr = new EasyQuizFragment();
                   FragmentChangeListener fc=(FragmentChangeListener)getActivity();
                   fc.replaceFragment(fr);
               }else{
                   Toast.makeText(getContext(), "You need more XP points to unlock this level :(", Toast.LENGTH_SHORT).show();
               }
            }

        });

        btnMedium.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(currentUserXP >= 500)
                {
                    Fragment fr = new MediumQuizFragment();
                    FragmentChangeListener fc=(FragmentChangeListener)getActivity();
                    fc.replaceFragment(fr);
                }else{
                    Toast.makeText(getContext(), "You need more XP points to unlock this level :(", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnHard.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(currentUserXP >= 1000)
                {
                    Fragment fr = new HardQuizFragment();
                    FragmentChangeListener fc=(FragmentChangeListener)getActivity();
                    fc.replaceFragment(fr);
                }else{
                    Toast.makeText(getContext(), "You need more XP points to unlock this level :(", Toast.LENGTH_SHORT).show();
                }
            }

        });




        return v;
    }


}