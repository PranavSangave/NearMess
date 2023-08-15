package com.example.nearmess;
import java.util.List;

public class MessOwnerPojo {
    private String MESS_ID;
    private String EMAIL;
    private String PASSWORD;
    private String OWNER_NM;
    private String MESS_NM;
    private String TYPED_ADDR;
    private String MESS_MAP_LINK;
    private String MESS_PHONE;
    private String SEATING_CAPACITY;
    private String SERVICE1;
    private String SERVICE2;
    private String MENU_TYPE;
    private String MORNING_START_TIME;
    private String MORNING_END_TIME;
    private String EVENING_START_TIME;
    private String EVENING_END_TIME;
    private String IS_WEEKLY;

    // Default constructor
    public MessOwnerPojo() {
        // Empty constructor
    }

    // Getter methods
    public String getMESS_ID() {
        return MESS_ID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getOWNER_NM() {
        return OWNER_NM;
    }

    public String getMESS_NM() {
        return MESS_NM;
    }

    public String getTYPED_ADDR() {
        return TYPED_ADDR;
    }

    public String getMESS_MAP_LINK() {
        return MESS_MAP_LINK;
    }

    public String getMESS_PHONE() {
        return MESS_PHONE;
    }

    public String getSEATING_CAPACITY() {
        return SEATING_CAPACITY;
    }

    public String getSERVICE1() {
        return SERVICE1;
    }

    public String getSERVICE2() {
        return SERVICE2;
    }

    public String getMENU_TYPE() {
        return MENU_TYPE;
    }

    public String getMORNING_START_TIME() {
        return MORNING_START_TIME;
    }

    public String getMORNING_END_TIME() {
        return MORNING_END_TIME;
    }

    public String getEVENING_START_TIME() {
        return EVENING_START_TIME;
    }

    public String getEVENING_END_TIME() {
        return EVENING_END_TIME;
    }

    public String getIS_WEEKLY() {
        return IS_WEEKLY;
    }
}
