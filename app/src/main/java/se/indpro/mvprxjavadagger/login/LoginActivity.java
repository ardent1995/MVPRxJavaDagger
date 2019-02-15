package se.indpro.mvprxjavadagger.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.indpro.mvprxjavadagger.root.App;
import se.indpro.mvprxjavadagger.R;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View{

    @Inject LoginActivityMVP.Presenter presenter;
    @BindView(R.id.et_firstname) EditText etFirstName;
    @BindView(R.id.et_lastname) EditText etLastName;
    @BindView(R.id.btn_login) Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App)getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void loginButtonClicked(){
        presenter.loginButtonClicked();
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
