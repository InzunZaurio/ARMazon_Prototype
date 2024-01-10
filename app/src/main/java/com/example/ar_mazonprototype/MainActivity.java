package com.example.ar_mazonprototype;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonList = findViewById(R.id.buttonList);
        Button buttonFavs = findViewById(R.id.buttonFavs);

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad ListaArmazones
                Intent intent = new Intent(MainActivity.this, ListaArmazones.class);
                startActivity(intent);
            }
        });

        buttonFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad ListaFavs
                Intent intent = new Intent(MainActivity.this, ListaFavs.class);
                startActivity(intent);
            }
        });
    }
}
