package com.gamatechno.vrlib.strategy.projection;

import android.content.Context;

import com.gamatechno.vrlib.model.MDMainPluginBuilder;
import com.gamatechno.vrlib.model.MDPosition;
import com.gamatechno.vrlib.objects.MDAbsObject3D;
import com.gamatechno.vrlib.objects.MDCubeMap;
import com.gamatechno.vrlib.objects.MDObject3DHelper;
import com.gamatechno.vrlib.plugins.MDAbsPlugin;
import com.gamatechno.vrlib.plugins.MDPanoramaPlugin;

public class CubeProjection extends AbsProjectionStrategy {

    private MDAbsObject3D object3D;

    public CubeProjection() {

    }

    @Override
    public MDAbsObject3D getObject3D() {
        return object3D;
    }

    @Override
    public MDPosition getModelPosition() {
        return MDPosition.getOriginalPosition();
    }

    @Override
    public void turnOnInGL(Context context) {
        object3D = new MDCubeMap();
        MDObject3DHelper.loadObj(context, object3D);
    }

    @Override
    public void turnOffInGL(Context context) {
    }

    @Override
    public boolean isSupport(Context context) {
        return true;
    }

    @Override
    public MDAbsPlugin buildMainPlugin(MDMainPluginBuilder builder) {
        return new MDPanoramaPlugin(builder);
    }
}
