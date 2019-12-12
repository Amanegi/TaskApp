package com.aman.taskapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aman.taskapp.R;
import com.aman.taskapp.model.Product;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtId, txtName, txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.detail_image_view);
        txtId = findViewById(R.id.detail_text_view_id);
        txtName = findViewById(R.id.detail_text_view_name);
        txtDesc = findViewById(R.id.detail_text_view_desc);

        Product p = getIntent().getParcelableExtra(ListActivity.KEY_INTENT_PRODUCT);

        Picasso.get().load(p.getProductImage()).into(imageView);
        txtId.setText(p.getProductId().toString());
        txtName.setText(p.getProductName());
        txtDesc.setText(p.getProductDesc());

    }
}
