package com.example.mentalmath;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.UUID;

import io.github.sidvenu.mathjaxview.MathJaxView;                                                   // Library to display MathML via MathJax in WebViews (https://github.com/sidvenu/MathJaxView)

/*
 *  TrainingFragment.java
 *  This class defines the functionality of the 'trainingFragment' layout.
 */

public class TrainingFragment extends Fragment {
    // Define Java Variables:
    private String API_URL = "https://studycounts.com/api/v1/algebra/linear-equations.json";        // API request URL (For random math tasks).
    private String API_URL_E = "https://studycounts.com/api/v1/algebra/linear-equations.json?difficulty=beginner";
    private String API_URL_M = "https://studycounts.com/api/v1/algebra/linear-equations.json?difficulty=intermediate";
    private String API_URL_H = "https://studycounts.com/api/v1/algebra/linear-equations.json?difficulty=advanced";
    private MathJaxView MJV1, MJV2, MJV3, MJV4, MJV5, MJV6;
    private TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8;
    private Task taskFromJSON;
    private RadioGroup rdG1;
    private int amount, XP = 0, right = 0, wrong = 0;
    private String config = "" +
            "MathJax.Hub.Config({" +
            "    extensions: ['fast-preview.js']," +
            "    messageStyle: 'none'," +
            "    \"fast-preview\": {" +
            "      disabled: false" +
            "    }," +
            "    CommonHTML: {" +
            "      linebreaks: { automatic: true, width: \"container\" }" +
            "    }," +
            "    tex2jax: {" +
            "      inlineMath: [ ['$','$'] ]," +
            "      displayMath: [ ['$$','$$'] ]," +
            "      processEscapes: true" +
            "    }," +
            "    TeX: {" +
            "      extensions: [\"file:///android_asset/MathJax/extensions/TeX/mhchem.js\"]," +
            "      mhchem: {legacy: false}"+
            "    }" +
            "});";
    String html_1 = "<html><head><style>body {text-align: left;}</style><script type=\"text/x-mathjax-config\">" + config + "</script><script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-MML-AM_CHTML\"></script></head><body>";
    String html_2 = "</body></html>";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training, container, false);         // Connect to the layout file 'fragment_training.xml'.
        MJV1 = v.findViewById(R.id.mathView);
        MJV2 = v.findViewById(R.id.mathViewR1);
        MJV3 = v.findViewById(R.id.mathViewR2);
        MJV4 = v.findViewById(R.id.mathViewR3);
        MJV5 = v.findViewById(R.id.mathViewR4);
        MJV6 = v.findViewById(R.id.mathViewR5);
        rdG1 = v.findViewById(R.id.rdgAnswer);
        txt1 = v.findViewById(R.id.txtOutcome);
        txt2 = v.findViewById(R.id.txtInstructions);
        txt3 = v.findViewById(R.id.txtDifficulty);
        txt4 = v.findViewById(R.id.txtCategory);
        txt5 = v.findViewById(R.id.txtTopic);
        txt6 = v.findViewById(R.id.txtXP);
        txt7 = v.findViewById(R.id.txtRight);
        txt8 = v.findViewById(R.id.txtWrong);



        // Volley API Request (Start):
        final RequestQueue queue = Volley.newRequestQueue(getContext());                            // Instantiate the RequestQueue.
        final StringRequest SR1 = new StringRequest(Request.Method.GET, API_URL_E,                    // Request a string response from the API URL.
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        // Display Task:
                        taskFromJSON = new Gson().fromJson(response, Task.class);                   // Create a 'task' object from the API JSON response.
                        MJV1.setText(taskFromJSON.getQuestion());                                   // Display the Task via MathJax.
                        txt2.setText(taskFromJSON.getInstruction());
                        txt3.setText(taskFromJSON.getDifficulty());
                        txt4.setText(taskFromJSON.getCategory());
                        txt5.setText(taskFromJSON.getTopic());
                        txt6.setText(Integer.toString(XP));
                        txt7.setText(Integer.toString(right));
                        txt8.setText(Integer.toString(wrong));

                        // Display the 5 possible answers (with custom formatting)                  // The html input is necessary to get the 'left-alignment'
                        MJV2.loadDataWithBaseURL("about:blank", html_1 + taskFromJSON.getChoices()[0] + html_2, "text/html", "UTF-8", "");
                        MJV3.loadDataWithBaseURL("about:blank", html_1 + taskFromJSON.getChoices()[1] + html_2, "text/html", "UTF-8", "");
                        MJV4.loadDataWithBaseURL("about:blank", html_1 + taskFromJSON.getChoices()[2] + html_2, "text/html", "UTF-8", "");
                        MJV5.loadDataWithBaseURL("about:blank", html_1 + taskFromJSON.getChoices()[3] + html_2, "text/html", "UTF-8", "");
                        MJV6.loadDataWithBaseURL("about:blank", html_1 + taskFromJSON.getChoices()[4] + html_2, "text/html", "UTF-8", "");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode == 429) {
                    Toast t = Toast.makeText(getContext(), "Max 10 requests per min!", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                } else {
                    System.out.println("API Error!");
                }
            }
        });
        queue.add(SR1);                                                                             // Add the request to the RequestQueue and execute the request.
        // Volley API Request (End)

        rdG1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {                                  // If the user selects one of the 5 radio buttons:
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {                         // checkedId is the id of the selected RadioButton.
                RadioButton selectedRB = group.findViewById(checkedId);                             // Currently selected Radiobutton.

                // Set XP change (according to difficulty)
                switch (taskFromJSON.getDifficulty()) {
                    case "Beginner":
                        amount = 10;
                        break;
                    case "Intermediate":
                        amount = 20;
                        break;
                    case "Advanced":
                        amount = 30;
                        break;
                }
                // database
                ExperiencePointsDatabase db = Room.databaseBuilder(getContext(),ExperiencePointsDatabase.class,"MentalMathDatabase")
                        .allowMainThreadQueries()
                        .build();

                //You must delete the database every time you run the application
                db.experiencePointsDao().insert(new ExperiencePoints("user", 0));

                // Handle correct / wrong answers:
                if (group.indexOfChild(selectedRB) == taskFromJSON.getCorrect_choice()) {           //
                    txt1.setText("Correct!\n+" + amount + " XP");
                    txt1.setTextColor(getResources().getColor(R.color.colorCorrect));
                    XP = XP + amount;

                    right++;
                } else {
                    txt1.setText("Wrong...\n-10 " + " XP");
                    txt1.setTextColor(getResources().getColor(R.color.colorWrong));
                    XP = XP - 10;

                    wrong++;
                }






                // Set the statistics:
                txt6.setText(Integer.toString(XP));
                txt7.setText(Integer.toString(right));
                txt8.setText(Integer.toString(wrong));

                // Reset to next task
                selectedRB.setChecked(false);
                //Inserting XP
                db.experiencePointsDao().updateXP("user",XP);
                loadNewTask(queue, SR1);
            }
        });
        return v;
    }

    public void loadNewTask(RequestQueue queue, StringRequest SR) {
        queue.add(SR);
    }
}