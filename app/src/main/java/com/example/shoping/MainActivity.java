package com.example.shoping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    RelativeLayout reallay1, really2;
    EditText passwordinput, inputemail;
    private FirebaseAuth auth;
    Button signup, forgot, Login;
    ProgressBar progressBar;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            reallay1.setVisibility(View.VISIBLE);
            really2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        forgot = findViewById(R.id.forgetbutton);
        signup = findViewById(R.id.signupbutton);
        passwordinput = findViewById(R.id.editpassword);
        inputemail = findViewById(R.id.emailid);
        reallay1 = findViewById(R.id.really1);
        really2 = findViewById(R.id.really2);
        Login = findViewById(R.id.loginbutton);
        progressBar = findViewById(R.id.progressBar);

        handler.postDelayed(runnable, 2000);

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(i);
                Animatoo.animateCard(MainActivity.this);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputemail.getText().toString();
                final String password = passwordinput.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        passwordinput.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForgotActivity.class);
                MainActivity.this.startActivity(i);
                Animatoo.animateCard(MainActivity.this);
            }
        });

    }


}

