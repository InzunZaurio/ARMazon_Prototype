package com.example.ar_mazonprototype;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class ListaArmazones extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private ArFragment arFragment;
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

        // Configurar ARCore en la actividad
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        // Manejar clics en los elementos de la lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = (String) parent.getItemAtPosition(position);
            Toast.makeText(ListaArmazones.this, "Clic en: " + item, Toast.LENGTH_SHORT).show();

            // Agregar un armazón al ambiente AR
            addGlassesToScene();
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

    private void addGlassesToScene() {
        ModelRenderable.builder()
                .setSource(this, R.raw.glasses_model) // Agrega aquí el modelo 3D de tus armazones
                .build()
                .thenAccept(modelRenderable -> placeObject(arFragment, modelRenderable))
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Error al cargar el modelo 3D", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void placeObject(ArFragment arFragment, ModelRenderable modelRenderable) {
        // Obtén el punto donde se toca la pantalla
        HitResult hitResult = arFragment.getArSceneView().getArFrame().hitTest(arFragment.getArSceneView().getWidth() / 2f,
                arFragment.getArSceneView().getHeight() / 2f).get(0);

        if (hitResult != null) {
            // Agrega el modelo 3D al punto tocado
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setRenderable(modelRenderable);
            arFragment.getArSceneView().getScene().addChild(anchorNode);
        }
    }
}
