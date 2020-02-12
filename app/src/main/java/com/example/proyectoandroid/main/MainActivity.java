package com.example.proyectoandroid.main;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.app.ProgressDialog;
import android.content.Intent;
//import com.an.biometric.BiometricCallback;

import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.activity.ActivityMain;
import com.example.proyectoandroid.activity.RegistrarUsuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private Button login;
    private ImageButton btnDedo;
    private TextView registro;
    private EditText textEmail;
    private EditText textPassword;
    private ProgressDialog progressDialog;

    BiometricPrompt mBiometricManager;
    Executor executor = Executors.newSingleThreadExecutor();

    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.btnLogin);
        registro = findViewById(R.id.btnRegistro);
        btnDedo = findViewById(R.id.btnEntrarDedo);

        textEmail = findViewById(R.id.correoRegistro);
        textPassword = findViewById(R.id.password);

        login.setOnClickListener(this);
        registro.setOnClickListener(this);
        btnDedo.setOnClickListener(this);

    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {

            case R.id.btnLogin:
                Log.e("CASE", "Login");
                loguearse();
                break;
            case R.id.btnRegistro:
                Log.e("CASE", "Registro");
                final Intent intent = new Intent(view.getContext(), RegistrarUsuario.class);
                startActivity(intent);
                break;
            case R.id.btnEntrarDedo:
                biometricPrompt.authenticate(promptInfo);
        }
    }
    public void loguearse() {
        final String email = textEmail.getText().toString().trim();
        final String password = textPassword.getText().toString().trim();
        comprobarCampos(email,password);

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //checking if success
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Bienvenido ",Toast.LENGTH_LONG).show();
                    Intent intentHome = new Intent(getApplicationContext(),ActivityMain.class);
                    startActivity(intentHome);
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(MainActivity.this,"Ese usuario ya existe",Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(MainActivity.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                }
                //progressDialog.dismiss();
            }
        });
    }

    public void comprobarCampos(String email,String password){

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

    }

    final BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this,executor, new BiometricPrompt.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            Log.e("ERROR","ERROR************");
        }

        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Log.e("ERROR","SUCCESS************");
            final Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
            startActivity(intent);

        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Log.e("ERROR","FAILED************");
        }
    });

    final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("PON TU HUELLA")
            .setDescription("AQUI ES UNA DESCRIPCION")
            .setNegativeButtonText("CANCELAR")
            .build();
}

