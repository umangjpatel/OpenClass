package io.github.umangjpatel.openclass.utils.viewholders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.models.ClassInfo;

public class ClassCatalogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public static final String CLASS_ID = "class_id_key";
    private static final String CLASS_LABEL = "class_label";

    private AppCompatTextView mClassNameTextView, mClassFacultyNameTextView;

    private ClassInfo mClassInfo;

    public ClassCatalogViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.catalog_class_list_item, parent, false));
        mClassNameTextView = itemView.findViewById(R.id.class_name_text_view);
        mClassFacultyNameTextView = itemView.findViewById(R.id.class_faculty_name_text_view);
        itemView.setOnClickListener(this);
    }

    public void bind(ClassInfo classInfo) {
        mClassInfo = classInfo;
        mClassNameTextView.setText(classInfo.getTitle());
        mClassFacultyNameTextView.setText(classInfo.getFaculty());
    }


    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(CLASS_ID, mClassInfo.getKey());
        bundle.putString(CLASS_LABEL, mClassInfo.getTitle());
        Navigation.findNavController(v).navigate(R.id.toFeedFragment, bundle);
    }
}
