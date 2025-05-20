package br.senac.telemedicina.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import br.senac.telemedicina.R;
import br.senac.telemedicina.database.DBHelper;

public class ListPatientsActivity extends AppCompatActivity {

    private ListView listView;
    private DBHelper dbHelper;
    private ArrayList<HashMap<String, String>> listaPacientes;
    private Button btnVoltar;
    private ArrayAdapter<String> adapter; // Mova o ArrayAdapter para ser um membro da classe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        setContentView(R.layout.activity_listar_pacientes);

        listView = findViewById(R.id.listViewPacientes);
        btnVoltar = findViewById(R.id.bt_back_listar_pacientes);
        dbHelper = new DBHelper(this);

        listaPacientes = new ArrayList<>(); // Inicialize a lista aqui
        carregarPacientes();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            HashMap<String, String> paciente = listaPacientes.get(position);

            Intent intent = new Intent(this, EditDeletePatientActivity.class);
            intent.putExtra("id", paciente.get("id"));
            intent.putExtra("nome", paciente.get("nome"));
            intent.putExtra("idade", paciente.get("idade"));
            intent.putExtra("pressao_arterial", paciente.get("pressao_arterial")); // Se você adicionou esse campo
            intent.putExtra("glicose", paciente.get("glicose")); // Se você adicionou esse campo
            intent.putExtra("colesterol", paciente.get("colesterol")); // Se você adicionou esse campo
            startActivity(intent);
        });

        btnVoltar.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarPacientes(); // Recarrega os pacientes sempre que a activity volta ao primeiro plano
    }

    private void carregarPacientes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome, idade, pressao_arterial, glicose, colesterol FROM Paciente", null); // Selecione todos os campos

        ArrayList<String> nomes = new ArrayList<>();
        listaPacientes.clear(); // Limpe a lista existente antes de recarregar

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String nome = cursor.getString(1);
                String idade = cursor.getString(2);
                String pressao = cursor.getString(3); // Obtenha os outros campos
                String glicose = cursor.getString(4);
                String colesterol = cursor.getString(5);

                nomes.add(nome + " (" + idade + ")" + " P: "+ pressao + " G: " + glicose  + "mg/mL C: " + colesterol + " mg/mL");

                HashMap<String, String> paciente = new HashMap<>();
                paciente.put("id", id);
                paciente.put("nome", nome);
                paciente.put("idade", idade);
                paciente.put("pressao_arterial", pressao);
                paciente.put("glicose", glicose);
                paciente.put("colesterol", colesterol);

                listaPacientes.add(paciente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomes);
            listView.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(nomes);
            adapter.notifyDataSetChanged(); // Notifica o adapter que os dados foram alterados
        }
    }
}
