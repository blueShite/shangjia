package com.huishengyuan.storemanage.FootList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huishengyuan.storemanage.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FootListFragment extends SupportFragment {

    public static FootListFragment newInstance() {
        Bundle args = new Bundle();
        FootListFragment fragment = new FootListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foot_list, container, false);
    }

}
