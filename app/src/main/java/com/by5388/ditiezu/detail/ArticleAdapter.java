package com.by5388.ditiezu.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ArticleBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    private static final int VIEW_TYPE_EMPTY = 1;
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int COUNT_EMPTY_DATA = 1;
    private List<ArticleBean> mList;

    public ArticleAdapter() {
        mList = new ArrayList<>();
    }


    public void setList(List<ArticleBean> list) {
        mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList == null || mList.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        }
        return VIEW_TYPE_NORMAL;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DataBindingUtil.inflate(inflater, R.layout.item_article_list,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        // TODO: 2019/12/17 没有数据时，需要补充相应的数据
    }

    @Override
    public int getItemCount() {
        if (mList == null || mList.isEmpty()) {
            return COUNT_EMPTY_DATA;
        }
        return mList.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {
        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
