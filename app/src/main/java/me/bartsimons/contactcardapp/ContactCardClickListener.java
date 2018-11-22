package me.bartsimons.contactcardapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ContactCardClickListener implements ContactCardClickInterface {

    private Context context;
    private ContactCardItem[] cci;

    public ContactCardClickListener(Context context, ContactCardItem[] cci) {
        this.context = context;
        this.cci = cci;
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent i = new Intent(this.context, DetailActivity.class);

        i.putExtra("photo", cci[position].getImageResource());
        i.putExtra("name", cci[position].getName());
        i.putExtra("date", cci[position].getDate());
        i.putExtra("email", cci[position].getEmail());
        i.putExtra("phone", cci[position].getPhone());

        this.context.startActivity(i);
    }
}
