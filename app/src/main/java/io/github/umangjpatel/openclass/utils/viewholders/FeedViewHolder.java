package io.github.umangjpatel.openclass.utils.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.models.ClassFeed;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView mFeedFacultyTextView, mFeedDateTextView, mFeedContentTextView;

    public FeedViewHolder(@NonNull LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.class_feed_item, parent, false));
        mFeedFacultyTextView = itemView.findViewById(R.id.feed_faculty_name_text_view);
        mFeedDateTextView = itemView.findViewById(R.id.feed_date_text_view);
        mFeedContentTextView = itemView.findViewById(R.id.feed_content_text_view);
    }

    public void bind(ClassFeed classFeed) {
        mFeedFacultyTextView.setText(classFeed.getFaculty());
        mFeedDateTextView.setText(formatDate(classFeed.getDate()));
        mFeedContentTextView.setText(classFeed.getContent());
    }

    private String formatDate(long date) {
        Date postDate = new Date(date);
        SimpleDateFormat formatter
                = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
        return formatter.format(postDate);
    }
}
