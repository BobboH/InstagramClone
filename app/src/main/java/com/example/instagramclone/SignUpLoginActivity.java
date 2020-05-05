package com.example.instagramclone;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {


        private Button btnSignUp;
        private Button btnLogin;
        private EditText edtUserNameSignUp;
        private EditText edtPasswordSignUp;
        private EditText edtUserNameLogin;
        private EditText edtPasswordLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        btnSignUp= findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    "SignUp succeeded!",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    "SignUp failed!",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  ParseUser appUser = new ParseUser();
          //      Toast.makeText(SignUpLoginActivity.this,edtPasswordLogin.getText().toString() + " " + edtUserNameLogin.getText().toString(),Toast.LENGTH_LONG).show();
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(),
                        edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null && e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    "Login succeeded!",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }else
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    "Login failed!" + e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                });
            }
        });

    }


}
