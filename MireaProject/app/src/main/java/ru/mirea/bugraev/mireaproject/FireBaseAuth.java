package ru.mirea.bugraev.mireaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireBaseAuth extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    private EditText emailField;
    private EditText passwordField;
    private Intent intent;

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_auth);
        emailField=findViewById(R.id.emailEditText);
        passwordField=findViewById(R.id.passwordEditText);
        intent=new Intent(FireBaseAuth.this,MainActivity.class);

        Auth=FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=Auth.getCurrentUser();
    }

    private boolean IsValidate()
    {
        boolean valid=true;
        String email=emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String password=passwordField.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            passwordField.setError("Required.");
            valid=false;
        }
        else {
            passwordField.setError(null);
        }

        return valid;
    }

    private void createAccount(String email, String password)
    {
        Log.d(TAG,"createAccount:"+email);
        if(!IsValidate())
        {
            Log.d(TAG,"RETURN");
            return;
        }

        Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
            new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Log.d(TAG,"Create user with email - success");
                        FirebaseUser user=Auth.getCurrentUser();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(FireBaseAuth.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }
                }
            });
    }

    private void signIn(String email, String password)
    {
        Log.d(TAG,"createAccount:"+email);
        if(!IsValidate())
        {
            return;
        }

        Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
            new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Log.d(TAG,"Create user with email - success");
                        FirebaseUser user=Auth.getCurrentUser();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(FireBaseAuth.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }
                }
            });
    }


    public void onSignIn(View view) {
        signIn(emailField.getText().toString(),passwordField.getText().toString());
    }

    public void onCreateAccount(View view) {
        createAccount(emailField.getText().toString(),passwordField.getText().toString());
    }
}