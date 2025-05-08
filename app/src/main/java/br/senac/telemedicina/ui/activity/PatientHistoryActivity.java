package br.senac.telemedicina.ui.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import br.senac.telemedicina.R;
import br.senac.telemedicina.database.DBHelper;

public class PatientHistoryActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private String pacienteId;
    private LinearLayout historicoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_patient_history);

        pacienteId = getIntent().getStringExtra("id");
        if (pacienteId == null) {
            Toast.makeText(this, "Paciente não especificado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        dbHelper = new DBHelper(this);

        EditText editPressao = findViewById(R.id.editTextPressao);
        EditText editGlicose = findViewById(R.id.editTextGlicose);
        EditText editColesterol = findViewById(R.id.editTextColesterol);
        Button buttonSalvar = findViewById(R.id.buttonSalvarHistorico);
        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        historicoContainer = findViewById(R.id.historicoContainer);

        carregarHistorico();

        buttonSalvar.setOnClickListener(v -> {
            String pressao = editPressao.getText().toString();
            String glicose = editGlicose.getText().toString();
            String colesterol = editColesterol.getText().toString();

            if (pressao.isEmpty() && glicose.isEmpty() && colesterol.isEmpty()) {
                Toast.makeText(this, "Preencha pelo menos um campo", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("paciente_id", pacienteId);
            values.put("pressao_arterial", pressao);
            values.put("glicose", glicose);
            values.put("colesterol", colesterol);

            long resultado = db.insert("HistoricoPaciente", null, values);
            if (resultado != -1) {
                Toast.makeText(this, "Histórico salvo", Toast.LENGTH_SHORT).show();
                editPressao.setText("");
                editGlicose.setText("");
                editColesterol.setText("");
                carregarHistorico();
            } else {
                Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
            }
        });
        buttonVoltar.setOnClickListener(v -> finish());
    }

    private void carregarHistorico() {
        historicoContainer.removeAllViews();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT data_registro, pressao_arterial, glicose, colesterol FROM HistoricoPaciente WHERE paciente_id = ? ORDER BY data_registro DESC",
                new String[]{pacienteId}
        );

        while (cursor.moveToNext()) {
            String data = cursor.getString(0);
            String pressao = cursor.getString(1);
            String glicose = cursor.getString(2);
            String colesterol = cursor.getString(3);

            TextView item = new TextView(this);
            item.setText(String.format("Data: %s\nPressão: %s\nGlicose: %s\nColesterol: %s\n", data, pressao, glicose, colesterol));
            item.setPadding(0, 8, 0, 8);
            historicoContainer.addView(item);
        }
        cursor.close();
    }
}
