package io.github.umangjpatel.openclass.utils.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.models.QuizInfo;
import io.github.umangjpatel.openclass.utils.viewholders.QuizInfoViewHolder;

public class QuizInfoAdapter extends RecyclerView.Adapter<QuizInfoViewHolder> {

    private List<QuizInfo> mQuizInfoList;

    public QuizInfoAdapter(List<QuizInfo> quizInfoList) {
        mQuizInfoList = quizInfoList;
    }

    @NonNull
    @Override
    public QuizInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new QuizInfoViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizInfoViewHolder holder, int position) {
        holder.bind(mQuizInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuizInfoList.size();
    }
}
