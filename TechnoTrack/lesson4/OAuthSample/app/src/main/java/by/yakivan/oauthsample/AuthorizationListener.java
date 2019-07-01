package by.yakivan.oauthsample;

public interface AuthorizationListener {
    void onAuthStarted();

    void onComplete(String token);

    void onError(String error);
}
