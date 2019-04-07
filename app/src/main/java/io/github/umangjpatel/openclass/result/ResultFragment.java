package io.github.umangjpatel.openclass.result;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import io.github.umangjpatel.openclass.R;

public class ResultFragment extends Fragment {


    public static final String QUIZ_SCORE = "quiz_score";

    private int mQuizScore;

    private AppCompatTextView mScoreTextView;
    private MaterialButton mNextButton;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mQuizScore = Objects.requireNonNull(getArguments()).getInt(QUIZ_SCORE);
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wireUpWidgets(view);
        displayScore();
        setListeners();
    }

    private void displayScore() {
        mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mQuizScore));
    }

    private void setListeners() {
        mNextButton.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
    }

    private void wireUpWidgets(View view) {
        mScoreTextView = view.findViewById(R.id.score_text_view);
        mNextButton = view.findViewById(R.id.next_button);
    }
}
