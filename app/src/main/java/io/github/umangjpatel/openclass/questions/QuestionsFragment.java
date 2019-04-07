package io.github.umangjpatel.openclass.questions;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.models.Question;
import io.github.umangjpatel.openclass.result.ResultFragment;
import io.github.umangjpatel.openclass.utils.viewholders.QuizInfoViewHolder;


public class QuestionsFragment extends Fragment {

    private String mQuizKey;

    private NavController mNavController;

    private AppCompatTextView mQuestionTextView;
    private RadioGroup mRadioGroup;
    private AppCompatButton mSubmitButton;
    private AppCompatRadioButton mOption1Button, mOption2Button, mOption3Button, mOption4Button;

    private List<Question> mQuestions;
    private Question mQuestion;
    private int mIndex, mQuizScore;

    public QuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mQuizKey = Objects.requireNonNull(getArguments()).getString(QuizInfoViewHolder.QUIZ_ID);
        return inflater.inflate(R.layout.fragment_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        wireUpWidgets(view);
        QuestionsViewModel questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        questionsViewModel.getQuestions(mQuizKey);

        questionsViewModel.getQuestionsListLiveData().observe(this, questions -> {
            mQuestions = questions;
            mIndex = 0;
            mQuizScore = 0;
            mQuestion = mQuestions.get(mIndex);
            updateQuestion();
        });

        setListeners();
    }

    private void updateQuestion() {
        mQuestionTextView.setText(mQuestion.getQuestion());
        mOption1Button.setText(mQuestion.getOption1());
        mOption2Button.setText(mQuestion.getOption2());
        mOption3Button.setText(mQuestion.getOption3());
        mOption4Button.setText(mQuestion.getOption4());
    }

    private void wireUpWidgets(View view) {
        mQuestionTextView = view.findViewById(R.id.question_text_view);
        mSubmitButton = view.findViewById(R.id.submit_button);
        mRadioGroup = view.findViewById(R.id.radio_group);
        mOption1Button = view.findViewById(R.id.option_1_radio_button);
        mOption2Button = view.findViewById(R.id.option_2_radio_button);
        mOption3Button = view.findViewById(R.id.option_3_radio_button);
        mOption4Button = view.findViewById(R.id.option_4_radio_button);
    }

    private void setListeners() {
        mSubmitButton.setOnClickListener(v -> {
            int position = getSelectedOptionPosition();
            checkAnswer(position);
        });
    }

    private void checkAnswer(int position) {
        String toastMessage;
        if (position == mQuestion.getAnswer()) {
            toastMessage = "Correct";
            mQuizScore += 20;
        } else
            toastMessage = "Incorrect";
        Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_SHORT).show();
        nextQuestion();
    }

    private void nextQuestion() {
        mIndex++;
        if (mIndex < mQuestions.size()) {
            mQuestion = mQuestions.get(mIndex);
            updateQuestion();
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(ResultFragment.QUIZ_SCORE, mQuizScore);
            mNavController.navigate(R.id.toResultFragment, bundle);
        }
    }

    private int getSelectedOptionPosition() {
        int radioBtnID = mRadioGroup.getCheckedRadioButtonId();
        AppCompatRadioButton selectedRadioButton = mRadioGroup.findViewById(radioBtnID);
        selectedRadioButton.setChecked(false);
        return mRadioGroup.indexOfChild(selectedRadioButton) + 1;
    }

}
