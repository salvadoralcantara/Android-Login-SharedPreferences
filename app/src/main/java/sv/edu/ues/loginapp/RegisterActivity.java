package sv.edu.ues.loginapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsuario, edtPassword, edtConfirmPassword, edtEmail;
    Button btnGuardar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtEmail = findViewById(R.id.edtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnGuardar.setOnClickListener(view -> {
            String usuario = edtUsuario.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirmPassword = edtConfirmPassword.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();

            // Validaciones
            if (usuario.length() < 3) {
                edtUsuario.setError("Debe tener al menos 3 caracteres");
                return;
            }

            if (password.length() < 5 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
                edtPassword.setError("Debe tener al menos 5 caracteres alfanuméricos");
                return;
            }

            if (!password.equals(confirmPassword)) {
                edtConfirmPassword.setError("Las contraseñas no coinciden");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setError("Correo inválido");
                return;
            }

            // Guardar en SharedPreferences
            SharedPreferences prefs = getSharedPreferences("usuarioPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("usuario", usuario);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

            // Limpiar campos
            edtUsuario.setText("");
            edtPassword.setText("");
            edtConfirmPassword.setText("");
            edtEmail.setText("");
        });

        btnRegresar.setOnClickListener(view -> finish());
    }
}
