package com.example.crumbs_.loginFeature.model.activitiesModel;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.crumbs_.loginFeature.model.interfacesModel.LoginModelInterface;
import com.example.crumbs_.loginFeature.model.interfacesModel.NetworkCallback;
import com.example.crumbs_.loginFeature.view.interfacesView.LoginViewInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginModel implements LoginModelInterface {
    @Override
    public void enterUser(String email, String password, FirebaseAuth firebaseAuth, NetworkCallback networkCallback)
    {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
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

    @Override
    public void handleGoogleSignIn(Intent data, NetworkCallback networkCallback)
    {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {
            GoogleSignInAccount account = task.getResult(Exception.class);
            if (account != null)
            {
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(authTask ->
                        {
                            if (authTask.isSuccessful())
                            {
                                networkCallback.onSuccessGoogle();
                            }
                            else
                            {
                                networkCallback.onFailureGoogle(authTask.getException().getMessage());
                            }
                        });
            }
        }
        catch (Exception e)
        {
            networkCallback.onFailureGoogle(e.getMessage());
        }
    }

    }

