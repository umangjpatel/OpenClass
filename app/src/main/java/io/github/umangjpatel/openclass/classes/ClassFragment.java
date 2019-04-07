package io.github.umangjpatel.openclass.classes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.utils.adapters.ClassCatalogAdapter;


public class ClassFragment extends Fragment {

    private static final int LOADING_CLASSES = 1, LOADED_CLASSES = 2;

    private SearchView mCatalogSearchView;
    private RecyclerView mCatalogRecyclerView;
    private ProgressBar mProgressBar;

    private ClassCatalogAdapter mAdapter;

    private ClassViewModel mViewModel;

    public ClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        wireUpWidgets(view);
        mViewModel = ViewModelProviders.of(this).get(ClassViewModel.class);
        updateUI(LOADING_CLASSES);
        mViewModel.getClassCatalogLiveData().observe(this, classInfos -> {
            if (classInfos != null && classInfos.size() > 0) {
                updateUI(LOADED_CLASSES);
                mAdapter = new ClassCatalogAdapter(classInfos);
                mCatalogRecyclerView.setAdapter(mAdapter);
            }
        });
        setListeners();
        return view;
    }

    private void setListeners() {
        mCatalogSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.searchCourse(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.searchCourse(newText);
                return true;
            }
        });
        mCatalogSearchView.setOnCloseListener(() -> {
            mViewModel.getCatalog();
            return true;
        });
    }

    private void wireUpWidgets(View view) {
        mProgressBar = view.findViewById(R.id.progress_bar);
        mCatalogSearchView = view.findViewById(R.id.catalog_search_view);
        mCatalogRecyclerView = view.findViewById(R.id.catalog_recycler_view);
        //addItems();
    }


    //Toolbar class name
    //Faculty name, Date, Body. Links

    private void updateUI(int layoutType) {
        switch (layoutType) {
            case LOADING_CLASSES:
                mProgressBar.setVisibility(View.VISIBLE);
                mCatalogRecyclerView.setVisibility(View.GONE);
                mCatalogRecyclerView.setLayoutManager(null);
                break;
            case LOADED_CLASSES:
                mProgressBar.setVisibility(View.GONE);
                mCatalogRecyclerView.setVisibility(View.VISIBLE);
                mCatalogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
        }
    }

}
