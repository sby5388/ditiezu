package com.by5388.ditiezu.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentSearchBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/24.
 */
public class SearchFragment extends Fragment {
    private FragmentSearchBinding mBinding;
    private SearchAdapter mSearchAdapter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public SearchAdapter getSearchAdapter() {
        return mSearchAdapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchAdapter = new SearchAdapter(new SearchAdapter.Callback() {
            @Override
            public void onTouch(String query) {
                // TODO: 2019/12/29  设置搜索、
                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        mBinding.setFragment(this);
        {
            Button button0 = new Button(getContext());
            button0.setText("地铁");
            Button button1 = new Button(getContext());
            button1.setText("网刊");
            Button button2 = new Button(getContext());
            button2.setText("地铁族");
            mBinding.searchHot.addView(button0);
            mBinding.searchHot.addView(button1);
            mBinding.searchHot.addView(button2);
        }
        return mBinding.getRoot();
    }
    //http://www.ditiezu.com/search.php?mod=forum&searchid=899&orderby=lastpost&ascdesc=desc&searchsubmit=yes&kw=%C8%C4%C6%BD&mobile=yes


}
