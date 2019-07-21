package by.enolizard.examplefive.common;

public interface MvpBasePresenter<V extends MvpBaseView> {
    void attachView(V view);
    void detachView();
}
