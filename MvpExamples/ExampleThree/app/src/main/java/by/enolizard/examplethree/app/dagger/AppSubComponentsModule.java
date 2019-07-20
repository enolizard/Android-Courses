package by.enolizard.examplethree.app.dagger;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import by.enolizard.examplethree.base.dagger.ActivityComponentBuilder;
import by.enolizard.examplethree.pin.dagger.PinCodeActivityComponent;
import by.enolizard.examplethree.pin.mvp.PinCodeActivity;

@Module(subcomponents = {PinCodeActivityComponent.class})
public class AppSubComponentsModule {

    @Provides
    @IntoMap
    @ClassKey(PinCodeActivity.class)
    ActivityComponentBuilder provideSplashViewBuilder(PinCodeActivityComponent.Builder builder) {
        return builder;
    }
}
