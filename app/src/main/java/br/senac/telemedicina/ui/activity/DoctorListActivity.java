package br.senac.telemedicina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.stream.Collectors;
import br.senac.telemedicina.R;
import br.senac.telemedicina.dao.MedicoDao;
import br.senac.telemedicina.model.Medico;

public class DoctorListActivity extends AppCompatActivity {

    private ListView listViewMedicos;
    private Button btnVoltar;
    private List<Medico> listaMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medicos);

        listViewMedicos = findViewById(R.id.listViewMedicos);
        btnVoltar = findViewById(R.id.btnVoltar);

        MedicoDao medicoDao = new MedicoDao(this);
        listaMedicos = medicoDao.listarTodos();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaMedicos.stream().map(m -> m.getNome() + " - " + m.getEspecialidade()).collect(Collectors.toList())
        );

        listViewMedicos.setAdapter(adapter);

        listViewMedicos.setOnItemClickListener((parent, view, position, id) -> {
            Medico medicoSelecionado = listaMedicos.get(position);

            Intent intent = new Intent(DoctorListActivity.this, ScheduleAppointmentActivity.class);
            intent.putExtra("medicoId", medicoSelecionado.getId());
            startActivity(intent);
        });

        btnVoltar.setOnClickListener(v -> finish());
    }
}
