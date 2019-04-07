package io.github.umangjpatel.openclass.classes;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.github.umangjpatel.openclass.models.ClassInfo;
import io.github.umangjpatel.openclass.utils.database.FirebaseQueryLiveData;

public class ClassViewModel extends AndroidViewModel {

    private static final DatabaseReference CATALOG_REF =
            FirebaseDatabase.getInstance().getReference().child("classes");

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<List<ClassInfo>> mClassCatalogLiveData;

    public ClassViewModel(@NonNull Application application) {
        super(application);
        mClassCatalogLiveData = new MediatorLiveData<>();
        getCatalog();
    }

    void getCatalog() {
        mLiveData = new FirebaseQueryLiveData(CATALOG_REF);
        addLiveDataSource();
    }

    LiveData<List<ClassInfo>> getClassCatalogLiveData() {
        return mClassCatalogLiveData;
    }

    void searchCourse(String query) {
        mLiveData = new FirebaseQueryLiveData(generateQuery(query));
        addLiveDataSource();
    }

    private void addLiveDataSource() {
        mClassCatalogLiveData.addSource(mLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    List<ClassInfo> classInfoList = new ArrayList<>();
                    for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                        ClassInfo classInfo = courseSnapshot.getValue(ClassInfo.class);
                        classInfoList.add(classInfo);
                    }
                    mClassCatalogLiveData.postValue(classInfoList);
                }).start();
            } else mClassCatalogLiveData.setValue(null);
        });
    }

    private Query generateQuery(String query) {
        return FirebaseDatabase
                .getInstance()
                .getReference()
                .child("classes")
                .orderByChild("title")
                .startAt(query)
                .endAt(query + "\uf8ff");
    }
}
