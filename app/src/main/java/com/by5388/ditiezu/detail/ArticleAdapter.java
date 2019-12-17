package com.by5388.ditiezu.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ArticleBean;
import com.by5388.ditiezu.databinding.ItemArticleListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    private static final int VIEW_TYPE_EMPTY = 1;
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int COUNT_EMPTY_DATA = 1;
    private List<ArticleBean> mList;

    ArticleAdapter() {
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
        if (VIEW_TYPE_EMPTY == viewType) {
            final View view = inflater.inflate(R.layout.item_article_list_empty, parent, false);
            return new EmptyHolder(view);
        }
        final ItemArticleListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_article_list, parent, false);
        return new ArticleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        // TODO: 2019/12/17 没有数据时，需要补充相应的数据
        if (mList == null || mList.isEmpty()) {
            return;
        }
        final ArticleBean articleBean = mList.get(position);
        holder.bind(articleBean);

    }

    @Override
    public int getItemCount() {
        if (mList == null || mList.isEmpty()) {
            return COUNT_EMPTY_DATA;
        }
        return mList.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {
        private ItemArticleListBinding mBinding;

        ArticleHolder(@NonNull View itemView) {
            super(itemView);
        }

        ArticleHolder(@NonNull ItemArticleListBinding binding) {
            this(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(ArticleBean articleBean) {
            if (mBinding == null) {
                return;
            }
            mBinding.setArticle(new ArticleViewModel(articleBean));
        }
    }

    class EmptyHolder extends ArticleHolder {
        EmptyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
