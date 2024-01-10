package com.example.ar_mazonprototype;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ListaArmazones extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAMERA_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_armazones);

        // Datos de ejemplo para la lista
        String[] datos = {"Modelo 1 - Lentes Cuadrados #420", "Modelo 2 - Lentes Circulares #690", "Modelo 3 - Lentes Rectangulares #240",
                "Modelo 4 - Lentes Circulares #421 Blue", "Modelo 5 - Lentes Grandes Cuadrados No-Label", "Modelo 6 - Lentes Estrella Equipable",
                "Modelo 7 - Lentes Vista Cansada #70", "Modelo 8 - Lentes Vista Cansada #71", "Modelo 9 - Lentes Vista Cansada #1",
                "Modelo 10 - Gafas Pixel NO AUMENTO", "Modelo 11 - Gafas Goofy Ones NO AUMENTO", "Modelo 12 - Lentes Cuadrados #421",
                "Modelo 13 - Lentes Circulares #777 Crepa", "Modelo 14 - Lentes Rectangulares #1 SWVJ", "Modelo 15 - Lentes Cuadrados Paldeanos",
                "Modelo 16 - Gafas Prueba", "Modelo 17 - Lentes Paldeanos Escarlatas", "Modelo 18 - Lentes Paldeanos Púrpuras", "Modelo 19 - Lentes Paldeanos Turquesas",
                "Modelo 20 - Lentes Paldeanos Índigos", "Modelo 21 - Gafas Circulares Aumento Experimental", "Modelo 22 - TEXTO DE PRUEBA XD", "Modelo 23 - TEXTO DE PRUEBA XD",
                "Modelo 24 - TEXTO DE PRUEBA XD", "Modelo 25 - TEXTO DE PRUEBA XD", "Modelo 26 - TEXTO DE PRUEBA XD"};

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

            // Verificar y solicitar permisos
            if (checkCameraPermission()) {
                dispatchTakePictureIntent();
            }
        });

        Button buttonListaArmazonesRegresar = findViewById(R.id.buttonListaArmazonesRegresar);
        buttonListaArmazonesRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad ListaArmazones
                Intent intent = new Intent(ListaArmazones.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Solicitar permiso
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION_CODE);
                return false;
            }
        }
        return true;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes abrir la cámara
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
