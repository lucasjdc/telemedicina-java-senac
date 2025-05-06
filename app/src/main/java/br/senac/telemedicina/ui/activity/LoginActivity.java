package br.senac.telemedicina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import br.senac.telemedicina.R;
import br.senac.telemedicina.dao.UsuarioDao;
import br.senac.telemedicina.database.DBHelper;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null)  {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin_activity_login);
        TextView linkCadastro  = findViewById(R.id.text_link_para_cadastro_activity_login);

        btnLogin.setOnClickListener(v -> {
            EditText edtNome  = findViewById(R.id.editText_nomeUsuario_activity_login);
            EditText edtSenha = findViewById(R.id.editText_senha_activity_login);

            String nome  = edtNome.getText().toString();
            String senha = edtSenha.getText().toString();
            String senhaHash = gerarHashSenhaSHA256(senha);
            DBHelper dbHelper = new DBHelper(this);
            UsuarioDao usuarioDao = new UsuarioDao(dbHelper);

            boolean loginValido = usuarioDao.verificarLogin(nome, senhaHash);

            if (loginValido) {
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Usuário ou senha inválidas", Toast.LENGTH_SHORT).show();
            }
        });

        linkCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
            startActivity(intent);
        });
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
            Log.e("HashSenha", "Erro ao gerar hash SHA-256", e);
            return senha; // como fallback (não recomendado, mas evita crash)
        }
    }
}
