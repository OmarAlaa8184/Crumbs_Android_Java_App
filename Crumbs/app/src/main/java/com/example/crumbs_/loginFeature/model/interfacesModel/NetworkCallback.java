package com.example.crumbs_.loginFeature.model.interfacesModel;

import java.util.List;

public interface NetworkCallback
{
    public void onSuccessResult();
    public void onSuccessGoogle();
    public void onFailureResult(String message);
    public void onFailureGoogle(String message);
}
