package sv.edu.ues.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario, edtPassword;
    Button btnIngresar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnSalir = findViewById(R.id.btnSalir);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Login");
        }

        // Botón Ingresar
        btnIngresar.setOnClickListener(view -> {
            String usuario = edtUsuario.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("usuarioPrefs", MODE_PRIVATE);
            String userGuardado = prefs.getString("usuario", "");
            String passGuardado = prefs.getString("password", "");

            if (usuario.equals(userGuardado) && password.equals(passGuardado)) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña inválidos", Toast.LENGTH_SHORT).show();
            }
        });

        btnSalir.setOnClickListener(v -> finishAffinity());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_registrar) {
            startActivity(new Intent(this, RegisterActivity.class));
            return true;
        } else if (id == R.id.menu_salir) {
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
