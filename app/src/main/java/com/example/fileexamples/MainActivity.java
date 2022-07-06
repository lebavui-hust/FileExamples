package com.example.fileexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final String PREF_NAME = "accounts";
    private final String PREF_USERNAME = "USERNAME";
    private final String PREF_PASSWORD = "PASSWORD";

    EditText editUsername, editPassword;
    CheckBox checkSaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        checkSaveAccount = findViewById(R.id.check_save_account);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String username = prefs.getString(PREF_USERNAME, "");
        String password = prefs.getString(PREF_PASSWORD, "");

        editUsername.setText(username);
        editPassword.setText(password);

        if (!username.isEmpty() && !password.isEmpty())
            checkSaveAccount.setChecked(true);
        else
            checkSaveAccount.setChecked(false);

        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                // Thuc hien dang nhap
                if (!username.isEmpty() && !password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_LONG).show();
                    if (checkSaveAccount.isChecked()) {
                        // Luu thong tin dang nhap
                        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(PREF_USERNAME, username);
                        editor.putString(PREF_PASSWORD, password);
                        editor.apply();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Nhap username va password", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Doc file text tu thu muc raw
        try {
            InputStream is = getResources().openRawResource(R.raw.test_raw);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            reader.close();

            Log.v("TAG", builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Doc file text tu thu muc assets
        try {
            InputStream is = getAssets().open("test_assets.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            reader.close();

            Log.v("TAG", builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}