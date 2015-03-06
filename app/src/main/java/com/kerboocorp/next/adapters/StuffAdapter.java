package com.kerboocorp.next.adapters;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by cgo on 6/03/2015.
 */
public class StuffAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Stuff> stuffList;
    private int rowLayout;
    private ActionBarActivity activity;

    public StuffAdapter(int rowLayout, ActionBarActivity activity) {
        this.stuffList = new ArrayList<Stuff>();
        this.rowLayout = rowLayout;
        this.activity = activity;
    }

    public void addItemList(List<Stuff> stuffList) {
        this.stuffList.addAll(stuffList);
        notifyDataSetChanged();
    }

    public void addItem(Stuff stuff) {
        stuffList.add(stuff);
        notifyDataSetChanged();
    }

    public void removeEvent(Stuff stuff) {
        stuffList.remove(stuff);
        notifyDataSetChanged();
    }

    public Stuff getItem(int position) {
        return stuffList.get(position);
    }

    public void clearPostList() {
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

            stuffViewHolder.position = position;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            stuffViewHolder.name.setText(stuff.getName());

            Map<String, Long> dateDifference = stuff.getDateDifference(new Date(), stuff.getExpirationDate());

            if (dateDifference.get("days") < 1) {
                stuffViewHolder.date.setText("dans " + String.valueOf(dateDifference.get("hours")) + "h");
            } else if (dateDifference.get("days") == 1) {
                SimpleDateFormat daysFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Map<String, Long> daysDifference = null;
                try {
                    daysDifference = stuff.getDateDifference(daysFormatter.parse(daysFormatter.format(new Date())), daysFormatter.parse(daysFormatter.format(stuff.getExpirationDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (daysDifference.get("days") == 1) {
                    stuffViewHolder.date.setText("demain");
                } else {
                    stuffViewHolder.date.setText("dans 2 jours");
                }


            } else {
                stuffViewHolder.date.setText("dans " + String.valueOf(dateDifference.get("days") + " jours"));
            }

            stuffViewHolder.content.setText(stuff.getContent());
            stuffViewHolder.name.setText(stuff.getName());
            stuffViewHolder.stuffLayout.setBackgroundColor(activity.getResources().getColor(stuff.getColor()));

        }
    }

    public class StuffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public LinearLayout stuffLayout;
        public TextView name;
        public TextView date;
        public TextView content;
        public ImageView map;

        public int position;

        public StuffViewHolder(View itemView) {
            super(itemView);

            stuffLayout = ButterKnife.findById(itemView, R.id.stuffLayout);
            name = ButterKnife.findById(itemView, R.id.nameTextView);
            date = ButterKnife.findById(itemView, R.id.dateTextView);
            content = ButterKnife.findById(itemView, R.id.contentTextView);
            map = ButterKnife.findById(itemView, R.id.mapImageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, StuffActivity.class);
            intent.putExtra("position", position);
            StuffAdapter.this.activity.startActivity(intent);
        }
    }


}
