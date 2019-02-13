package se.indpro.mvprxjavadagger.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import se.indpro.mvprxjavadagger.root.App;
import se.indpro.mvprxjavadagger.R;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View{

    @Inject LoginActivityMVP.Presenter presenter;
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App)getApplication()).getComponent().inject(this);

        etFirstName = findViewById(R.id.et_firstname);
        etLastName = findViewById(R.id.et_lastname);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return etFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return etLastName.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this,"Error the user is not available",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this,"First or Last name can not be empty",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserSavedMessage() {
        Toast.makeText(this,"User Saved!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFirstName(String fname) {
        etFirstName.setText(fname);
    }

    @Override
    public void setLastName(String lname) {
        etLastName.setText(lname);
    }
}
