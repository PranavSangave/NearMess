package com.example.nearmess;
/***
 * Author : Vasudev Raut
 *last edited By : Vasudev Raut
 *Date : 15-08-2025
 * Note :
 */
public class Users {

    String userId , name ,profile,email;

    public Users(String userId, String name, String profile,String email) {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
        this.email = email;
    }

    public Users() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
