package com.by5388.ditiezu.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.DataTools;
import com.by5388.ditiezu.bean.PageData;
import com.by5388.ditiezu.databinding.ItemPageBinding;

import java.util.List;

/**
 * @author by5388  on 2019/12/15.
 */
public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageHolder> {

    private final List<PageData> mDataList;
    private final Context mContext;

    public PageAdapter(Context context) {
        mContext = context;
        mDataList = DataTools.getPageDataList();
    }

    @NonNull
    @Override
    public PageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemPageBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_page, parent, false);
        return new PageHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PageHolder holder, int position) {
        final PageData mainData = mDataList.get(position);
        holder.bind(mainData);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class PageHolder extends RecyclerView.ViewHolder {
        private ItemPageBinding mBinding;

        PageHolder(@NonNull ItemPageBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(PageData mainData) {
            mBinding.setPage(new PageViewModel(mainData, mContext));
            mBinding.executePendingBindings();
        }
    }
}
