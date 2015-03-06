package com.kerboocorp.next.managers;

import com.kerboocorp.next.R;
import com.kerboocorp.next.model.Stuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cgo on 6/03/2015.
 */
public class StuffManager {

    private List<Stuff> stuffList;

    private static StuffManager instance = new StuffManager();

    private StuffManager() {
        stuffList = new ArrayList<Stuff>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Stuff s1 = new Stuff();
        s1.setName("Soirée jeux Kevin");
        try {
            s1.setExpirationDate(formatter.parse("2015-03-06 19:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s1.setContent("- Apporter boissons \n- Apporter chips \n- Apporter jeux");
        s1.setAddress("llalala");
        s1.setColor(R.color.red);
        stuffList.add(s1);

        Stuff s2 = new Stuff();
        s2.setName("Sortie Oli & JB");
        try {
            s2.setExpirationDate(formatter.parse("2015-03-07 20:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s2.setContent("Infinity ?");
        s2.setColor(R.color.blue);
        stuffList.add(s2);

        Stuff s4 = new Stuff();
        s4.setName("Anniversaire Flo & Quentin");
        try {
            s4.setExpirationDate(formatter.parse("2015-03-08 12:30:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s4.setContent("");
        s4.setColor(R.color.green);
        stuffList.add(s4);

        Stuff s3 = new Stuff();
        s3.setName("Courses");
        try {
            s3.setExpirationDate(formatter.parse("2015-03-09 17:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s3.setContent("- Kellog's \n" +
                "- Viande \n" +
                "- Oeufs \n- Jus d'orange \n - Fruits ");
        s3.setColor(R.color.yellow);
        stuffList.add(s3);

        Stuff s5 = new Stuff();
        s5.setName("Théâtre Emi");
        try {
            s5.setExpirationDate(formatter.parse("2015-03-18 19:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s5.setContent("");
        s5.setColor(R.color.orange);
        stuffList.add(s5);

        Stuff s6 = new Stuff();
        s6.setName("Sortie Mario Party 10");
        try {
            s6.setExpirationDate(formatter.parse("2015-03-20 19:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s6.setContent("- Inviter potes");
        s6.setColor(R.color.white);
        stuffList.add(s6);
    }

    public static StuffManager getInstance() {
        return instance;
    }

    public List<Stuff> findStuffList() {
        return stuffList;
    }

    public Stuff findStuff(int position) {
        return stuffList.get(position);
    }
}
