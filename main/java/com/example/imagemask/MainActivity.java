package com.example.imagemask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    MaskedImageView miv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miv = findViewById(R.id.imageView);
        miv.setTYPE(0);
        miv.setImageResource(R.drawable.bayrak);
        //miv.setResourseId(R.drawable.serif);

    }
}
