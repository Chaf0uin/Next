package com.kerboocorp.next.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerboocorp.next.R;
import com.kerboocorp.next.adapters.StuffAdapter;
import com.kerboocorp.next.managers.StuffManager;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cgo on 20/03/2015.
 */
public class StuffListFragment extends Fragment {

    @InjectView(R.id.stuffList)
    RecyclerView stuffListView;

    private LinearLayoutManager linearLayoutManager;
    private StuffAdapter stuffAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.fragment_stuff_list, container, false);
        Bundle args = getArguments();

        ButterKnife.inject(this, rootView);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        stuffListView.setLayoutManager(linearLayoutManager);
        stuffListView.setItemAnimator(new DefaultItemAnimator());

        stuffAdapter = new StuffAdapter(R.layout.list_item_stuff, getActivity(), getActivity());
        stuffListView.setAdapter(stuffAdapter);

        stuffAdapter.addItemList(StuffManager.getInstance().findStuffList());

        return rootView;
    }
}
