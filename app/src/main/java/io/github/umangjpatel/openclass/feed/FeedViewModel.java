package io.github.umangjpatel.openclass.feed;

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
import io.github.umangjpatel.openclass.models.ClassFeed;
import io.github.umangjpatel.openclass.utils.database.FirebaseQueryLiveData;

public class FeedViewModel extends AndroidViewModel {

    private static final DatabaseReference CLASS_FEED_REF =
            FirebaseDatabase.getInstance().getReference().child("feed");

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<List<ClassFeed>> mFeedLiveData;

    public FeedViewModel(@NonNull Application application) {
        super(application);
        mFeedLiveData = new MediatorLiveData<>();
    }

    void getFeed(String classKey) {
        mLiveData = new FirebaseQueryLiveData(CLASS_FEED_REF.child(classKey));
        addLiveDataSource();
    }

    private void addLiveDataSource() {
        mFeedLiveData.addSource(mLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    List<ClassFeed> classFeedList = new ArrayList<>();
                    for (DataSnapshot feedSnapshot : dataSnapshot.getChildren()) {
                        ClassFeed classFeed = feedSnapshot.getValue(ClassFeed.class);
                        classFeedList.add(classFeed);
                    }
                    mFeedLiveData.postValue(classFeedList);
                }).start();
            } else mFeedLiveData.setValue(null);
        });
    }

    LiveData<List<ClassFeed>> getFeedLiveData() {
        return mFeedLiveData;
    }
}
