package com.example.fileexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class InternalStorageActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        editText = findViewById(R.id.edit_text);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    OutputStream os = openFileOutput("data.txt", MODE_PRIVATE);
                    OutputStreamWriter writer = new OutputStreamWriter(os);
                    writer.write(editText.getText().toString());
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        findViewById(R.id.button_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputStream is = openFileInput("data.txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder builder = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line + "\n");
                    }
                    reader.close();

                    editText.setText(builder.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}