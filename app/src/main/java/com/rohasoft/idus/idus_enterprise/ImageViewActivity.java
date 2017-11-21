package com.rohasoft.idus.idus_enterprise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageViewActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        imageView= (ImageView) findViewById(R.id.cusView_imageView_full);

        String s=getIntent().getExtras().getString("img");
        Picasso.with(this).load("http://www.idusmarket.com/loan-app/admin/uploads/"+s).into(imageView);

    }
}
