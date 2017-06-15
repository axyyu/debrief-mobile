package com.kaiamelung.debrief;

/**
 * Created by andrew on 6/10/17.
 */

public class ThreadGroup {
    public String longsum;
    public String shortsum;
    public String title;
    public String url;

    public ThreadGroup() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public ThreadGroup(String longsum, String shortsum, String title, String url) {
        this.longsum = longsum;
        this.shortsum = shortsum;
        this.title = title;
        this.url = url;
    }
}
