package br.senac.telemedicina.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import br.senac.telemedicina.R;

public class PatientHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        String pacienteId = getIntent().getStringExtra("id");
        if (pacienteId != null) {
            Log.d("PatientHistoryActivity", "Paciente ID recebido: " + pacienteId);
        } else {
            Toast.makeText(this, "Paciente não especificado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
