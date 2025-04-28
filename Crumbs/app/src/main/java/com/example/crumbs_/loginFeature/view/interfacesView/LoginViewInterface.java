package com.example.crumbs_.loginFeature.view.interfacesView;

public interface LoginViewInterface
{
    void onLogInSuccess();
    void onLoginFailure(String message);
    void onGoogleLoginSuccess();
    void onGoogleLoginFailure(String message);
}
