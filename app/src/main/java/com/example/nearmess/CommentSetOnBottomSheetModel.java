package com.example.nearmess;




/***
 * Author : Vasudev Raut
 *last edited By : Vasudev Raut
 *Date : 14-08-2023
 * Note :
 */
public class CommentSetOnBottomSheetModel {

    String user_name,comment,comment_time,emotion_happy,emotion_sad;
    String profile_pic;

    public CommentSetOnBottomSheetModel(String user_name, String comment, String comment_time) {
        this.user_name = user_name;
        this.comment = comment;
        this.comment_time = comment_time;
//        this.emotion_happy = emotion_happy;
//        this.emotion_sad = emotion_sad;
//        this.profile_pic = profile_pic;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getEmotion_happy() {
        return emotion_happy;
    }

    public void setEmotion_happy(String emotion_happy) {
        this.emotion_happy = emotion_happy;
    }

    public String getEmotion_sad() {
        return emotion_sad;
    }

    public void setEmotion_sad(String emotion_sad) {
        this.emotion_sad = emotion_sad;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
