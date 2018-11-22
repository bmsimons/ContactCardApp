package me.bartsimons.contactcardapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactCardAdapter extends RecyclerView.Adapter<ContactCardAdapter.ViewHolder> {
    private final Context context;
    private final ContactCardItem[] values;
    private final ContactCardClickInterface listener;

    public ContactCardAdapter(Context context, ContactCardItem[] values, ContactCardClickInterface listener) {
        // super(context, -1, values);
        this.context = context;
        this.values = values;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contactcarditem, viewGroup, false);
        final ViewHolder vHolder = new ViewHolder(rowView);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, vHolder.getAdapterPosition());
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ImageView imageView = (ImageView) viewHolder.rowView.findViewById(R.id.contactImageView);
        TextView nameView = (TextView) viewHolder.rowView.findViewById(R.id.contactName);
        TextView dateView = (TextView) viewHolder.rowView.findViewById(R.id.contactDate);

        Bitmap imageViewSource = values[i].getImageResource();
        String name = values[i].getName();
        String date = values[i].getDate();

        imageView.setImageBitmap(imageViewSource);
        nameView.setText(name);
        dateView.setText(date);
    }

    @Override
    public int getItemCount() {
        if (this.values == null) {
            return 0;
        } else {
            return this.values.length;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rowView;

        public ViewHolder(View rowView) {
            super(rowView);
            this.rowView = rowView;
        }
    }
}
