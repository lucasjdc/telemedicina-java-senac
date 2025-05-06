package br.senac.telemedicina.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.senac.telemedicina.R;
import br.senac.telemedicina.model.Paciente;
import br.senac.telemedicina.ui.recyclerview.adapter.PacientesAdapter;

public class AddPatientActivity extends AppCompatActivity {
    private PacientesAdapter pacientesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient);

        EditText nomeEditText = findViewById(R.id.editText_nomePaciente_addpatient);
        EditText idadeEditText = findViewById(R.id.editText_idadePaciente_addpatient);
        EditText pressaoEditText = findViewById(R.id.editText_pressaoArterial_addpatient);
        EditText glicoseEditText = findViewById(R.id.editTextglicose_addpatient);
        EditText colesterolEditText = findViewById(R.id.editTextcolesterol_addpatient);
        Button addButton = findViewById(R.id.btnAddNovoPaciente);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPacientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pacientesAdapter);

        addButton.setOnClickListener(v -> {
            String nome = nomeEditText.getText().toString();
            int idade = Integer.parseInt(idadeEditText.getText().toString());
            String pressao = pressaoEditText.getText().toString();
            String glicose = glicoseEditText.getText().toString();
            String colesterol = colesterolEditText.getText().toString();

            Paciente paciente = new Paciente(nome, idade, pressao, glicose, colesterol);

            // Adiciona o paciente ao ViewModel

            Toast.makeText(this, "Paciente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        });
    }
}