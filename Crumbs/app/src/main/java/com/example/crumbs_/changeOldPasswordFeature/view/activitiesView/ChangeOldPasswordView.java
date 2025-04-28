package com.example.crumbs_.changeOldPasswordFeature.view.activitiesView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crumbs_.R;
import com.example.crumbs_.changeOldPasswordFeature.presenter.activitiesPresenter.ChangeOldPasswordPresenter;
import com.example.crumbs_.changeOldPasswordFeature.view.InterfacesesView.ChangeOldPasswordViewInterface;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;

public class ChangeOldPasswordView extends AppCompatActivity implements ChangeOldPasswordViewInterface
{
     EditText currentpasswordEditText;
     EditText newpasswordEditeText;
     EditText confirmNewPasswordEditeText;
     Button btnChangePassword;

     ChangeOldPasswordPresenter changeOldPasswordPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changeoldpasswordscreen_layout);
        currentpasswordEditText=findViewById(R.id.currentpasswordEditText);
        newpasswordEditeText=findViewById(R.id.newpasswordEditText);
        confirmNewPasswordEditeText=findViewById(R.id.confirmNewPasswordEditText);
        btnChangePassword=findViewById(R.id.btnChangepassword);
        changeOldPasswordPresenter =new ChangeOldPasswordPresenter(this);


        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              String oldPassword=currentpasswordEditText.getText().toString().trim();
              String newPassword=newpasswordEditeText.getText().toString().trim();
              String confirmNewPassword=confirmNewPasswordEditeText.getText().toString().trim();
              changeOldPasswordPresenter.changePassword(oldPassword,newPassword,confirmNewPassword);
            }
        });

    }
    @Override
    public void onChangePasswordSuccess()
    {
        Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(ChangeOldPasswordView.this, LoginView.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onChangePasswordFailure(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
