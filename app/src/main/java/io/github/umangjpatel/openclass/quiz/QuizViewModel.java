package io.github.umangjpatel.openclass.quiz;

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
import io.github.umangjpatel.openclass.models.QuizInfo;
import io.github.umangjpatel.openclass.utils.database.FirebaseQueryLiveData;

public class QuizViewModel extends AndroidViewModel {

    private static final DatabaseReference QUIZ_INFO_REF =
            FirebaseDatabase.getInstance().getReference().child("quiz_info");

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<List<QuizInfo>> mQuizCatalogLiveData;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        mQuizCatalogLiveData = new MediatorLiveData<>();
        getCatalog();
    }

    private void getCatalog() {
        mLiveData = new FirebaseQueryLiveData(QUIZ_INFO_REF);
        addLiveDataSource();
    }

    LiveData<List<QuizInfo>> getClassCatalogLiveData() {
        return mQuizCatalogLiveData;
    }


    private void addLiveDataSource() {
        mQuizCatalogLiveData.addSource(mLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    List<QuizInfo> quizInfoList = new ArrayList<>();
                    for (DataSnapshot quizSnapshot : dataSnapshot.getChildren()) {
                        QuizInfo quizInfo = quizSnapshot.getValue(QuizInfo.class);
                        quizInfoList.add(quizInfo);
                    }
                    mQuizCatalogLiveData.postValue(quizInfoList);
                }).start();
            } else mQuizCatalogLiveData.setValue(null);
        });
    }
}
