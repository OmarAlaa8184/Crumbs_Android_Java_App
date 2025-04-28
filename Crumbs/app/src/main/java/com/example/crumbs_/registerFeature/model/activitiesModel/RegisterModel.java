package com.example.crumbs_.registerFeature.model.activitiesModel;

import com.example.crumbs_.registerFeature.model.interfacesModel.RegisterModelInterface;
import com.example.crumbs_.registerFeature.view.interfacesView.RegisterViewInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterModel implements RegisterModelInterface {
    @Override
    public void createUser(String email, String password, String fullName, FirebaseAuth firebaseAuth, NetworkCallback networkCallback )
    {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task ->
                {
                    if (task.isSuccessful())
                    {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null)
                        {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fullName)
                                    .build();

                                     user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(profileTask ->
                                    {
                                        if (profileTask.isSuccessful())
                                        {
                                                 networkCallback.onSuccessResult();
                                        }
                                        else
                                        {
                                            networkCallback.onFailureResult(profileTask.getException().getMessage());
                                        }
                                    });
                        }
                        else
                        {
                            networkCallback.onFailureResult("User creation failed");
                        }
                    }
                    else
                    {
                             networkCallback.onFailureResult(task.getException().getMessage());
                    }
                });
    }
}

