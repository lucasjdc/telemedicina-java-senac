package br.senac.telemedicina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.senac.telemedicina.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Esconde a barra de título
        if (getSupportActionBar() != null)  {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin_activity_login);
        TextView linkCadastro  = findViewById(R.id.text_link_para_cadastro_activity_login);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });

        linkCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
            startActivity(intent);
        });


    }
}
