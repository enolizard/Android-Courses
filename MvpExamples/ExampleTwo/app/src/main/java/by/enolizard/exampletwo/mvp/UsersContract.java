package by.enolizard.exampletwo.mvp;

import java.util.List;

import by.enolizard.exampletwo.common.User;

public interface UsersContract {
    interface View {

        void setPresenter(UsersContract.Presenter presenter);

        UserData getUserData();

        void showUsers(List<User> users);

        void showToast(int resId);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {

        void attachView(UsersContract.View view);

        void detachView();

        void viewIsReady();

        void onClickAddBtn();

        void onClickClearBtn();
    }
}
