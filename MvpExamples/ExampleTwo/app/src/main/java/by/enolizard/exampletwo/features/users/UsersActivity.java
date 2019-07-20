package by.enolizard.exampletwo.features.users;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.enolizard.exampletwo.R;
import by.enolizard.exampletwo.common.UserModel;
import by.enolizard.exampletwo.common.UserAdapter;
import by.enolizard.exampletwo.database.DbHelper;

public class UsersActivity extends AppCompatActivity implements UsersContract.View {

    private UserAdapter userAdapter;

    private EditText textName;
    private EditText textEmail;
    private ProgressDialog progressDialog;

    private UsersContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void init() {
        textName = findViewById(R.id.txt_name);
        textEmail = findViewById(R.id.txt_email);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickAddBtn();
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickClearBtn();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        userAdapter = new UserAdapter();

        RecyclerView userList = findViewById(R.id.rv_users_list);
        userList.setLayoutManager(layoutManager);
        userList.setAdapter(userAdapter);


        DbHelper dbHelper = new DbHelper(this);
        UsersModel usersModel = new UsersModel(dbHelper);
        presenter = new UsersPresenter(usersModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }


    // UsersContract.View interface
    @Override
    public void setPresenter(UsersContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public UserData getUserData() {

        UserData userData = new UserData();
        userData.setName(textName.getText().toString());
        userData.setEmail(textEmail.getText().toString());
        return userData;
    }

    @Override
    public void showUsers(List<UserModel> userModels) {
        userAdapter.setData(userModels);
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait));
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
