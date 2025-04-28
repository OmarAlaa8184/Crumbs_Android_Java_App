package com.example.crumbs_.splashFeature.view.activtiesView;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crumbs_.R;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;
import com.example.crumbs_.registerFeature.view.activitiesView.RegisterView;
import com.example.crumbs_.splashFeature.presenter.activitiesPresenter.SplashPresenter;
import com.example.crumbs_.splashFeature.view.interfacesView.SplashViewInterface;


public class SplashView extends AppCompatActivity implements SplashViewInterface {

    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layouy);
        splashPresenter=new SplashPresenter(this);
        //splashPresenter.displaySplashScreen();

    }

    @Override
    protected void onStart() {
        super.onStart();
        splashPresenter.displaySplashScreen();

    }

    @Override
    public void goToLoginScreen()
    {
        Intent intent=new Intent(SplashView.this, LoginView.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToHomeScreen()
    {
        Intent intent = new Intent(SplashView.this, RegisterView.class); // or MainActivity
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        splashPresenter.onDestroyRemoveCallbacks();
    }

}