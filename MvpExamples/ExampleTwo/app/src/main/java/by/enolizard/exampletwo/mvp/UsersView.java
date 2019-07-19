package by.enolizard.exampletwo.mvp;

import java.util.List;

import by.enolizard.exampletwo.common.User;

public interface UsersView {
    UserData getUserData();

    void showUsers(List<User> users);

    void showToast(int resId);

    void showProgress();

    void hideProgress();
}
