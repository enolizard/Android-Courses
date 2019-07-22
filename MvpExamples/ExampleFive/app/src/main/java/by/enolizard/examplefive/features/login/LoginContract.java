package by.enolizard.examplefive.features.login;

import by.enolizard.examplefive.common.MvpBasePresenter;
import by.enolizard.examplefive.common.MvpBaseView;

interface LoginContract {
    interface MvpView extends MvpBaseView {
        LoginData getLoginData();

        void showProgress();

        void hideProgress();

        void toastText(String text);

        void goToWork();

        void goToRegistration();
    }

    interface MvpPresenter extends MvpBasePresenter<MvpView> {
        void onClickLogin();

        void onClickRegistration();
    }
}
