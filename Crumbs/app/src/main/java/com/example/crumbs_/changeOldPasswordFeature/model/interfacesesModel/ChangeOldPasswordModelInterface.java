package com.example.crumbs_.changeOldPasswordFeature.model.interfacesesModel;

import com.example.crumbs_.changeOldPasswordFeature.view.InterfacesesView.ChangeOldPasswordViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public interface ChangeOldPasswordModelInterface
{
    void updatePassword(String oldPassword, String newPassword, FirebaseAuth auth, NetworkCallback networkCallback);

}
