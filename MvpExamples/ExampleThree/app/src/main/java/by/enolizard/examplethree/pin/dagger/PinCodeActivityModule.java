package by.enolizard.examplethree.pin.dagger;

import dagger.Module;
import dagger.Provides;
import by.enolizard.examplethree.base.dagger.ActivityModule;
import by.enolizard.examplethree.common.Constants;
import by.enolizard.examplethree.pin.mvp.PinCodeChangePresenter;
import by.enolizard.examplethree.pin.mvp.PinCodeCheckPresenter;
import by.enolizard.examplethree.pin.mvp.PinCodeContract;
import by.enolizard.examplethree.pin.mvp.PinCodeCreatePresenter;
import by.enolizard.examplethree.storage.Preferences;

@Module
public class PinCodeActivityModule implements ActivityModule {

    private final Constants.PinCodeMode pinCodeMode;

    public PinCodeActivityModule(Constants.PinCodeMode pinCodeMode) {
        this.pinCodeMode = pinCodeMode;
    }

    @PinCodeActivityScope
    @Provides
    PinCodeContract.Presenter providePinCodePresenter(Preferences preferences) {
        switch (pinCodeMode) {
            case CREATE:
                return new PinCodeCreatePresenter(preferences);
            case CHANGE:
                return new PinCodeChangePresenter(preferences);
            case CHECK:
                return new PinCodeCheckPresenter(preferences);
            default:
                return null;
        }
    }
}
