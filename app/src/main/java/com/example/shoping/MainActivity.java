package com.example.shoping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;


public class MainActivity extends AppCompatActivity {

    RelativeLayout reallay1 , really2 ;
    EditText passwordinput;

    Button signup , forgot,  Login;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            reallay1.setVisibility(View.VISIBLE);
            really2.setVisibility(View.VISIBLE);
        }
    };


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forgot = findViewById(R.id.forgetbutton);
       signup = findViewById(R.id.signupbutton);
        passwordinput = findViewById(R.id.editpassword);
        reallay1 = findViewById(R.id.really1);
        really2 = findViewById(R.id.really2);
        Login = findViewById(R.id.loginbutton);

        handler.postDelayed(runnable , 2000);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , RegisterActivity.class);
                MainActivity.this.startActivity(i);
                Animatoo.animateCard(MainActivity.this);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , RegisterActivity.class);
                MainActivity.this.startActivity(i);
                Animatoo.animateCard(MainActivity.this);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , ForgotActivity.class);
                MainActivity.this.startActivity(i);
                Animatoo.animateCard(MainActivity.this);
            }
        });



    }


}
