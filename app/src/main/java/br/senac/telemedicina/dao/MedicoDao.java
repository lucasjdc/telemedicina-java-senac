package br.senac.telemedicina.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.senac.telemedicina.database.DBHelper;
import br.senac.telemedicina.model.Medico;

public class MedicoDao {
    private SQLiteDatabase db;

    public MedicoDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long inserir(Medico medico) {
        ContentValues values = new ContentValues();
        values.put("nome", medico.getNome());
        values.put("especialidade", medico.getEspecialidade());
        values.put("crm", medico.getCrm());
        return db.insert("Medico", null, values);
    }

    public List<Medico> listarTodos() {
        List<Medico> medicos = new ArrayList<>();
        Cursor cursor = db.query("Medico", null, null, null, null, null, "nome ASC");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
            String especialidade = cursor.getString(cursor.getColumnIndexOrThrow("especialidade"));
            String crm = cursor.getString(cursor.getColumnIndexOrThrow("crm"));
            medicos.add(new Medico(id, nome, especialidade, crm));
        }

        cursor.close();
        return medicos;
    }

    public Medico buscarPorId(int id) {
        Cursor cursor = db.query("Medico", null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
            String especialidade = cursor.getString(cursor.getColumnIndexOrThrow("especialidade"));
            String crm = cursor.getString(cursor.getColumnIndexOrThrow("crm"));
            cursor.close();
            return new Medico(id, nome, especialidade, crm);
        }
        cursor.close();
        return null;
    }

    public int atualizar(Medico medico) {
        ContentValues values = new ContentValues();
        values.put("nome", medico.getNome());
        values.put("especialidade", medico.getEspecialidade());
        values.put("crm", medico.getCrm());
        return db.update("Medico", values, "_id=?", new String[]{String.valueOf(medico.getId())});
    }

    public int excluir(int id) {
        return db.delete("Medico", "_id=?", new String[]{String.valueOf(id)});
    }
}

