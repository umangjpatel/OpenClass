package io.github.umangjpatel.openclass.questions;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.github.umangjpatel.openclass.models.Question;
import io.github.umangjpatel.openclass.utils.database.FirebaseQueryLiveData;

public class QuestionsViewModel extends AndroidViewModel {

    private static final DatabaseReference QUIZ_QUESTIONS_REG =
            FirebaseDatabase.getInstance().getReference().child("questions");

    private FirebaseQueryLiveData mLiveData;

    private MediatorLiveData<List<Question>> mQuestionsListLiveData;

    public QuestionsViewModel(@NonNull Application application) {
        super(application);
        mQuestionsListLiveData = new MediatorLiveData<>();
    }

    void getQuestions(String quizKey) {
        mLiveData = new FirebaseQueryLiveData(QUIZ_QUESTIONS_REG.child(quizKey));
        addLiveDataSource();
    }

    private void addLiveDataSource() {
        mQuestionsListLiveData.addSource(mLiveData, dataSnapshot -> new Thread(() -> {
            if (dataSnapshot != null) {
                List<Question> questionList = new ArrayList<>();
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    Question question = questionSnapshot.getValue(Question.class);
                    questionList.add(question);
                }
                mQuestionsListLiveData.postValue(questionList);
            } else mQuestionsListLiveData.setValue(null);
        }).start());
    }

    LiveData<List<Question>> getQuestionsListLiveData() {
        return mQuestionsListLiveData;
    }
}
