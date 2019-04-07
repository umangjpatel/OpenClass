package io.github.umangjpatel.openclass.utils.viewholders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.models.QuizInfo;

public class QuizInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String QUIZ_ID = "quiz_id_key";
    private AppCompatTextView mTitleTextView, mDifficultyTextView;
    private QuizInfo mQuizInfo;

    public QuizInfoViewHolder(@NonNull LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.quiz_info_list_item, parent, false));
        mTitleTextView = itemView.findViewById(R.id.quiz_title_text_view);
        mDifficultyTextView = itemView.findViewById(R.id.quiz_difficulty_text_view);
        itemView.setOnClickListener(this);
    }

    public void bind(QuizInfo quizInfo) {
        mQuizInfo = quizInfo;
        mTitleTextView.setText(quizInfo.getTitle());
        mDifficultyTextView.setText(String.format("Difficulty : %s", quizInfo.getDifficulty()));
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(QUIZ_ID, mQuizInfo.getKey());
        Navigation.findNavController(v).navigate(R.id.toQuestionsFragment, bundle);
    }
}
