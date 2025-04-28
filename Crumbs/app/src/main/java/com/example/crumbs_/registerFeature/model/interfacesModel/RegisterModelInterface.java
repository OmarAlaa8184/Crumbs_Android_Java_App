package com.example.crumbs_.registerFeature.model.interfacesModel;

import com.example.crumbs_.registerFeature.model.activitiesModel.NetworkCallback;
import com.example.crumbs_.registerFeature.view.interfacesView.RegisterViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public interface RegisterModelInterface
{
    void createUser(String email, String password, String fullName, FirebaseAuth firebaseAuth, NetworkCallback networkCallback);


}
