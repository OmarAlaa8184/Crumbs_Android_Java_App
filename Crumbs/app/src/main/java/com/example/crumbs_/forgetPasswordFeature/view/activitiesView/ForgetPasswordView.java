package com.example.crumbs_.forgetPasswordFeature.view.activitiesView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crumbs_.R;
import com.example.crumbs_.forgetPasswordFeature.presenter.activitiesPresenter.ForgetPasswordPresenter;
import com.example.crumbs_.forgetPasswordFeature.view.InterfacesesView.ForgetPasswordViewInterface;

public class ForgetPasswordView extends AppCompatActivity implements ForgetPasswordViewInterface
{
     EditText emailEditText;
     Button btnSendResetEmail;
     ForgetPasswordPresenter forgetPasswordPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword_layout);
        emailEditText=findViewById(R.id.sendEmailEditText);
        btnSendResetEmail=findViewById(R.id.btnSendEmail);
        forgetPasswordPresenter=new ForgetPasswordPresenter(this);

        btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              String email=emailEditText.getText().toString().trim();
              forgetPasswordPresenter.sendResetPasswordEmail(email);
            }
        });

    }
    @Override
    public void onResetEmailSent()
    {
        Toast.makeText(this, "Reset link sent to your email.", Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public void onResetEmailFailure(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
