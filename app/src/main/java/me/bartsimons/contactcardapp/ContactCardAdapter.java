package me.bartsimons.contactcardapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactCardAdapter extends ArrayAdapter<ContactCardItem> {
    private final Context context;
    private final ContactCardItem[] values;

    public ContactCardAdapter(Context context, ContactCardItem[] values) {
        super(context, -1, values);
        // super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contactcarditem, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.contactImageView);
        TextView nameView = (TextView) rowView.findViewById(R.id.contactName);
        TextView dateView = (TextView) rowView.findViewById(R.id.contactDate);

        Bitmap imageViewSource = values[position].getImageResource();
        String name = values[position].getName();
        String date = values[position].getDate();

        imageView.setImageBitmap(imageViewSource);
        // imageView.setImageResource(imageViewSource);
        nameView.setText(name);
        dateView.setText(date);

        return rowView;
    }
}
