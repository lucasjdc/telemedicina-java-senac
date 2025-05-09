package br.senac.telemedicina.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.senac.telemedicina.database.DBHelper;
import br.senac.telemedicina.model.Consulta;

public class ConsultaDao {
    private SQLiteDatabase db;

    public ConsultaDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long inserir(Consulta consulta) {
        ContentValues values = new ContentValues();
        values.put("paciente_id", consulta.getPacienteId());
        values.put("medico_id", consulta.getMedicoId());
        values.put("data_consulta", consulta.getDataConsulta());
        values.put("diagnostico", consulta.getDiagnostico());
        values.put("prescricao", consulta.getPrescricao());
        return db.insert("Consulta", null, values);
    }

    public List<Consulta> listarTodas() {
        List<Consulta> consultas = new ArrayList<>();
        Cursor cursor = db.query("Consulta", null, null, null, null, null, "data_consulta DESC");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            int pacienteId = cursor.getInt(cursor.getColumnIndexOrThrow("paciente_id"));
            int medicoId = cursor.getInt(cursor.getColumnIndexOrThrow("medico_id"));
            String dataConsulta = cursor.getString(cursor.getColumnIndexOrThrow("data_consulta"));
            String diagnostico = cursor.getString(cursor.getColumnIndexOrThrow("diagnostico"));
            String prescricao = cursor.getString(cursor.getColumnIndexOrThrow("prescricao"));
            consultas.add(new Consulta(id, pacienteId, medicoId, dataConsulta, diagnostico, prescricao));
        }

        cursor.close();
        return consultas;
    }

    public Consulta buscarPorId(int id) {
        Cursor cursor = db.query("Consulta", null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            int pacienteId = cursor.getInt(cursor.getColumnIndexOrThrow("paciente_id"));
            int medicoId = cursor.getInt(cursor.getColumnIndexOrThrow("medico_id"));
            String dataConsulta = cursor.getString(cursor.getColumnIndexOrThrow("data_consulta"));
            String diagnostico = cursor.getString(cursor.getColumnIndexOrThrow("diagnostico"));
            String prescricao = cursor.getString(cursor.getColumnIndexOrThrow("prescricao"));
            cursor.close();
            return new Consulta(id, pacienteId, medicoId, dataConsulta, diagnostico, prescricao);
        }
        cursor.close();
        return null;
    }

    public int atualizar(Consulta consulta) {
        ContentValues values = new ContentValues();
        values.put("paciente_id", consulta.getPacienteId());
        values.put("medico_id", consulta.getMedicoId());
        values.put("data_consulta", consulta.getDataConsulta());
        values.put("diagnostico", consulta.getDiagnostico());
        values.put("prescricao", consulta.getPrescricao());
        return db.update("Consulta", values, "_id=?", new String[]{String.valueOf(consulta.getId())});
    }

    public int excluir(int id) {
        return db.delete("Consulta", "_id=?", new String[]{String.valueOf(id)});
    }
}
