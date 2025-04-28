package com.example.crumbs_.loginFeature.view.activitiesView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crumbs_.R;
import com.example.crumbs_.forgetPasswordFeature.view.activitiesView.ForgetPasswordView;
import com.example.crumbs_.loginFeature.presenter.activitiesPresenter.LoginPresenter;
import com.example.crumbs_.loginFeature.view.interfacesView.LoginViewInterface;
import com.example.crumbs_.registerFeature.view.activitiesView.RegisterView;
import com.example.crumbs_.splashFeature.view.activtiesView.SplashView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class LoginView extends AppCompatActivity implements LoginViewInterface
{
    TextView signUp;
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    Button googleLoginButton;
    LoginPresenter loginPresenter;
    TextView forgetPassword;
    static final int RC_SIGN_IN = 1;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen_layout);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signupText);
        forgetPassword=findViewById(R.id.forgotPasswordText);
        googleLoginButton=findViewById(R.id.googleLoginButton);

        loginPresenter=new LoginPresenter(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleLoginButton.setOnClickListener(new View.OnClickListener()
        {
              @Override
              public void onClick(View v)
              {
                  signInWithGoogle();
               }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginView.this, RegisterView.class);
                startActivity(intent);

            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginView.this, ForgetPasswordView.class);
                startActivity(intent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                loginPresenter.loginUser(email,password);

            }
        });
    }

    @Override
    public void onLogInSuccess()
    {
        Toast.makeText(this, "LogIn Successful", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(LoginView.this, SplashView.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onGoogleLoginSuccess()
    {
        Toast.makeText(this, "Google Login Successful", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(LoginView.this,SplashView.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onGoogleLoginFailure(String message)
    {
        Toast.makeText(this, "Google Login Failed: " + message, Toast.LENGTH_LONG).show();

    }
    private void signInWithGoogle()
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN)
        {
            if (data != null)
            {
                loginPresenter.handleGoogleLoginResult(data);
            }
            else
            {
                Toast.makeText(this, "Google Sign In failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}