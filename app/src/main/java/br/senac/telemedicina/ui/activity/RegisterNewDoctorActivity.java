package br.senac.telemedicina.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import br.senac.telemedicina.R;
import br.senac.telemedicina.dao.MedicoDao;
import br.senac.telemedicina.model.Medico;
import br.senac.telemedicina.ui.recyclerview.adapter.MedicoAdapter;

public class RegisterNewDoctorActivity extends AppCompatActivity {

    private MedicoAdapter medicoAdapter;
    private MedicoDao medicoDao;
    private EditText nomeEditText;
    private EditText especialidadeEditText;
    private EditText crmEditText;
    private Button btnSave;
    private Button btnBack;
    private List<Medico> listaMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_adddoctor);

        // DAO
        medicoDao = new MedicoDao(this);

        // Inicializa lista e Adapter
        listaMedicos = new ArrayList<>(medicoDao.listarTodos());
        medicoAdapter = new MedicoAdapter(listaMedicos);

        // UI
        nomeEditText = findViewById(R.id.editText_nome_adddoctor);
        especialidadeEditText = findViewById(R.id.editText_especialidade_adddoctor);
        crmEditText = findViewById(R.id.editText_crm_adddoctor);
        btnSave = findViewById(R.id.btn_save_adddoctor);
        btnBack = findViewById(R.id.btn_back_adddoctor);

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView_medicos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(medicoAdapter);

        // Botão Salvar
        btnSave.setOnClickListener(v -> {
            String nome = nomeEditText.getText().toString().trim();
            String especialidade = especialidadeEditText.getText().toString().trim();
            String crm = crmEditText.getText().toString().trim();

            if (!nome.isEmpty() && !especialidade.isEmpty() && !crm.isEmpty()) {
                Medico novoMedico = new Medico(0, nome, especialidade, crm);
                medicoDao.inserir(novoMedico);

                // Atualiza apenas a lista e notifica o adapter
                listaMedicos.clear();
                listaMedicos.addAll(medicoDao.listarTodos());
                medicoAdapter.notifyDataSetChanged();

                // Limpar campos
                nomeEditText.setText("");
                especialidadeEditText.setText("");
                crmEditText.setText("");

                Toast.makeText(this, "Médico salvo com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

        // Botão Voltar
        btnBack.setOnClickListener(v -> finish());
    }
}

