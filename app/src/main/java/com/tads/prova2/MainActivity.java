package com.tads.prova2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            SQLiteDatabase banco = openOrCreateDatabase("app", MODE_PRIVATE, null);

            banco.execSQL("CREATE TABLE IF NOT EXISTS frases (id INTEGER PRIMARY KEY AUTOINCREMENT, frase VARCHAR(255))");

            //banco.execSQL("INSERT INTO frases (frase) VALUES ('Que toda inveja, vire massa muscular…')");
            //banco.execSQL("INSERT INTO frases (frase) VALUES ('Seja mais forte que sua melhor desculpa.')");
            //banco.execSQL("INSERT INTO frases (frase) VALUES ('Não existe glória sem sacrifícios.')");
            //banco.execSQL("INSERT INTO frases (frase) VALUES ('Os únicos limites são aqueles que você acredita ter.')");
            //banco.execSQL("INSERT INTO frases (frase) VALUES ('Se não existe esforço, não existe progresso.')");
            //banco.execSQL("DROP TABLE frases");

            String select = "SELECT * FROM frases";

            Cursor cursor = banco.rawQuery(select, null);

            int indexId = cursor.getColumnIndex("id");
            int indexFrase = cursor.getColumnIndex("frase");

            if(cursor != null && cursor.moveToFirst()){
                do{
                    String id = cursor.getString(indexId);
                    String frase = cursor.getString(indexFrase);
                    Log.i("CONSOLE", "ID: " + id + " - FRASE: " + frase);
                }while(cursor.moveToNext());
            }

            int qtde = cursor.getCount();
            Random random = new Random();
            int nrRandom = random.nextInt(qtde) + 1;

            String queryForId = "SELECT frase FROM frases WHERE id = " + nrRandom;
            cursor = banco.rawQuery(queryForId, null);
            cursor.moveToFirst();

            int idFrase = cursor.getColumnIndex("frase");
            String getFrase = cursor.getString(idFrase);

            TextView textFrase = findViewById(R.id.textFrase);

            textFrase.setText(getFrase);
    }
}
