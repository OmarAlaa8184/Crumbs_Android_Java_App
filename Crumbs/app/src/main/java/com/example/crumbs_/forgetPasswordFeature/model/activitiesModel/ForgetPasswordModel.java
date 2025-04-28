package com.example.crumbs_.forgetPasswordFeature.model.activitiesModel;

import com.example.crumbs_.forgetPasswordFeature.model.interfacesesModel.ForgetPasswordModelInterface;
import com.example.crumbs_.forgetPasswordFeature.model.interfacesesModel.NetworkCallback;
import com.example.crumbs_.forgetPasswordFeature.view.InterfacesesView.ForgetPasswordViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordModel implements ForgetPasswordModelInterface {
    @Override
    public void sendResetEmail(String email, FirebaseAuth auth, NetworkCallback networkCallback) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                networkCallback.onSuccessResult();
            }
            else
            {
                networkCallback.onFailureResult(task.getException().getMessage());
            }
        });
    }
}


