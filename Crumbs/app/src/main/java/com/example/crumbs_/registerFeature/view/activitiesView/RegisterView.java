package com.example.crumbs_.registerFeature.view.activitiesView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crumbs_.R;
import com.example.crumbs_.registerFeature.model.activitiesModel.RegisterModel;
import com.example.crumbs_.registerFeature.presenter.activitiesPresenter.RegisterPresenter;
import com.example.crumbs_.registerFeature.view.interfacesView.RegisterViewInterface;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterView extends AppCompatActivity implements RegisterViewInterface {

    private EditText emailEdite, passwordEdite, userNameEdite, passwordConfirmEdite;
    private Button btnCreateAccount;

    private TextView alreadyhaveaccountText;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerscreen_layout);

        emailEdite = findViewById(R.id.emailEditText);
        passwordEdite = findViewById(R.id.passwordEditText);
        passwordConfirmEdite = findViewById(R.id.confirmPasswordEditText);
        userNameEdite = findViewById(R.id.nameEditText);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        alreadyhaveaccountText=findViewById(R.id.alreadyhaveaccountText);
        presenter = new RegisterPresenter(this,new RegisterModel(),FirebaseAuth.getInstance());

        alreadyhaveaccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEdite.getText().toString().trim();
                String password = passwordEdite.getText().toString().trim();
                String confirmPassword = passwordConfirmEdite.getText().toString().trim();
                String fullName = userNameEdite.getText().toString().trim();

                presenter.register(email, password, confirmPassword, fullName);

            }
        });
    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterFailure(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
