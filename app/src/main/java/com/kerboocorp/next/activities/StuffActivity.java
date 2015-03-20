package com.kerboocorp.next.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kerboocorp.next.R;
import com.kerboocorp.next.managers.StuffManager;
import com.kerboocorp.next.model.Stuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cgo on 6/03/2015.
 */
public class StuffActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.stuffLayout)
    LinearLayout stuffLayout;
    @InjectView(R.id.nameTextView)
    TextView name;
    @InjectView(R.id.dateTextView)
    TextView date;
    @InjectView(R.id.contentTextView)
    TextView content;
    @InjectView(R.id.mapImageView)
    ImageView map;

    private Stuff stuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff);

        ButterKnife.inject(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stuff = StuffManager.getInstance().findStuff(getIntent().getIntExtra("id", 0));

        name.setText(stuff.getName());

        Map<String, Long> dateDifference = stuff.getDateDifference(new Date(), stuff.getExpirationDate());

        if (dateDifference.size() > 0) {
            if (dateDifference.get("days") < 1) {
                date.setText("dans " + String.valueOf(dateDifference.get("hours")) + "h");
            } else if (dateDifference.get("days") == 1) {
                SimpleDateFormat daysFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Map<String, Long> daysDifference = null;
                try {
                    daysDifference = stuff.getDateDifference(daysFormatter.parse(daysFormatter.format(new Date())), daysFormatter.parse(daysFormatter.format(stuff.getExpirationDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (daysDifference.get("days") == 1) {
                    date.setText("demain");
                } else {
                    date.setText("dans 2 jours");
                }


            } else {
                date.setText("dans " + String.valueOf(dateDifference.get("days") + " jours"));
            }
        } else {
            date.setText("expiré");
        }


        content.setText(stuff.getContent());

        if (stuff.getAddress() != null) {
            map.setImageResource(R.drawable.map);
        }

        toolbar.setBackgroundColor(getResources().getColor(stuff.getColor()));
        stuffLayout.setBackgroundColor(getResources().getColor(stuff.getColor()));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return true;
    }
}
