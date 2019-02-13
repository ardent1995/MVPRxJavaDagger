package se.indpro.mvprxjavadagger.root;

import android.app.Application;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
