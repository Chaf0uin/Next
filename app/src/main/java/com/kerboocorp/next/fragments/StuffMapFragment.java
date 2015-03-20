package com.kerboocorp.next.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerboocorp.next.R;
import com.kerboocorp.next.adapters.StuffAdapter;
import com.kerboocorp.next.managers.StuffManager;

import butterknife.ButterKnife;

/**
 * Created by cgo on 20/03/2015.
 */
public class StuffMapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stuff_map, container, false);
        Bundle args = getArguments();

        ButterKnife.inject(this, rootView);

        return rootView;
    }
}
