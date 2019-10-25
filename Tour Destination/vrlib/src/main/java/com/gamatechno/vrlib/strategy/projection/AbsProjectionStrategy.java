package com.gamatechno.vrlib.strategy.projection;

import android.content.Context;

import com.gamatechno.vrlib.MD360DirectorFactory;
import com.gamatechno.vrlib.model.MDMainPluginBuilder;
import com.gamatechno.vrlib.plugins.MDAbsPlugin;
import com.gamatechno.vrlib.strategy.IModeStrategy;

/**
 * Created by hzqiujiadi on 16/6/25.
 * hzqiujiadi ashqalcn@gmail.com
 */
public abstract class AbsProjectionStrategy implements IModeStrategy, IProjectionMode {

    @Override
    public void onResume(Context context) {

    }

    @Override
    public void onPause(Context context) {

    }

    protected MD360DirectorFactory hijackDirectorFactory(){ return null; }

    abstract MDAbsPlugin buildMainPlugin(MDMainPluginBuilder builder);
}
