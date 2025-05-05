package br.senac.telemedicina.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.senac.telemedicina.R;

public class AddPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_addpatient);
    }

}
