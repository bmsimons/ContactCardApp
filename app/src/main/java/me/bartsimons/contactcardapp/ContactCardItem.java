package me.bartsimons.contactcardapp;

import android.graphics.Bitmap;

public class ContactCardItem {
    private Bitmap imageResource;
    private String name;
    private String date;
    private String email;
    private String phone;

    public ContactCardItem(Bitmap imageResource, String name, String date, String email, String phone) {
        this.imageResource = imageResource;
        this.name = name;
        this.date = date;
        this.email = email;
        this.phone = phone;
    }

    public Bitmap getImageResource() {
        return this.imageResource;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getEmail() { return this.email; }

    public String getPhone() { return this.phone; }
}
