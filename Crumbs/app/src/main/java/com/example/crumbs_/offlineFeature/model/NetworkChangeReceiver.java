package com.example.crumbs_.offlineFeature.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.crumbs_.offlineFeature.presenter.NetworkSplashPresenter;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private static NetworkSplashPresenter presenter;

    public static void setPresenter(NetworkSplashPresenter presenter) {
        NetworkChangeReceiver.presenter = presenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (presenter != null) {
            presenter.onNetworkChanged();
        }
    }
}