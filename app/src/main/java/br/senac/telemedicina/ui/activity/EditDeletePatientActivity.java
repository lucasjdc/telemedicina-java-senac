package br.senac.telemedicina.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import br.senac.telemedicina.R;

public class EditDeletePatientActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        if (getSupportActionBar() != null)  {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_editdeletepatient);
    }
}
