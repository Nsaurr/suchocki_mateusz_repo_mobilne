package com.example.pozycjonowanieelemntowmateuszsuchocki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = findViewById(R.id.buttonSave);
        Button load = findViewById(R.id.buttonLoad);
        TextView text = findViewById(R.id.editTextTextPersonName);
        File path = getFilesDir();
        File file = new File(path, "plik.txt");
        String tekst = text.getText().toString();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileWriter fileWriter= new FileWriter(file);
                    BufferedWriter out = new BufferedWriter(fileWriter);
                    out.write(tekst);
                    out.close();
                } catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = (int) file.length();

                byte[] bytes = new byte[length];

               try {
                   FileInputStream in = new FileInputStream(file);
                   in.read(bytes);
                   in.close();
               }catch (IOException e) {
                   Log.e("Exception", "File read failed: " + e.toString());
               }

                String contents = new String(bytes);
                text.setText(contents);
            }
        });
    }
}