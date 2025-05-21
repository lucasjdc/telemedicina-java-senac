package br.senac.telemedicina.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import br.senac.telemedicina.R;
import br.senac.telemedicina.dao.ConsultaDao;
import br.senac.telemedicina.dao.MedicoDao;
import br.senac.telemedicina.dao.PacientesDao;
import br.senac.telemedicina.database.DBHelper;
import br.senac.telemedicina.model.Consulta;
import br.senac.telemedicina.model.Medico;
import br.senac.telemedicina.model.Paciente;
import android.util.Log;

public class ScheduleAppointmentActivity extends AppCompatActivity {

    private EditText nomeMedicoEditText;
    private EditText nomePacienteEditText;
    private EditText idadePacienteEditText;
    private EditText dataConsultaEditText;
    private Button btnAgendar;
    private Button btnVoltar;
    private DBHelper dbHelper;
    private MedicoDao medicoDao;
    private PacientesDao pacientesDao;
    private ConsultaDao consultaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        // Inicializa as views
        nomeMedicoEditText = findViewById(R.id.editText_nomeMedico_schedule_appointment);
        nomePacienteEditText = findViewById(R.id.editText_nomePaciente_schedule_appointment);
        idadePacienteEditText = findViewById(R.id.editText_idadePaciente_schedule_appointment);
        dataConsultaEditText = findViewById(R.id.editText_data_schedule_appointment);
        btnAgendar = findViewById(R.id.btn_agendar_schedule_appointment);
        btnVoltar = findViewById(R.id.btn_back_schedule_appointment);

        // Inicializa o DBHelper e DAOs
        dbHelper = new DBHelper(this);
        medicoDao = new MedicoDao(this); // Ou new MedicoDao(dbHelper), se necessário
        pacientesDao = new PacientesDao(dbHelper);
        consultaDao = new ConsultaDao(this); // Ou new ConsultaDao(dbHelper)

        // Obtém o ID do médico passado via Intent
        int medicoId = getIntent().getIntExtra("medicoId", -1);

        // Preenche o nome do médico, se disponível
        if (medicoId != -1) {
            Medico medico = medicoDao.buscarPorId(medicoId);
            if (medico != null) {
                nomeMedicoEditText.setText(medico.getNome());
            }
        }

        // Define o listener do botão Agendar
        btnAgendar.setOnClickListener(v -> agendarConsulta());

        // Define o listener do botão Voltar
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void agendarConsulta() {
        Log.d("ScheduleAppointmentActivity", "Agendar consulta clicado!");

        // Obtém os dados dos campos de entrada
        String nomePaciente = nomePacienteEditText.getText().toString().trim();
        String idadeStr = idadePacienteEditText.getText().toString().trim();
        String dataConsulta = dataConsultaEditText.getText().toString().trim();

        // Valida os dados
        if (!validarDados(nomePaciente, idadeStr, dataConsulta)) {
            return; // Encerra o método se os dados forem inválidos
        }

        // Cria o objeto Paciente
        Paciente paciente = criarPaciente(nomePaciente, idadeStr);
        if (paciente == null) return; // Encerra se houver erro ao criar o paciente

        Log.d("ScheduleAppointmentActivity", "Paciente: " + paciente + ", Data Consulta: " + dataConsulta);

        // Salva o paciente no banco de dados
        long pacienteId = pacientesDao.adiciona(paciente);
        Log.d("ScheduleAppointmentActivity", "Paciente ID após salvar: " + pacienteId);

        // Salva a consulta no banco de dados
        if (pacienteId != -1) {
            int pacienteIdInt = (int) pacienteId;
            final int medicoId = getIntent().getIntExtra("medicoId", -1);
            Consulta consulta = new Consulta(pacienteIdInt, medicoId, dataConsulta, "", "");
            long consultaId = consultaDao.inserir(consulta);
            Log.d("ScheduleAppointmentActivity", "Consulta ID após salvar: " + consultaId);

            // Exibe mensagem de sucesso ou erro
            if (consultaId != -1) {
                Toast.makeText(this, "Consulta agendada com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Log.e("ScheduleAppointmentActivity", "Erro ao agendar consulta: Falha ao inserir no banco de dados");
                Toast.makeText(this, "Erro ao agendar consulta.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Erro ao agendar consulta: Falha ao salvar paciente.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarDados(String nomePaciente, String idadeStr, String dataConsulta) {
        if (nomePaciente.isEmpty() || idadeStr.isEmpty() || dataConsulta.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Adicione validações adicionais conforme necessário (ex: formato da data)
        return true;
    }

    private Paciente criarPaciente(String nomePaciente, String idadeStr) {
        try {
            int idadePaciente = Integer.parseInt(idadeStr);
            Paciente paciente = new Paciente();
            paciente.setNome(nomePaciente);
            paciente.setIdade(idadePaciente);
            Log.d("ScheduleAppointmentActivity", "Paciente preparado: " + paciente);
            return paciente;
        } catch (NumberFormatException e) {
            Log.e("ScheduleAppointmentActivity", "Erro ao converter idade para inteiro: " + e.getMessage());
            Toast.makeText(this, "Idade inválida.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}