package by.enolizard.exampletwo.features.users;

import android.content.ContentValues;
import android.text.TextUtils;

import java.util.List;

import by.enolizard.exampletwo.R;
import by.enolizard.exampletwo.common.UserModel;
import by.enolizard.exampletwo.common.UserTable;

public class UsersPresenter implements UsersContract.Presenter {

    private UsersContract.View view;
    private final UsersModel model;

    public UsersPresenter(UsersModel model) {
        this.model = model;
    }

    // UsersContract.Presenter
    @Override
    public void attachView(UsersContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void viewIsReady() {
        loadUsers();
    }

    @Override
    public void onClickAddBtn() {
        UserData userData = view.getUserData();
        if (TextUtils.isEmpty(userData.getName()) || TextUtils.isEmpty(userData.getEmail())) {
            view.showToast(R.string.empty_values);
            return;
        }

        ContentValues cv = new ContentValues(2);
        cv.put(UserTable.COLUMN.NAME, userData.getName());
        cv.put(UserTable.COLUMN.EMAIL, userData.getEmail());
        view.showProgress();
        model.addUser(cv, new UsersModel.CompleteCallback() {
            @Override
            public void onComplete() {
                view.hideProgress();
                loadUsers();
            }
        });
    }

    @Override
    public void onClickClearBtn() {
        view.showProgress();
        model.clearUsers(new UsersModel.CompleteCallback() {
            @Override
            public void onComplete() {
                view.hideProgress();
                loadUsers();
            }
        });
    }

    public void loadUsers() {
        model.loadUsers(new UsersModel.LoadUserCallback() {
            @Override
            public void onLoad(List<UserModel> userModels) {
                view.showUsers(userModels);
            }
        });
    }
}
