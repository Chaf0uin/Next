package com.kerboocorp.next.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kerboocorp.next.R;
import com.kerboocorp.next.adapters.StuffAdapter;
import com.kerboocorp.next.adapters.StuffCalendarAdapter;
import com.kerboocorp.next.managers.StuffManager;
import com.kerboocorp.next.model.Stuff;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cgo on 20/03/2015.
 */
public class StuffCalendarFragment extends Fragment {

    @InjectView(R.id.stuffList)
    RecyclerView stuffListView;

    private Context context;
    private LinearLayoutManager linearLayoutManager;
    private StuffCalendarAdapter stuffCalendarAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.fragment_stuff_calendar, container, false);
        Bundle args = getArguments();

        ButterKnife.inject(this, rootView);

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle argsCalendar = new Bundle();
        Calendar cal = Calendar.getInstance();
        argsCalendar.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        argsCalendar.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(argsCalendar);

//        FragmentTransaction t = getFragmentManager().beginTransaction();
//        t.replace(R.id.fragment_calendar, caldroidFragment);
//        t.commit();

//        FragmentTransaction t = context.().beginTransaction();
//        t.replace(R.id.fragment_calendar, caldroidFragment);
//        t.commit();

        FragmentTransaction t = ((ActionBarActivity)getActivity()).getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_calendar, caldroidFragment);
        t.commit();


        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                stuffCalendarAdapter.clearItemList();
                stuffCalendarAdapter.addItemList(StuffManager.getInstance().findStuffListByDate(date));
            }

            @Override
            public void onChangeMonth(int month, int year) {}

            @Override
            public void onLongClickDate(Date date, View view) {}

            @Override
            public void onCaldroidViewCreated() {}

        };

        caldroidFragment.setCaldroidListener(listener);



        List<Stuff> stuffList = StuffManager.getInstance().findStuffList();
        HashMap<Date, Integer> stuffMap = new HashMap<Date, Integer>();
        for (Stuff stuff : stuffList) {
            stuffMap.put(stuff.getExpirationDate(), stuff.getColor());
        }

        caldroidFragment.setBackgroundResourceForDates(stuffMap);
        caldroidFragment.refreshView();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        stuffListView.setLayoutManager(linearLayoutManager);
        stuffListView.setItemAnimator(new DefaultItemAnimator());

        stuffCalendarAdapter = new StuffCalendarAdapter(R.layout.list_item_stuff_calendar, getActivity(), getActivity());
        stuffListView.setAdapter(stuffCalendarAdapter);

        return rootView;
    }
}
