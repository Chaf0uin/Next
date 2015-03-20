package com.kerboocorp.next.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cgo on 6/03/2015.
 */
public class Stuff implements Serializable, Comparable<Stuff> {

    private Integer id;
    private String name;
    private String content;
    private Date expirationDate;
    private String address;
    private int color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Map<String, Long> getDateDifference(Date startDate, Date endDate){

        Map<String, Long> date = new HashMap<String, Long>();

        long different = endDate.getTime() - startDate.getTime();

        if (different < 0) {
            return date;
        } else {

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            date.put("days", elapsedDays);
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            date.put("hours", elapsedHours);
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            date.put("minutes", elapsedMinutes);
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;
            date.put("seconds", elapsedSeconds);

            return date;
        }

    }

    @Override
    public int compareTo(Stuff another) {
        return this.getExpirationDate().compareTo(another.getExpirationDate());
    }
}
