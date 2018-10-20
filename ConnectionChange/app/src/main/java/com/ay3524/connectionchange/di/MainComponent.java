package com.ay3524.connectionchange.di;

import com.ay3524.connectionchange.MainActivity;

import dagger.Component;

@Component(modules = MainModule.class)
@MainScope
public interface MainComponent {
    void injectMainActvity(MainActivity mainActivity);
}
