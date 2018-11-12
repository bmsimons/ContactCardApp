package me.bartsimons.contactcardapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

public class ContactListClickListener implements AdapterView.OnItemClickListener {
    private Context context;
    private ContactCardItem[] items;

    public ContactListClickListener(Context context, ContactCardItem[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this.context, DetailActivity.class);

        i.putExtra("photo", items[position].getImageResource());
        i.putExtra("name", items[position].getName());
        i.putExtra("date", items[position].getDate());
        i.putExtra("email", items[position].getEmail());
        i.putExtra("phone", items[position].getPhone());

        this.context.startActivity(i);
    }
}
