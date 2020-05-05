package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginLogin, btnLoginSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setTitle("Log in");
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginLogin = findViewById(R.id.btnLoginLogin);
        btnLoginSignUp = findViewById(R.id.btnLoginSignUp);
        btnLoginSignUp.setOnClickListener(this);
        btnLoginLogin.setOnClickListener(this);
        if (ParseUser.getCurrentUser() !=null){
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLoginLogin:
               if(edtLoginPassword.getText().toString().equals("")
                       || edtLoginEmail.getText().toString().equals("")){

                   Toast.makeText(LogInActivity.this,
                           "You must enter an Email addres and a Password",
                           Toast.LENGTH_LONG).show();;
               }else {

                   ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                           edtLoginPassword.getText().toString(), new LogInCallback() {
                               @Override
                               public void done(ParseUser user, ParseException e) {
                                   if (e == null && user != null) {
                                       Toast.makeText(LogInActivity.this,
                                               "User " + user.getUsername().toString() +
                                                       " Successfully Logged In", Toast.LENGTH_LONG).show();
                                       transitionToSocialMediaActivity();
                                   } else {
                                       Toast.makeText(LogInActivity.this,
                                               "Login Failed", Toast.LENGTH_LONG).show();
                                   }
                               }
                           });
               }
                break;
            case R.id.btnLoginSignUp:
                Intent intent = new Intent(LogInActivity.this,SignUp.class);
                startActivity(intent);
                break;
        }
    }
    public void rootLayoutTapped(View view){
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }

    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LogInActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
