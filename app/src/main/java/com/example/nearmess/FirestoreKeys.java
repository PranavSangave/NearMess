/**
 *  AUTHOR : PRANAV SANGAVE
 *  CREATION DATE : 15/08/2023
 *  LAST EDITED BY : PRANAV SANGAVE
 *  LAST MODIFIED DATE : 15/08/2023
 *
 *  NOTE: PLEASE DON't MODIFY THIS FILE UNLESS AND UNTIL YOU WANT TO CHANGE DATABASE SCHEMA/STRUCTURE
 * **/

package com.example.nearmess;

public class FirestoreKeys {

    // DOCUMENT_IDS
    public final static String MESS_OWNERS = "MESS_OWNERS";
    public final static String END_USERS = "END_USERS";
    public final static String MESS_MENU = "MESS_MENU";
    public final static String AREA = "AREA"; // no keys (cause variable documents e.g kondhwa)
    public final static String MESS_BY_AREA = "MESS_BY_AREA"; // no keys
    public final static String WEEKLY_MESSES = "WEEKLY_MESSES"; // no keys

    // MESS_OWNER KEYS
    public final static String MESS_ID = "MESS_ID";
    public final static String MESS_OWNER_NAME ="MESS_OWNER_NAME";
    public final static String OWNER_EMAIL = "OWNER_EMAIL";
    public final static String OWNER_PASS = "OWNER_PASS";
    public final static String MESS_NAME = "MESS_NAME";
    public final static String MESS_ADDRESS = "MESS_ADDRESS";
    public final static String MESS_TYPED_ADDRESS = "MESS_TYPED_ADDRESS";
    public final static String MESS_MAP_LINK = "MESS_MAP_LINK";
    public final static String MESS_PHONE = "MESS_PHONE";
    public final static String SEATING_CAPACITY = "SEATING_CAPACITY";
    public final static String SERVICES = "SERVICES";
    public final static String MENU_TYPE = "MENU_TYPE";
    public final static String TIMINGS = "TIMINGS";
    public final static String MORNING = "MORNING";
    public final static String MORNING_START_TIME = "MORNING_START_TIME";
    public final static String MORNING_END_TIME = "MORNING_END_TIME";
    public final static String EVENING_START_TIME = "EVENING_START_TIME";
    public final static String EVENING_END_TIME = "EVENING_END_TIME";
    public final static String EVENING = "EVENING";
    public final static String IS_WEEKLY = "IS_WEEKLY";
    public final static String MESS_PHOTO = "MESS_PHOTO";
    public final static String AREA_NAME = "AREA_NAME";
    public final static String RATING = "RATING";
    public final static String TOTAL_RATING = "TOTAL_RATING";
    public final static String NUM_RATING = "NUM_RATING";
    public final static String MESS_STATUS = "MESS_STATUS";
    public final static String STREAKS = "STREAKS";

    // END_USER_KEYS
    public final static String END_USER_EMAIL = "END_USER_EMAIL";
    public final static String END_USER_NAME = "END_USER_NAME";
    public final static String END_USER_PASS = "END_USER_PASS";
    public final static String FAVOURITE_MESS_IDS = "FAVOURITE_MESS_IDS";
    public final static String COMPANION_MESS_ID = "COMPANION_MESS_ID";

    // MESS_MENU_KEYS
    public final static String DAILY = "DAILY";
    public final static String WEEKLY = "WEEKLY";
    public final static String MENU_STRING = "MENU_STRING";
    public final static String MEAL_TYPE = "MEAL_TYPE";
    public final static String LUNCH = "LUNCH";
    public final static String DINNER = "DINNER";
    public final static String COMMENTS = "COMMENTS";
    public final static String LIKES = "LIKES";
    public final static String DISLIKES = "DISLIKES";
    public final static String SPECIAL_MSG = "SPECIAL_MSG";
    public final static String CHARGES = "CHARGES";
    public final static String RICE_PLATE = "RICE_PLATE";
    public final static String NORMAL_PLATE = "NORMAL_PLATE";
    public static final String SUNDAY = "SUNDAY";
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";

    // LOCATION_KEYS
    public static final String LOCATION = "LOCATION";


}
