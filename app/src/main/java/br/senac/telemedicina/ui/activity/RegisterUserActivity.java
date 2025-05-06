package br.senac.telemedicina.ui.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.senac.telemedicina.R;
import br.senac.telemedicina.database.DBHelper;

public class RegisterUserActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonRegister;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        editTextUsername = findViewById(R.id.editTextUserName_activity_register_user);
        editTextPassword = findViewById(R.id.editTextPassword_activity_register_user);
        buttonRegister = findViewById(R.id.buttonRegister_activity_register_user);
        dbHelper = new DBHelper(this);
        buttonRegister.setOnClickListener(v  -> registerUser());
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome_usuario", username);
        String senhaHash = gerarHashSenhaSHA256(password);
        values.put("senha_hash", senhaHash);

        long id  = db.insert("Usuario", null, values);
        db.close();

        if (id != -1) {
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao cadastrar. Nome de usuário já existe?", Toast.LENGTH_SHORT).show();
        }
    }

    private String gerarHashSenhaSHA256(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return senha; // fallback (não recomendado)
        }
    }
}