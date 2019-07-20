package by.enolizard.exampletwo.features.users;

import java.util.List;

import by.enolizard.exampletwo.common.UserModel;

public interface UsersContract {
    interface View {

        void setPresenter(UsersContract.Presenter presenter);

        UserData getUserData();

        void showUsers(List<UserModel> userModels);

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
