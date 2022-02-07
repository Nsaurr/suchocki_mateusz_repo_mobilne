package com.example.pozycjonowanieelemntowmateuszsuchocki;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = findViewById(R.id.buttonSave);
        Button load = findViewById(R.id.buttonLoad);
        TextView text = findViewById(R.id.editTextTextPersonName);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = text.getText().toString();
                writeToFile("plik.txt", content);
                text.setText("");
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String content = readFromFile("plik.txt");
               text.setText(content);
            }
        });
    }

    public String readFromFile(String fileName){
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, fileName);
        byte[] content = new byte[(int)readFrom.length()];
        try{
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            return new String(content);
        }catch(Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    public void writeToFile(String fileName, String content){
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            writer.write(content.getBytes(StandardCharsets.UTF_8));
            writer.close();
            Toast.makeText(getApplicationContext(), "Pomyślnie zapisano do pliku: "+fileName, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}