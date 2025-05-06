package br.senac.telemedicina.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "telemedicina.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela Paciente
        db.execSQL("CREATE TABLE Paciente (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "idade INTEGER CHECK (idade >= 0), " +
                "pressao_arterial TEXT, " +
                "glicose TEXT, " +
                "colesterol TEXT)");

        // Criação da tabela HistoricoPaciente
        db.execSQL("CREATE TABLE HistoricoPaciente (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "paciente_id INTEGER NOT NULL, " +
                "data_registro TEXT DEFAULT (date('now')), " +
                "pressao_arterial TEXT, " +
                "glicose TEXT, " +
                "colesterol TEXT, " +
                "FOREIGN KEY (paciente_id) REFERENCES Paciente(_id) ON DELETE CASCADE)");

        // Criação da tabela Usuario
        db.execSQL("CREATE TABLE Usuario (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome_usuario TEXT UNIQUE NOT NULL, " +
                "senha_hash TEXT NOT NULL, " +
                "criado_em TEXT DEFAULT (datetime('now')))");

        Log.d("DBHelper", "Tabelas criadas com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBHelper", "Atualizando banco de dados da versão " + oldVersion + " para " + newVersion);
        // Aqui você pode adicionar lógica para atualizar tabelas se necessário no futuro
    }
}
