package com.example.crumbs_.loginFeature.model.interfacesModel;

import android.content.Intent;

import com.example.crumbs_.loginFeature.view.interfacesView.LoginViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public interface LoginModelInterface
{
    void enterUser(String email, String password, FirebaseAuth firebaseAuth, NetworkCallback networkCallback);
    void handleGoogleSignIn(Intent data, NetworkCallback networkCallback);


}
