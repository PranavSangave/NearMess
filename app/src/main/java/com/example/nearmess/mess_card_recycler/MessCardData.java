package com.example.nearmess.mess_card_recycler;

public class MessCardData {

    String mess_name,mess_timing,mess_seating,mess_status,mess_rating;

    Boolean expandable;

    int mess_img;

    public MessCardData(String mess_name, String mess_timing, String mess_seating, String mess_status, String mess_rating, int mess_img) {
        this.mess_name = mess_name;
        this.mess_timing = mess_timing;
        this.mess_seating = mess_seating;
        this.mess_status = mess_status;
        this.mess_rating = mess_rating;
        this.mess_img = mess_img;
        this.expandable = false;
    }

    public String getMess_name() {
        return mess_name;
    }

    public void setMess_name(String mess_name) {
        this.mess_name = mess_name;
    }

    public String getMess_timing() {
        return mess_timing;
    }

    public void setMess_timing(String mess_timing) {
        this.mess_timing = mess_timing;
    }

    public String getMess_seating() {
        return mess_seating;
    }

    public void setMess_seating(String mess_seating) {
        this.mess_seating = mess_seating;
    }

    public String getMess_status() {
        return mess_status;
    }

    public void setMess_status(String mess_status) {
        this.mess_status = mess_status;
    }

    public String getMess_rating() {
        return mess_rating;
    }

    public void setMess_rating(String mess_rating) {
        this.mess_rating = mess_rating;
    }

    public int getMess_img() {
        return mess_img;
    }

    public void setMess_img(int mess_img) {
        this.mess_img = mess_img;
    }

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }
}
