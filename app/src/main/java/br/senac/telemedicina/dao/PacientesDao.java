package br.senac.telemedicina.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import br.senac.telemedicina.database.DBHelper;
import br.senac.telemedicina.model.Paciente;

public class PacientesDao {
    private final DBHelper dbHelper;
    public PacientesDao(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public List<Paciente> buscaTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM Paciente";
            cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst())  {
                do {
                    Paciente paciente = new Paciente(
                            cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("idade")),
                            cursor.getString(cursor.getColumnIndexOrThrow("pressao_arterial")),
                            cursor.getString(cursor.getColumnIndexOrThrow("glicose")),
                            cursor.getString(cursor.getColumnIndexOrThrow("colesterol"))
                    );
                    pacientes.add(paciente);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return pacientes;
    }

    public long adiciona(Paciente paciente) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", paciente.getNome());
        values.put("idade", paciente.getIdade());
        values.put("pressao_arterial", paciente.getPressao());
        values.put("glicose", paciente.getGlicose());
        values.put("colesterol", paciente.getColesterol());

        long id = db.insert("pacientes", null, values);
        db.close();
        return id;
    }

    public void limparPacientes() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete("pacientes", null, null);
        db.close();
        Log.i("PacientesDao", "Pacientes removidos: " + rowsDeleted);
    }
}
