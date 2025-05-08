package com.example.crumbs_.offlineFeature.presenter;

import com.example.crumbs_.offlineFeature.model.NetworkMonitor;
import com.example.crumbs_.offlineFeature.view.NetworkSplashView;

public class NetworkSplashPresenter
{
    private final NetworkSplashView view;
    private final NetworkMonitor monitor;
    private boolean isSplashShown = false;

    public NetworkSplashPresenter(NetworkSplashView view, NetworkMonitor monitor) {
        this.view = view;
        this.monitor = monitor;
    }

    public void onNetworkChanged() {
        if (!monitor.isNetworkAvailable() && !isSplashShown) {
            isSplashShown = true;
            view.showSplashScreen();
        } else if (monitor.isNetworkAvailable() && isSplashShown) {
            isSplashShown = false;
            view.hideSplashScreen();
        }
    }

    public void onRetryClicked() {
        if (monitor.isNetworkAvailable()) {
            isSplashShown = false;
            view.hideSplashScreen();
        } else {
            view.showError("Still no internet connection");
        }
    }
}
