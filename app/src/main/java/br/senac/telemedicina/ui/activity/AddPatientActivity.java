package br.senac.telemedicina.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.senac.telemedicina.R;
import br.senac.telemedicina.model.Paciente;
import br.senac.telemedicina.ui.recyclerview.adapter.PacientesAdapter;

public class AddPatientActivity extends AppCompatActivity {
    private List<Paciente> pacientesList = new ArrayList<>();
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
        pacientesAdapter = new PacientesAdapter(pacientesList);
        recyclerView.setAdapter(pacientesAdapter);

        addButton.setOnClickListener(v -> {
            Paciente paciente = new Paciente(
                    nomeEditText.getText().toString(),
                    idadeEditText.getText().toString(),
                    pressaoEditText.getText().toString(),
                    glicoseEditText.getText().toString(),
                    colesterolEditText.getText().toString()
            );

            pacientesList.add(paciente);
            pacientesAdapter.notifyItemInserted(pacientesList.size() - 1);
        });
    }
}