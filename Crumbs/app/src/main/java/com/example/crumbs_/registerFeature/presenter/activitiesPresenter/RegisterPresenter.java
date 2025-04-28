package com.example.crumbs_.registerFeature.presenter.activitiesPresenter;

import com.example.crumbs_.registerFeature.model.activitiesModel.NetworkCallback;
import com.example.crumbs_.registerFeature.model.interfacesModel.RegisterModelInterface;
import com.example.crumbs_.registerFeature.presenter.interfacesPresenter.RegisterPresenterInterface;
import com.example.crumbs_.registerFeature.view.interfacesView.RegisterViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPresenter implements RegisterPresenterInterface ,NetworkCallback {
    private final RegisterViewInterface view;
    private final RegisterModelInterface model;
    private final FirebaseAuth firebaseAuth;

    public RegisterPresenter(RegisterViewInterface view, RegisterModelInterface model, FirebaseAuth firebaseAuth) {
        this.view = view;
        this.model = model;
        this.firebaseAuth = firebaseAuth;
    }


    @Override
    public void register(String email, String password, String confirmPassword, String fullName)
    {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullName.isEmpty())
        {
            view.onRegisterFailure("Please fill in all fields");
            return;
        }
        if (!password.equals(confirmPassword)) {
            view.onRegisterFailure("Passwords do not match");
            return;
        }
        model.createUser(email, password, fullName, firebaseAuth,this);
    }
    @Override
    public void onSuccessResult()
    {
        view.onRegisterSuccess();

    }
    @Override
    public void onFailureResult(String message)
    {
         view.onRegisterFailure(message);
    }

}
