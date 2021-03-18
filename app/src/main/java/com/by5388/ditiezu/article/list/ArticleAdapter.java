package com.by5388.ditiezu.article.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ArticleBean;
import com.by5388.ditiezu.databinding.ItemArticleListBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_EMPTY = 1;
    private static final int VIEW_TYPE_ACTION = 2;
    private static final int COUNT_EMPTY_DATA = 1;
    private List<ArticleBean> mList;
    private PageChangCallback mCallback;
    private boolean mFirstPage = true;

    public ArticleAdapter() {
        mList = new ArrayList<>();
    }

    public void setFirstPage(boolean firstPage) {
        mFirstPage = firstPage;
    }

    public void setCallback(PageChangCallback callback) {
        mCallback = callback;
    }

    public void setList(List<ArticleBean> list) {
        mList = list;
        this.notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList == null || mList.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        }
        if (position == mList.size()) {
            return VIEW_TYPE_ACTION;
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
        if (VIEW_TYPE_ACTION == viewType) {
            final View view = inflater.inflate(R.layout.item_article_list_action, parent, false);
            return new ActionHolder(view);
        }
        final ItemArticleListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_article_list, parent, false);
        return new ArticleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        if (mList == null || mList.isEmpty()) {
            return;
        }
        if (position == mList.size()) {
            //show action buttons
            holder.bind(null);
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
        //add a item show Buttons
        return mList.size() + 1;
    }

    public interface PageChangCallback {
        void changePrePage();

        void changeNextPage();
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

        @Override
        void bind(ArticleBean articleBean) {
            //ignore
        }
    }

    class ActionHolder extends ArticleHolder implements View.OnClickListener {
        private final Button mButtonPre;
        private final Button mButtonNext;

        ActionHolder(@NonNull View itemView) {
            super(itemView);
            mButtonPre = itemView.findViewById(R.id.button_pre_page);
            mButtonNext = itemView.findViewById(R.id.button_next_page);
            mButtonPre.setOnClickListener(this);
            mButtonNext.setOnClickListener(this);
        }

        @Override
        void bind(ArticleBean articleBean) {
            mButtonPre.setVisibility(mFirstPage ? View.INVISIBLE : View.VISIBLE);
        }

        @Override
        public void onClick(View v) {
            if (mCallback == null) {
                return;
            }
            switch (v.getId()) {
                case R.id.button_pre_page:
                    mCallback.changePrePage();
                    break;
                case R.id.button_next_page:
                    mCallback.changeNextPage();
                    break;
                default:
                    break;
            }
        }
    }
}
