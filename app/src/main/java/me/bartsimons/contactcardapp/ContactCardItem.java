package me.bartsimons.contactcardapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
