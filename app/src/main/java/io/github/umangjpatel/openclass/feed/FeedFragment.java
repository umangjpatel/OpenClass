package io.github.umangjpatel.openclass.feed;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.utils.adapters.ClassFeedAdapter;
import io.github.umangjpatel.openclass.utils.viewholders.ClassCatalogViewHolder;


public class FeedFragment extends Fragment {

    private String mClassKey;

    private ClassFeedAdapter mAdapter;

    private RecyclerView mFeedRecyclerView;


    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mClassKey = Objects.requireNonNull(getArguments()).getString(ClassCatalogViewHolder.CLASS_ID);
        //addItems();
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

//    private void addItems() {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("feed").child(mClassKey);
//        ClassFeed feed = new ClassFeed();
//        feed.setKey(reference.push().getKey());
//        feed.setFaculty("Sonal Rami");
//        feed.setDate(new Date().getTime());
//        feed.setContent("Dear Students,\n\n" +
//                        "Please find below Unit Test-II Syllabus from my portion:\n" +
//                        "Support Vector Machines.(Linear and Nonlinear) Building blocks of Deep Neural Networks, CNN, LeNet-5 Model, RNNs-Types of RNNS, Applications of RNN, Vanishing and Exploding Gradients problem, CNN vs RNN, GRU and LSTM, Difference between GRU and LSTM.\n" +
//                        "Link for GRU vs LSTM: https://datascience.stackexchange.com/questions/14581/when-to-use-gru-over-lstm\n" +
//                        "Also, Kindly find study materials for PPT of SVM and RNN and CNN Demo example on How does a machine look at an image?\n" +
//                        "I have attached Deep Learning ebook and lecture notes from deep learning book by Ian Goodfellow and Yoshua Bengio MIT Press which are easy to understand.\n\n" +
//                        "All the best!");
//        reference.child(feed.getKey()).setValue(feed);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFeedRecyclerView = view.findViewById(R.id.feed_recycler_view);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        NavigationUI.setupWithNavController(toolbar, Navigation.findNavController(view));
        FeedViewModel feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        feedViewModel.getFeed(mClassKey);

        feedViewModel.getFeedLiveData().observe(this, classFeeds -> {
            if (classFeeds != null && classFeeds.size() > 0) {
                mAdapter = new ClassFeedAdapter(classFeeds);
                mFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mFeedRecyclerView.setAdapter(mAdapter);
            }
        });
        //Toast.makeText(getActivity(), mClassKey, Toast.LENGTH_SHORT).show();
    }

}
