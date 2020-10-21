package com.example.myapplication.myapplication14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private static final String NOM_FICHIER = "monFichier.txt";
    private static final String CONTENU = "Ceci est mon exemple";
    private static final String TAG = "ACOS";
    private StringBuffer resultat = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSave(View view) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    FileOutputStream fos = openFileOutput(NOM_FICHIER, MODE_PRIVATE);
                    fos.write(CONTENU.getBytes());
                    fos.close();
                } catch (Exception e) {
                    Log.e(TAG, "Message" + e.getMessage());
                }
            }
        }).start();
    }

    public void onClickRead(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream fis = openFileInput(NOM_FICHIER);
                    byte[] buffer = new byte[1024];
                    while(fis.read(buffer) == -1){
                        resultat.append(new String (buffer));

                    }
                    fis.close();
                } catch (Exception e) {
                    Log.e(TAG, "Message" + e.getMessage());
                }

            }
        }).start();
        Toast.makeText(MainActivity.this, "Lecture" + resultat, Toast.LENGTH_SHORT).show();
    }

}