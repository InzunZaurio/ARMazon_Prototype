package com.example.ar_mazonprototype;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText editTextUsuario;
    private EditText editTextContraseña;
    private CheckBox checkBoxMostrarContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        checkBoxMostrarContraseña = findViewById(R.id.checkBoxMostrarContraseña);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar lógica de autenticación si es necesario

                // Iniciar la actividad MainActivity después del clic en el botón
                startActivity(new Intent(Login.this, MainActivity.class));
                finish(); // Cierra la actividad actual para que no vuelva atrás al presionar el botón "Atrás"
            }
        });

        checkBoxMostrarContraseña.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Mostrar u ocultar la contraseña según el estado del CheckBox
            int inputType = isChecked ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            editTextContraseña.setInputType(inputType);

            // Mover el cursor al final del texto para mantener la posición del cursor al cambiar el tipo de texto
            editTextContraseña.setSelection(editTextContraseña.length());
        });
    }
}
