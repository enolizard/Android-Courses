package by.enolizard.examplefive.features.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import by.enolizard.examplefive.R;
import by.enolizard.examplefive.features.registration.RegistrationActivity;
import by.enolizard.examplefive.features.work.WorkActivity;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class LoginActivity extends AppCompatActivity implements LoginContract.MvpView {

    // FIXME не правильно просто создавать презентер при создании activity
    private LoginContract.MvpPresenter presenter = new LoginPresenter();

    // FIXME deprecated
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter.attachView(this);
        initViews();
    }


    void login() {
        Observable<Boolean> str = Observable.just(true, false);

        str.subscribe(progressDialog::setCanceledOnTouchOutside);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initViews() {
        findViewById(R.id.btn_login)
                .setOnClickListener((view) -> presenter.onClickLogin());
        findViewById(R.id.txt_registration)
                .setOnClickListener((view) -> presenter.onClickRegistration());
    }

    // LoginContract.MvpView interface
    @Override
    public LoginData getLoginData() {
        TextView login = findViewById(R.id.txt_login);
        TextView pass = findViewById(R.id.txt_pass);

        return new LoginData(login.getText().toString(), pass.getText().toString());
    }

    @Override
    public void goToWork() {
        Intent intent = new Intent(this, WorkActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    public void toastText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        // FIXME deprecated
        progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait));
    }

    @Override
    public void hideProgress() {
        // FIXME deprecated
        progressDialog.dismiss();
    }
}
