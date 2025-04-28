package com.example.crumbs_.changeOldPasswordFeature.presenter.activitiesPresenter;

import com.example.crumbs_.changeOldPasswordFeature.model.activitiesModel.ChangeOldPasswordModel;
import com.example.crumbs_.changeOldPasswordFeature.model.interfacesesModel.ChangeOldPasswordModelInterface;
import com.example.crumbs_.changeOldPasswordFeature.model.interfacesesModel.NetworkCallback;
import com.example.crumbs_.changeOldPasswordFeature.presenter.InterfacesPresenter.ChangeOldPasswordPresenterInterface;
import com.example.crumbs_.changeOldPasswordFeature.view.InterfacesesView.ChangeOldPasswordViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public class ChangeOldPasswordPresenter implements ChangeOldPasswordPresenterInterface, NetworkCallback
{
    ChangeOldPasswordModelInterface changeOldPasswordModelInterface;
    ChangeOldPasswordViewInterface changeOldPasswordViewInterface;
    FirebaseAuth firebaseAuth;

    public ChangeOldPasswordPresenter(ChangeOldPasswordViewInterface changeOldPasswordViewInterface)
    {
        this.changeOldPasswordViewInterface = changeOldPasswordViewInterface;
        this.changeOldPasswordModelInterface =new ChangeOldPasswordModel();
        this.firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String confirmPassword)
    {
        if (oldPassword.isEmpty() ||newPassword.isEmpty()|| confirmPassword.isEmpty())
        {
            changeOldPasswordViewInterface.onChangePasswordFailure("Please fill in all fields");

        }
        else if (!newPassword.equals(confirmPassword))
        {
            changeOldPasswordViewInterface.onChangePasswordFailure("New passwords do not match");
        }
        else
        {
            changeOldPasswordModelInterface.updatePassword(oldPassword,newPassword,firebaseAuth, this);
        }

    }

    @Override
    public void onSuccessResult()
    {
        changeOldPasswordViewInterface.onChangePasswordSuccess();
    }

    @Override
    public void onFailureResult(String message)
    {
        changeOldPasswordViewInterface.onChangePasswordFailure(message);
    }
}
