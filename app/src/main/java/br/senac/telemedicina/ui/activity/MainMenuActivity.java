package br.senac.telemedicina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import br.senac.telemedicina.R;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_mainmenu);

        btnAdd = findViewById(R.id.btnRegistrarPaciente);
        btnEdit = findViewById(R.id.btnAtualizarDeletar);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, AddPatientActivity.class);
            startActivity(intent);
        });

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, ListPatientsActivity.class);
            startActivity(intent);
        });
    }
}
