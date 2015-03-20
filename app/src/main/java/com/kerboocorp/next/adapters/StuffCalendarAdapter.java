package com.kerboocorp.next.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kerboocorp.next.R;
import com.kerboocorp.next.activities.StuffActivity;
import com.kerboocorp.next.model.Stuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by cgo on 6/03/2015.
 */
public class StuffCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Stuff> stuffList;
    private int rowLayout;
    private Activity activity;
    private Context context;

    public StuffCalendarAdapter(int rowLayout, Context context, Activity activity) {
        this.stuffList = new ArrayList<Stuff>();
        this.rowLayout = rowLayout;
        this.activity = activity;
        this.context = context;
    }

    public void addItemList(List<Stuff> stuffList) {
        this.stuffList.addAll(stuffList);
        notifyDataSetChanged();
    }

    public void addItem(Stuff stuff) {
        stuffList.add(stuff);
        Collections.sort(stuffList);
        notifyDataSetChanged();
    }

    public void removeEvent(Stuff stuff) {
        stuffList.remove(stuff);
        notifyDataSetChanged();
    }

    public Stuff getItem(int position) {
        return stuffList.get(position);
    }

    public void clearItemList() {
        stuffList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (stuffList == null ? 0 : stuffList.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new StuffViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof StuffViewHolder) {

            final StuffViewHolder stuffViewHolder = (StuffViewHolder) viewHolder;
            Stuff stuff = stuffList.get(position);

            stuffViewHolder.id = stuff.getId();

            stuffViewHolder.name.setText(stuff.getName());

            stuffViewHolder.stuffLayout.setBackgroundColor(context.getResources().getColor(stuff.getColor()));

        }
    }

    public class StuffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public LinearLayout stuffLayout;
        public TextView name;

        public int id;

        public StuffViewHolder(View itemView) {
            super(itemView);

            stuffLayout = ButterKnife.findById(itemView, R.id.stuffLayout);
            name = ButterKnife.findById(itemView, R.id.nameTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, StuffActivity.class);
            intent.putExtra("id", id);
            StuffCalendarAdapter.this.activity.startActivity(intent);
        }
    }


}
