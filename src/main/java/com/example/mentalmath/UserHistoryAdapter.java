package com.example.mentalmath;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserHistoryAdapter extends RecyclerView.Adapter<UserHistoryAdapter.UserHistoryViewHolder> {

    private List<Task> tasksToAdapt;

    public static class UserHistoryViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView userQuestion;

        public UserHistoryViewHolder(View v){
            super(v);
            view = v;
            userQuestion = v.findViewById(R.id.user_history_view);
        }

        public void bind (Task task){
            userQuestion.setText(task.getQuestion());
        }
    }

    //This method just creates the ViewHolder
    @NonNull
    @Override
    public UserHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.userhistory_viewholder, parent, false);

        UserHistoryViewHolder historyViewHolder = new UserHistoryViewHolder(view);
        return historyViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull UserHistoryViewHolder holder, int position)
    {
        Task taskAtPosition = tasksToAdapt.get(position);

        holder.bind(taskAtPosition);
    }

    @Override
    public int getItemCount(){return tasksToAdapt.size();}

}
