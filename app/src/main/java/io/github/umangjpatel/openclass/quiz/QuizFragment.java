package io.github.umangjpatel.openclass.quiz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.utils.adapters.QuizInfoAdapter;


public class QuizFragment extends Fragment {

    private static final int LOADING_QUIZZES = 1, LOADED_QUIZZES = 2;

    private QuizInfoAdapter mQuizInfoAdapter;

    private RecyclerView mQuizRecyclerView;
    private ProgressBar mProgressBar;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //addItems();
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        wireUpWidgets(view);
        QuizViewModel quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        updateUI(LOADING_QUIZZES);
        quizViewModel.getClassCatalogLiveData().observe(this, quizInfoList -> {
            if (quizInfoList != null && quizInfoList.size() > 0) {
                updateUI(LOADED_QUIZZES);
                mQuizInfoAdapter = new QuizInfoAdapter(quizInfoList);
                mQuizRecyclerView.setAdapter(mQuizInfoAdapter);
            }
        });
        //addItems();
        return view;

    }

    private void wireUpWidgets(View view) {
        mQuizRecyclerView = view.findViewById(R.id.quizzes_recycler_view);
        mProgressBar = view.findViewById(R.id.quiz_progress_bar);
    }

    private void updateUI(int layoutType) {
        switch (layoutType) {
            case LOADING_QUIZZES:
                mProgressBar.setVisibility(View.VISIBLE);
                mQuizRecyclerView.setVisibility(View.GONE);
                mQuizRecyclerView.setLayoutManager(null);
                break;
            case LOADED_QUIZZES:
                mProgressBar.setVisibility(View.GONE);
                mQuizRecyclerView.setVisibility(View.VISIBLE);
                mQuizRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
        }
    }

//    private void addItems() {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("questions").child("-Lbm6oet8xrKAntbLZiP");
//        Question question = new Question();
//        question.setKey(reference.push().getKey());
//        question.setQuestion("What is the full form of SGD?");
//        question.setAnswer(4);
//        question.setOption1("Semi Gradient Descent");
//        question.setOption2("Semi-automatic Gradient Descent");
//        question.setOption3("Simplified Gradient Descent");
//        question.setOption4("Stochastic Gradient Descent");
//        reference.child(question.getKey()).setValue(question);
//    }

}
