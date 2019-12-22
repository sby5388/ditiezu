package com.by5388.ditiezu.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.ItemListCityBinding;

import java.util.List;

/**
 * @author by5388  on 2019/12/22.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {
    private List<CityBean> mCityBeans;


    public CityAdapter(List<CityBean> cityBeans) {
        mCityBeans = cityBeans;
    }

    public void setCityBeans(List<CityBean> cityBeans) {
        mCityBeans = cityBeans;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemListCityBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.item_list_city, parent, false);
        return new CityHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        final CityBean cityBean = mCityBeans.get(position);
        holder.bind(cityBean);

    }

    @Override
    public int getItemCount() {
        return mCityBeans.size();
    }

    class CityHolder extends RecyclerView.ViewHolder {
        private ItemListCityBinding mBinding;
        private static final String BASE_URL = "http://www.ditiezu.com/";

        CityHolder(@NonNull ItemListCityBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(CityBean cityBean) {
            mBinding.cityIcon.setImageResource(R.drawable.ic_add);
            mBinding.setCity(new CityViewModel(cityBean));
            final String iconUrl = cityBean.mIconUrl;
            Glide.with(mBinding.getRoot())
                    .load(BASE_URL + iconUrl)
                    .into(mBinding.cityIcon);
        }


    }
}
