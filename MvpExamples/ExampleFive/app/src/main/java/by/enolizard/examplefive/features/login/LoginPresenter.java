package by.enolizard.examplefive.features.login;


import android.os.Handler;

import by.enolizard.examplefive.models.AppDatabase;
import by.enolizard.examplefive.models.User;

public class LoginPresenter implements LoginContract.MvpPresenter {

    private LoginContract.MvpView view;

    // FIXME mock data
    public LoginPresenter() {
        User us = new User();
        us.setEmail("bro");
        us.setFirstName("Ivan");
        us.setSecondName("Boris");
        us.setPassword("pass");
        AppDatabase.getAppDatabase().userDao().addUser(us);
    }

    @Override
    public void onClickLogin() {
        LoginData data = view.getLoginData();
        view.showProgress();

        new Handler().postDelayed(() -> {
            User us = AppDatabase.getAppDatabase().userDao().findUser(data.getLogin(),
                    data.getPass());

            if (us == null) {
                view.hideProgress();
                view.toastText("Incorrect login or/and password");
            } else {
                view.hideProgress();
                view.goToWork();
            }
        }, 3000);
    }

    @Override
    public void onClickRegistration() {
        view.goToRegistration();
    }

    @Override
    public void attachView(LoginContract.MvpView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
