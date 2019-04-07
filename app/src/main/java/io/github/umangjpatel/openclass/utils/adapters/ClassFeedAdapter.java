package io.github.umangjpatel.openclass.utils.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.models.ClassFeed;
import io.github.umangjpatel.openclass.utils.viewholders.FeedViewHolder;

public class ClassFeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private List<ClassFeed> mClassFeedList;

    public ClassFeedAdapter(List<ClassFeed> classFeedList) {
        mClassFeedList = classFeedList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FeedViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.bind(mClassFeedList.get(position));
    }

    @Override
    public int getItemCount() {
        return mClassFeedList.size();
    }
}
