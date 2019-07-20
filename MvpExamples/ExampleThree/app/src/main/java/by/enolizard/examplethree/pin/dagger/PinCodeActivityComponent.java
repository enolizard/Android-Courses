package by.enolizard.examplethree.pin.dagger;

import dagger.Subcomponent;
import by.enolizard.examplethree.base.dagger.ActivityComponent;
import by.enolizard.examplethree.base.dagger.ActivityComponentBuilder;
import by.enolizard.examplethree.pin.mvp.PinCodeActivity;

@PinCodeActivityScope
@Subcomponent(modules = PinCodeActivityModule.class)
public interface PinCodeActivityComponent extends ActivityComponent<PinCodeActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<PinCodeActivityComponent, PinCodeActivityModule> {
    }
}
