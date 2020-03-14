package com.example.mentalmath;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/*
 *  HistoryFragment.java
 *  This class defines the functionality of the 'homeFragment' layout.
 */

public class HistoryFragment extends Fragment {
    // Define Java Variables:


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);            // Connect to the layout file 'fragment_home.xml'.

        return v;
    }
}