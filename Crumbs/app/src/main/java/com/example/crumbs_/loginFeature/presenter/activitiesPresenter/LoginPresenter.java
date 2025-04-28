package com.example.crumbs_.loginFeature.presenter.activitiesPresenter;

import android.content.Intent;

import com.example.crumbs_.loginFeature.model.activitiesModel.LoginModel;
import com.example.crumbs_.loginFeature.model.interfacesModel.LoginModelInterface;
import com.example.crumbs_.loginFeature.model.interfacesModel.NetworkCallback;
import com.example.crumbs_.loginFeature.presenter.interfacesPresenter.LoginPresenterInterface;
import com.example.crumbs_.loginFeature.view.interfacesView.LoginViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginPresenterInterface, NetworkCallback
{
    final  LoginModelInterface loginModelInterface;
    final LoginViewInterface loginViewInterface;
    final FirebaseAuth firebaseAuth;


    public LoginPresenter(LoginViewInterface loginViewInterface)
    {
        this.loginViewInterface = loginViewInterface;
        this.loginModelInterface=new LoginModel();
        this.firebaseAuth=FirebaseAuth.getInstance();
    }
    @Override
    public void loginUser(String email, String password)
    {
        if (email.isEmpty() || password.isEmpty())
        {
            loginViewInterface.onLoginFailure("Please fill in all fields");
        }
        else
        {
            loginModelInterface.enterUser(email,password,firebaseAuth,this);
        }

    }
    @Override
    public void handleGoogleLoginResult(Intent data)
    {
        loginModelInterface.handleGoogleSignIn(data,this);

    }
    @Override
    public void onSuccessResult()
    {
       loginViewInterface.onLogInSuccess();
    }

    @Override
    public void onSuccessGoogle()
    {
       loginViewInterface.onGoogleLoginSuccess();
    }

    @Override
    public void onFailureResult(String message)
    {
        loginViewInterface.onLoginFailure(message);
    }
    @Override
    public void onFailureGoogle(String message)
    {
            loginViewInterface.onGoogleLoginFailure(message);
    }
}
