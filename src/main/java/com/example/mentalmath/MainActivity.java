package com.example.mentalmath;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/*
 *  MainActivity.java
 *  In this class handles what happens on App Start.
 *  The App always starts with a 'HistoryFragment' loaded.
 */

// TODO: History: Log all past questions with the user answer in a database and show them in a RecyclerView in the 'History Tab' --> Patricks task.
// TODO: Develop quiz unlock, if a certain XP amount is reached. (Save current total XP in database) and check onButtonClick. --> Patricks task.
// TODO: Get Task formatting right again + make it larger

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                                                         // onCreate (Guarantee user-friendliness)
        setContentView(R.layout.activity_main);                                                     // Set the activity to be used.

        // Link Java Variables to Layout-Elements:
        BottomNavigationView bnv1 = findViewById(R.id.bnvMain);                                     // Link UI-Element (via it's ID "bnvMain") to the Java variable "bnv1".

        // Initialize 'home fragment' on App Start:
        swapFragment(new TrainingFragment());

        // BottomNavigationView:
        bnv1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigationTraining:                                                   // If the BNV-element with the id 'navigationTraining' (In 'navigation.xml') is pressed:
                        swapFragment(new TrainingFragment());                                       // place the 'TrainingFragment' into the 'fragmentContainer'.
                        break;
                    case R.id.navigationHistory:                                                       // If the BNV-element with the id 'navigationHome' (In 'navigation.xml') is pressed:
                        swapFragment(new HistoryFragment());                                           // place the 'HistoryFragment' into the 'fragmentContainer'.
                        break;
                    case R.id.navigationQuiz:                                                       // If the BNV-element with the id 'navigationQuiz' (In 'navigation.xml') is pressed:
                        swapFragment(new QuizzesFragment());                                        // place the 'QuizzesFragment' into the 'fragmentContainer'.
                        break;
                }
                return true;
            }
        });



    }

    /**
     * Helper method to change the fragment displayed in the activity.
     * @param fmt: instance of the fragment that is supposed to go into the slot.
     */
    public void swapFragment(Fragment fmt) {
        FragmentManager FM = getSupportFragmentManager();                                           // Instantiate a new FragmentManager object 'FM'. (Needed to to the add / replace / remove commands)
        FragmentTransaction fmtTransaction = FM.beginTransaction();
        fmtTransaction.replace(R.id.fragmentContainer, fmt);
        fmtTransaction.commit();                                                                    // Execute the action above.
    }

    @Override

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.toString());
        fragmentTransaction.commit();
}


}