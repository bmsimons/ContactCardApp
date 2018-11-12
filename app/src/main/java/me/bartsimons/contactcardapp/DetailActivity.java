package me.bartsimons.contactcardapp;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarcenterlayout);

        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        Bitmap image = (Bitmap) extras.get("photo");
        ImageView iv = (ImageView) findViewById(R.id.circleImageView);
        iv.setImageBitmap(image);

        String name = extras.getString("name");
        TextView tvName = (TextView) findViewById(R.id.nameTextView);
        tvName.setText(name);

        String date = extras.getString("date");
        TextView tvDate = (TextView) findViewById(R.id.dateTextView);
        tvDate.setText(date);

        String email = extras.getString("email");
        TextView tvEmail = (TextView) findViewById(R.id.emailTextView);
        tvEmail.setText(email);

        String phone = extras.getString("phone");
        TextView tvPhone = (TextView) findViewById(R.id.phoneTextView);
        tvPhone.setText(phone);
    }
}
