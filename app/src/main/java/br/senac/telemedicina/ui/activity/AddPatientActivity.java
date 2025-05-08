package br.senac.telemedicina.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.senac.telemedicina.R;
import br.senac.telemedicina.dao.PacientesDao;
import br.senac.telemedicina.database.DBHelper;
import br.senac.telemedicina.model.Paciente;
import br.senac.telemedicina.ui.recyclerview.adapter.PacientesAdapter;

public class AddPatientActivity extends AppCompatActivity {
    private PacientesAdapter pacientesAdapter;
    private PacientesDao pacientesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)  {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_addpatient);

        // Inicializa o DAO
        DBHelper dbHelper = new DBHelper(this);
        pacientesDao =  new PacientesDao(dbHelper);

        // Busca pacientes existentes no banco
        List<Paciente> listaPacientes = pacientesDao.buscaTodos();
        pacientesAdapter = new PacientesAdapter(listaPacientes);

        // Elementos de UI
        EditText nomeEditText = findViewById(R.id.editText_nomePaciente_addpatient);
        EditText idadeEditText = findViewById(R.id.editText_idadePaciente_addpatient);
        EditText pressaoEditText = findViewById(R.id.editText_pressaoArterial_addpatient);
        EditText glicoseEditText = findViewById(R.id.editTextglicose_addpatient);
        EditText colesterolEditText = findViewById(R.id.editTextcolesterol_addpatient);
        Button addButton = findViewById(R.id.btnAddNovoPaciente);
        Button returnButton = findViewById(R.id.btnVoltar_activity_addpatient);

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPacientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pacientesAdapter);

        // Botão de adicionar paciente
        addButton.setOnClickListener(v -> {
            String nome = nomeEditText.getText().toString();
            String idadeStr = idadeEditText.getText().toString();

            if (nome.isEmpty() || idadeStr.isEmpty()) {
                Toast.makeText(this, "Nome e idade são obrigatórios", Toast.LENGTH_SHORT).show();
                return;
            }

            int idade = Integer.parseInt(idadeStr);
            String pressao = pressaoEditText.getText().toString();
            String glicose = glicoseEditText.getText().toString();
            String colesterol = colesterolEditText.getText().toString();

            Paciente paciente = new Paciente(nome, idade, pressao, glicose, colesterol);

            // Adiciona no banco
            pacientesDao.adiciona(paciente);

            // Atualiza RecyclerView
            List<Paciente> pacientesAtualizados = pacientesDao.buscaTodos();
            pacientesAdapter.atualizarLista(pacientesAtualizados);
            Toast.makeText(this, "Paciente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            // Limpa os campos
            nomeEditText.setText("");
            idadeEditText.setText("");
            pressaoEditText.setText("");
            glicoseEditText.setText("");
            colesterolEditText.setText("");
        });

        returnButton.setOnClickListener(v -> finish());
    }

}