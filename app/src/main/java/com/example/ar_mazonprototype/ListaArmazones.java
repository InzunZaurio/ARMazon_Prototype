package com.example.ar_mazonprototype;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ListaArmazones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_armazones);

        // Datos de ejemplo para la lista
        String[] datos = {"Modelo 1 - ", "Modelo 2 - ", "Modelo 3 - ", "Modelo 4 - ", "Modelo 5 - ", "Modelo 6 - ", "Modelo 7 - ", "Modelo 8 - ", "Modelo 9 - ", "Modelo 10 - ", "Modelo 11 - ", "Modelo 12 - ", "Modelo 13 - "};

        // Crear un adaptador para la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);

        // Obtener la referencia de la ListView desde el diseño
        ListView listView = findViewById(R.id.ListaDeArmazones);

        // Asignar el adaptador a la ListView
        listView.setAdapter(adapter);

        // Manejar clics en los elementos de la lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = (String) parent.getItemAtPosition(position);
            Toast.makeText(ListaArmazones.this, "Clic en: " + item, Toast.LENGTH_SHORT).show();
        });
    }
}
