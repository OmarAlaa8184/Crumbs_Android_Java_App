package com.example.crumbs_.offlineFeature.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crumbs_.R;
import com.example.crumbs_.addToFavoriteFeature.view.activitiesView.FavoriteView;
import com.example.crumbs_.offlineFeature.model.NetworkMonitor;
import com.example.crumbs_.offlineFeature.presenter.NetworkSplashPresenter;

public class NetworkSplashActivity extends AppCompatActivity implements NetworkSplashView {
    private NetworkSplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        TextView messageTextView = findViewById(R.id.tv_network_message);
        Button retryButton = findViewById(R.id.btn_retry);
        Button goToFav=findViewById(R.id.btngofav);
        presenter = new NetworkSplashPresenter(this, new NetworkMonitor(this));

        messageTextView.setText("No Internet Connection\nPlease check your Wi-Fi or mobile data");

        retryButton.setOnClickListener(v -> presenter.onRetryClicked());
        goToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NetworkSplashActivity.this, FavoriteView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showSplashScreen() {
        // Already shown, as this activity is the splash screen
    }

    @Override
    public void hideSplashScreen() {
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}