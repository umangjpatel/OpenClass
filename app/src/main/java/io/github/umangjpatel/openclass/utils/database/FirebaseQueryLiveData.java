package io.github.umangjpatel.openclass.utils.database;

import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {

    private final Query mQuery;
    private final MyValueEventListener mListener = new MyValueEventListener();
    private final Handler mHandler = new Handler();
    private boolean mListenerRemovePending = false;
    private final Runnable mRemoveListener = new Runnable() {
        @Override
        public void run() {
            mQuery.removeEventListener(mListener);
            mListenerRemovePending = false;
        }
    };

    public FirebaseQueryLiveData(DatabaseReference databaseReference) {
        mQuery = databaseReference;
    }

    public FirebaseQueryLiveData(Query query) {
        mQuery = query;
    }

//    public FirebaseQueryLiveData(Query query) {
//        mQuery = query;
//    }

    @Override
    protected void onActive() {
        if (mListenerRemovePending)
            mHandler.removeCallbacks(mRemoveListener);
        else
            mQuery.addValueEventListener(mListener);
        mListenerRemovePending = false;
    }

    @Override
    protected void onInactive() {
        mHandler.postDelayed(mRemoveListener, 2000);
        mListenerRemovePending = true;
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    }
}
