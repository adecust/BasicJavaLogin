package com.example.javacalculator;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button kayitol;
    Button yazdir;
    EditText editTextUsername;
    EditText editTextPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        mAuth = FirebaseAuth.getInstance();
        kayitol = findViewById(R.id.buttonSignUp);
        editTextUsername = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        yazdir = findViewById(R.id.buttonYazdir);

        yazdir.setOnClickListener(v -> writeToFile());

        kayitol.setOnClickListener(v -> {
            String email = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(Register.this, "Email or password cannot be empty",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            });
            }
        });
    }

    private void writeToFile() {
        try {
            String email = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("myText.txt", Context.MODE_APPEND));
            outputStreamWriter.write("Mail= "+email);
            outputStreamWriter.write("\n");
            outputStreamWriter.write("Şifre= "+password);
            outputStreamWriter.write("\n");
            outputStreamWriter.write("****************************\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }

    }

}