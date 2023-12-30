package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.entity.User;
import com.example.project.utils.Helper;
import com.example.project.utils.UserDBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Optional;

public class SignIn extends AppCompatActivity {
    EditText username;
    EditText password;

    UserDBHelper userDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(()-> true);
        new Handler().postDelayed(()-> {
            splashScreen.setKeepOnScreenCondition(()-> false);
        },3000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        userDBHelper = new UserDBHelper(SignIn.this);
    }


    public void signIn(View view) {
        String usernameText = username.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        if(usernameText.isEmpty() || passwordText.isEmpty()){
            Helper.makeLongToast(SignIn.this, "you need to complete information");
            return;
        }

        Optional<User> optionalUser =userDBHelper.getByUsername(usernameText);

        if(optionalUser.isPresent()){
            if(optionalUser.get().getPassword().equals(passwordText)){
                startActivity(new Intent(SignIn.this.getApplicationContext(), MainActivity.class));
                finish();
            }
            else {
                Helper.makeLongToast(SignIn.this, "password is incorrect");
            }
        }
        else {
            Helper.makeLongToast(SignIn.this, "user not exist");
        }
    }
}