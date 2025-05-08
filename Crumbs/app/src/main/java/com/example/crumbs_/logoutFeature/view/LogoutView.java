package com.example.crumbs_.logoutFeature.view;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crumbs_.R;
import com.example.crumbs_.getRandomMeal.view.activitiesView.HomeView;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;
import com.example.crumbs_.logoutFeature.presenter.activitiesPresenter.LogoutPresenter;


public class LogoutView extends AppCompatActivity implements LogoutViewInterface
{

    private LogoutPresenter logoutPresenter;

    Button logoutButton,cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout_layout);
        cancelButton=findViewById(R.id.cancelButton);
        logoutButton = findViewById(R.id.logoutButton);
        logoutPresenter = new LogoutPresenter(this);


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutPresenter.logout();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LogoutView.this, HomeView.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onLogoutSuccess() 
    {
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onLogoutFailure(String message)
    {
        Toast.makeText(this, "Logout failed: " + message, Toast.LENGTH_SHORT).show();
    }
}
