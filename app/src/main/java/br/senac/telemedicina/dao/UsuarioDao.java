package br.senac.telemedicina.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.senac.telemedicina.database.DBHelper;

public class UsuarioDao {
    private final DBHelper dbHelper;

    public UsuarioDao(DBHelper dbHelper) {
        this.dbHelper  = dbHelper;
    }

    public boolean inserirUsuario(String nomeUsuario, String senhaHash) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome_usuario", nomeUsuario);
        values.put("senha_hash", senhaHash);

        long result = -1;
        try {
            result = db.insert("Usuario", null, values);
        } catch (Exception e) {
            Log.e("UsuarioDao", "Erro ao inserir usuário", e);
        } finally {
            db.close();
        }
        return result != -1;
    }

    public boolean verificarLogin(String nomeUsuario, String senhaHash) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT _id FROM Usuario WHERE nome_usuario = ? AND senha_hash = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nomeUsuario, senhaHash});

        boolean existe = cursor.moveToFirst();
        cursor.close();
        db.close();
        return existe;
    }
}
