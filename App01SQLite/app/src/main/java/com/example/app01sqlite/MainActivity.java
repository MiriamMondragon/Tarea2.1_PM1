package com.example.app01sqlite;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = (Button) findViewById(R.id.btnMap);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        Button btnCombo = (Button) findViewById(R.id.btnCmb);

        btnCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityCombo.class);
                startActivity(intent);
            }
        });

        Button btnFoto = (Button) findViewById(R.id.btnTomarFoto);

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityFoto.class);
                startActivity(intent);
            }
        });

        Button btnVideo = (Button) findViewById(R.id.btnVideo);

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityVideo.class);
                startActivity(intent);
            }
        });
    }


    public void openIngresar(View view) {
        Intent intent = new Intent(this, ActivityIngresar.class);
        startActivity(intent);
    }

    public void openConsultar(View view) {
        Intent intent = new Intent(this, ActivityConsulta.class);
        startActivity(intent);
    }

    public void openListar(View view) {
        Intent intent = new Intent(this, ActivityListView.class);
        startActivity(intent);
    }
}