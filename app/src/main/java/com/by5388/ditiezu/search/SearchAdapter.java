package com.by5388.ditiezu.search;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.by5388.ditiezu.R;

/**
 * @author by5388  on 2019/12/28.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    public interface Callback {
        void onTouch(String query);
    }

    private Callback mCallback;

    public SearchAdapter(Callback callback) {
        mCallback = callback;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        DataBindingUtil.inflate(inflater, R.layout.item_search_history,parent,false);
        final View view = inflater.inflate(R.layout.item_search_history, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        holder.bind("item" + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class SearchHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;
        private String mText;

        SearchHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        if (!TextUtils.isEmpty(mText)) {
                            mCallback.onTouch(mText);
                        }
                    }
                }
            });
            mTextView = itemView.findViewById(R.id.textView_history);
        }

        void bind(String s) {
            this.mText = s;
            mTextView.setText(s);
        }

    }
}
