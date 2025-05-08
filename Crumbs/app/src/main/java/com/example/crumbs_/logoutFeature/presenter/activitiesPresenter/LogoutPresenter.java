package com.example.crumbs_.logoutFeature.presenter.activitiesPresenter;

import com.example.crumbs_.logoutFeature.presenter.interfacesPresenter.LogoutPresenterInterface;
import com.example.crumbs_.logoutFeature.view.LogoutViewInterface;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutPresenter implements LogoutPresenterInterface {

    private LogoutViewInterface logoutViewInterface;

    public LogoutPresenter(LogoutViewInterface logoutViewInterface) {
        this.logoutViewInterface = logoutViewInterface;
    }


    @Override
    public void logout() {
        try
        {
            FirebaseAuth.getInstance().signOut();
            logoutViewInterface.onLogoutSuccess();
        }
        catch (Exception e)
        {
            logoutViewInterface.onLogoutFailure(e.getMessage());
        }
    }
}
