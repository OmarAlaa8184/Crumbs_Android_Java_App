package com.example.crumbs_.loginFeature.presenter.interfacesPresenter;

import android.content.Intent;

import com.example.crumbs_.loginFeature.model.interfacesModel.NetworkCallback;

public interface LoginPresenterInterface
{
    void loginUser(String email ,String password);
    void handleGoogleLoginResult(Intent data);

}
