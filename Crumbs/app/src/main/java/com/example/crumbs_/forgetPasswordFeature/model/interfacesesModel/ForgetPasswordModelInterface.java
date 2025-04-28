package com.example.crumbs_.forgetPasswordFeature.model.interfacesesModel;

import com.example.crumbs_.forgetPasswordFeature.view.InterfacesesView.ForgetPasswordViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public interface ForgetPasswordModelInterface
{
    void sendResetEmail(String email, FirebaseAuth auth, NetworkCallback networkCallback);

}
