package com.example.crumbs_.forgetPasswordFeature.presenter.activitiesPresenter;

import com.example.crumbs_.forgetPasswordFeature.model.activitiesModel.ForgetPasswordModel;
import com.example.crumbs_.forgetPasswordFeature.model.interfacesesModel.ForgetPasswordModelInterface;
import com.example.crumbs_.forgetPasswordFeature.model.interfacesesModel.NetworkCallback;
import com.example.crumbs_.forgetPasswordFeature.presenter.InterfacesPresenter.ForgetPasswordPresenterInterface;
import com.example.crumbs_.forgetPasswordFeature.view.InterfacesesView.ForgetPasswordViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordPresenter implements ForgetPasswordPresenterInterface, NetworkCallback
{

    ForgetPasswordModelInterface forgetPasswordModelInterface;
    ForgetPasswordViewInterface forgetPasswordViewInterface;
    FirebaseAuth firebaseAuth;

    public ForgetPasswordPresenter(ForgetPasswordViewInterface forgetPasswordViewInterface)
    {
        this.forgetPasswordViewInterface = forgetPasswordViewInterface;
        this.forgetPasswordModelInterface=new ForgetPasswordModel();
        this.firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void sendResetPasswordEmail(String email)
    {
        if (email.isEmpty())
        {
            forgetPasswordViewInterface.onResetEmailFailure("Please enter your Email");
        }
        else
        {
            forgetPasswordModelInterface.sendResetEmail(email,firebaseAuth,this);
        }

    }

    @Override
    public void onSuccessResult()
    {
        forgetPasswordViewInterface.onResetEmailSent();

    }

    @Override
    public void onFailureResult(String message)
    {
        forgetPasswordViewInterface.onResetEmailFailure(message);

    }
}
