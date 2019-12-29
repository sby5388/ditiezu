package com.by5388.ditiezu.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ArticleCommentBean;
import com.by5388.ditiezu.databinding.ItemCommentNormalBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator  on 2019/12/18.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private List<ArticleCommentBean> mCommentBeans;

    public CommentAdapter() {
        mCommentBeans = new ArrayList<>();
    }

    public void setCommentBeans(List<ArticleCommentBean> commentBeans) {
        mCommentBeans = commentBeans;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemCommentNormalBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_comment_normal, parent, false);
        return new CommentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        final ArticleCommentBean bean = mCommentBeans.get(position);
        holder.bind(bean);
    }

    @Override
    public int getItemCount() {
        return mCommentBeans.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        private final ItemCommentNormalBinding mBinding;

        public CommentHolder(@NonNull ItemCommentNormalBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(ArticleCommentBean bean) {
            mBinding.imageViewIcon.setImageResource(R.drawable.ic_loading);
            mBinding.setComment(new CommentViewModel(bean));
            Glide.with(itemView).load(bean.mUserIconUrl).into(mBinding.imageViewIcon);
        }
    }
}
