package com.example.crumbs_.changeOldPasswordFeature.model.activitiesModel;

import androidx.annotation.NonNull;

import com.example.crumbs_.changeOldPasswordFeature.model.interfacesesModel.ChangeOldPasswordModelInterface;
import com.example.crumbs_.changeOldPasswordFeature.model.interfacesesModel.NetworkCallback;
import com.example.crumbs_.changeOldPasswordFeature.view.InterfacesesView.ChangeOldPasswordViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeOldPasswordModel implements ChangeOldPasswordModelInterface {
    @Override
    public void updatePassword(String oldPassword, String newPassword, FirebaseAuth auth, NetworkCallback networkCallback)
    {
        FirebaseUser firebaseUser=auth.getCurrentUser();
        if (firebaseUser!=null && firebaseUser.getEmail()!=null)
        {
            AuthCredential authCredential = EmailAuthProvider.getCredential(firebaseUser.getEmail(), oldPassword);

            firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        firebaseUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful())
                                {
                                    networkCallback.onSuccessResult();
                                }
                                else
                                {
                                   networkCallback.onFailureResult(task.getException().getMessage());
                                }
                            }
                        });
                    }
                    else
                    {
                        networkCallback.onFailureResult(task.getException().getMessage());

                    }
                }
            });
        }
        else
        {
             networkCallback.onFailureResult("No user is currently logged in");
        }
    }
 }


