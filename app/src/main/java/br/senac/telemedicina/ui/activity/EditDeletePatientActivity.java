package br.senac.telemedicina.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import br.senac.telemedicina.R;
import br.senac.telemedicina.database.DBHelper;

public class EditDeletePatientActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText nomeEditText;
    private EditText idadeEditText;
    private EditText pressaoEditText;
    private EditText glicoseEditText;
    private EditText colesterolEditText;
    private Button atualizarButton;
    private Button apagarButton;
    private Button historicoButton;
    private String pacienteId; // Para armazenar o ID do paciente que está sendo editado

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_editdeletepatient);

        // Inicializa DBHelper
        dbHelper = new DBHelper(this);

        // Obtém referências aos EditTexts
        nomeEditText = findViewById(R.id.editText_nomePaciente_activity_editdeletepatient);
        idadeEditText = findViewById(R.id.editText_idadePaciente_activity_editdeletepatient);
        pressaoEditText = findViewById(R.id.editText_pressaoArterial_activity_editdeletepatient);
        glicoseEditText = findViewById(R.id.editText_glicose_activity_editdeletepatient);
        colesterolEditText = findViewById(R.id.editText_colesterol_activity_editdeletepatient);

        // Obtém referências aos Buttons
        atualizarButton = findViewById(R.id.btn_atualizar_activity_editdeletepatient);
        apagarButton = findViewById(R.id.btn_apagar_activity_editdeletepatient);
        historicoButton = findViewById(R.id.btn_historico_activity_editdeletepatient);

        // Verifica se vieram dados via Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pacienteId = extras.getString("id");
            String nome = extras.getString("nome");
            String idade = extras.getString("idade");
            String pressao = extras.getString("pressao_arterial");
            String glicose = extras.getString("glicose");
            String colesterol = extras.getString("colesterol");

            nomeEditText.setText(nome);
            idadeEditText.setText(idade);
            pressaoEditText.setText(pressao);
            glicoseEditText.setText(glicose);
            colesterolEditText.setText(colesterol);
        }

        // Listener para o botão Atualizar
        atualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarPaciente();
            }
        });

        // Listener para o botão Apagar
        apagarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apagarPaciente();
            }
        });

        historicoButton.setOnClickListener(v -> {
            if (pacienteId != null) {
                Intent intent = new Intent(EditDeletePatientActivity.this, PatientHistoryActivity.class);
                intent.putExtra("id", pacienteId); // Envia o ID do Paciente
                startActivity(intent);
            } else {
                Toast.makeText(EditDeletePatientActivity.this, "ID do paciente não disponível", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void atualizarPaciente() {
        String nome = nomeEditText.getText().toString();
        String idadeStr = idadeEditText.getText().toString();
        String pressao = pressaoEditText.getText().toString();
        String glicose = glicoseEditText.getText().toString();
        String colesterol = colesterolEditText.getText().toString();

        Log.d("EditPatientActivity", "Tentando atualizar paciente com ID: " + pacienteId);
        Log.d("EditPatientActivity", "Nome: " + nome + ", Idade: " + idadeStr + ", Pressão: " + pressao + ", Glicose: " + glicose + ", Colesterol: " + colesterol);

        if (nome.isEmpty() || idadeStr.isEmpty()) {
            Toast.makeText(this, "Nome e idade são obrigatórios", Toast.LENGTH_SHORT).show();
            Log.w("EditPatientActivity", "Campos obrigatórios (nome ou idade) não preenchidos.");
            return;
        }

        int idade = Integer.parseInt(idadeStr);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("idade", idade);
        values.put("pressao_arterial", pressao);
        values.put("glicose", glicose);
        values.put("colesterol", colesterol);

        int rowsAffected = db.update("Paciente", values, "_id = ?", new String[]{pacienteId});
        Log.d("EditPatientActivity", "Número de linhas afetadas na atualização: " + rowsAffected);
        db.close();

        if (rowsAffected > 0) {
            Toast.makeText(this, "Paciente atualizado com sucesso", Toast.LENGTH_SHORT).show();
            Log.i("EditPatientActivity", "Paciente com ID " + pacienteId + " atualizado com sucesso.");
            finish(); // Volta para a ListPatientsActivity
        } else {
            Toast.makeText(this, "Erro ao atualizar paciente", Toast.LENGTH_SHORT).show();
            Log.e("EditPatientActivity", "Erro ao atualizar paciente com ID " + pacienteId + ".");
        }
    }

    private void apagarPaciente() {
        Log.d("EditPatientActivity", "Tentando apagar paciente com ID: " + pacienteId);
        if (pacienteId != null) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int rowsDeleted = db.delete("Paciente", "_id = ?", new String[]{pacienteId});
            Log.d("EditPatientActivity", "Número de linhas deletadas: " + rowsDeleted);
            db.close();

            if (rowsDeleted > 0) {
                Toast.makeText(this, "Paciente apagado com sucesso", Toast.LENGTH_SHORT).show();
                Log.i("EditPatientActivity", "Paciente com ID " + pacienteId + " apagado com sucesso.");
                finish(); // Volta para a ListPatientsActivity
            } else {
                Toast.makeText(this, "Erro ao apagar paciente", Toast.LENGTH_SHORT).show();
                Log.e("EditPatientActivity", "Erro ao apagar paciente com ID " + pacienteId + ".");
            }
        } else {
            Toast.makeText(this, "ID do paciente não encontrado", Toast.LENGTH_SHORT).show();
            Log.w("EditPatientActivity", "Tentativa de apagar paciente sem um ID válido.");
        }
    }
}