package by.enolizard.examplethree.app.dagger;

import by.enolizard.examplethree.app.ComponentsHolder;
import by.enolizard.examplethree.storage.Preferences;
import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, AppSubComponentsModule.class})
public interface AppComponent {
    void injectComponentsHolder(ComponentsHolder componentsHolder);
    Preferences getPreferences();
}
