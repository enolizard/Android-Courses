package by.enolizard.examplefive.features.login;


import android.os.Handler;

public class LoginPresenter implements LoginContract.MvpPresenter {

    private LoginContract.MvpView view;

    @Override
    public void onClickLogin() {
        LoginData data = view.getLoginData();
        new Handler().postDelayed(() -> {
            if (data.getLogin().equals("ad")
            && data.getPass().equals("ad")){
                view.hideProgress();
                view.goToWork();
            } else {
                view.hideProgress();
                view.toastText("Incorrect login or/and password");
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
