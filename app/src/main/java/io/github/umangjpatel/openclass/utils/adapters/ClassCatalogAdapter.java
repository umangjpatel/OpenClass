package io.github.umangjpatel.openclass.utils.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.models.ClassInfo;
import io.github.umangjpatel.openclass.utils.viewholders.ClassCatalogViewHolder;

public class ClassCatalogAdapter extends RecyclerView.Adapter<ClassCatalogViewHolder> {

    private List<ClassInfo> mClassInfoList;

    public ClassCatalogAdapter(List<ClassInfo> classInfoList) {
        mClassInfoList = classInfoList;
    }

    @NonNull
    @Override
    public ClassCatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ClassCatalogViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassCatalogViewHolder holder, int position) {
        holder.bind(mClassInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mClassInfoList.size();
    }
}
