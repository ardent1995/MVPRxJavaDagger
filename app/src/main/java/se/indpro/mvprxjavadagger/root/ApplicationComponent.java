package se.indpro.mvprxjavadagger.root;

import javax.inject.Singleton;

import dagger.Component;
import se.indpro.mvprxjavadagger.login.LoginActivity;
import se.indpro.mvprxjavadagger.login.LoginModule;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);

}
