package com.example.userprofileandroidexam.di.components;


import android.app.Application;

import com.example.userprofileandroidexam.BaseApplication;
import com.example.userprofileandroidexam.di.builder.ActivityBuilder;
import com.example.userprofileandroidexam.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class
        , AppModule.class
        , ActivityBuilder.class
})
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    void inject(Application application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
