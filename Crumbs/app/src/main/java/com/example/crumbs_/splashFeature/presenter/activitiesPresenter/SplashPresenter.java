package com.example.crumbs_.splashFeature.presenter.activitiesPresenter;

import android.os.Handler;
import android.os.Looper;

import com.example.crumbs_.splashFeature.presenter.interfacesPresenter.SplashPresenterInterface;
import com.example.crumbs_.splashFeature.view.interfacesView.SplashViewInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashPresenter implements SplashPresenterInterface {

    private Handler handler= new Handler(Looper.myLooper());
    private Runnable runnable;
    private SplashViewInterface splashViewInterface;

    public SplashPresenter(SplashViewInterface splashViewInterface) {
        this.splashViewInterface = splashViewInterface;
    }

    @Override
    public void displaySplashScreen()
    {
        runnable = new Runnable() {
            @Override
            public void run()
            {
                checkUserLoggedIn();
            }

        };
        handler.postDelayed(runnable,3000);
    }

    @Override
    public void onDestroyRemoveCallbacks()
    {
        handler.removeCallbacks(runnable);

    }

    @Override
    public void checkUserLoggedIn()
    {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null)
        {
             splashViewInterface.goToHomeScreen();
             firebaseUser.reload();
        }
        else
        {
            splashViewInterface.goToLoginScreen();
        }

    }
}
