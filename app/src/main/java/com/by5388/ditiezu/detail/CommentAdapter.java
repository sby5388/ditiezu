package com.by5388.ditiezu.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ArticleCommentBean;
import com.by5388.ditiezu.databinding.ItemCommentNormalBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Administrator  on 2019/12/18.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private static final int VIEW_TYPE_COMMENT = 0;
    private static final int VIEW_TYPE_TITLE = 1;
    private ArticleDetailBean mDetailBean;


    public CommentAdapter() {

    }

    public void setCommentBeans(ArticleDetailBean detailBean) {
        mDetailBean = detailBean;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        if (VIEW_TYPE_TITLE == viewType) {
//            return
//        }

        final ItemCommentNormalBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_comment_normal, parent, false);
        return new CommentHolder(binding);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_TITLE;
        }
        return VIEW_TYPE_COMMENT;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        if (position == 0) {

        }
        final ArticleCommentBean bean = mDetailBean.getCommentBeans().get(position);
        holder.bind(bean);
    }

    @Override
    public int getItemCount() {
        if (mDetailBean == null) {
            return 0;
        }
        return mDetailBean.getCommentBeans().size();
    }

    public static class CommentHolder extends RecyclerView.ViewHolder {
        private ItemCommentNormalBinding mBinding;

        CommentHolder(@NonNull View itemView) {
            super(itemView);
        }

        CommentHolder(@NonNull ItemCommentNormalBinding binding) {
            this(binding.getRoot());
            mBinding = binding;
        }

        void bind(ArticleCommentBean bean) {
            if (mBinding == null) {
                return;
            }
            mBinding.imageViewIcon.setImageResource(R.drawable.ic_loading);
            mBinding.setComment(new CommentViewModel(bean));
            Glide.with(itemView).load(bean.mUserIconUrl).into(mBinding.imageViewIcon);
        }
    }

    public static class TitleHolder extends CommentHolder {
        public TitleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
