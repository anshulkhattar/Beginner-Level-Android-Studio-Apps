package com.gamatechno.vrlib.strategy.projection;

import android.content.Context;

import com.gamatechno.vrlib.MD360Director;
import com.gamatechno.vrlib.MD360DirectorFactory;
import com.gamatechno.vrlib.common.MDDirection;
import com.gamatechno.vrlib.model.MDMainPluginBuilder;
import com.gamatechno.vrlib.model.MDPosition;
import com.gamatechno.vrlib.objects.MDAbsObject3D;
import com.gamatechno.vrlib.objects.MDObject3DHelper;
import com.gamatechno.vrlib.objects.MDStereoSphere3D;
import com.gamatechno.vrlib.plugins.MDAbsPlugin;
import com.gamatechno.vrlib.plugins.MDPanoramaPlugin;

/**
 * Created by hzqiujiadi on 16/6/26.
 * hzqiujiadi ashqalcn@gmail.com
 */
public class StereoSphereProjection extends AbsProjectionStrategy {

    private static class FixedDirectorFactory extends MD360DirectorFactory{
        @Override
        public MD360Director createDirector(int index) {
            return MD360Director.builder().build();
        }
    }

    private MDDirection direction;

    private MDAbsObject3D object3D;

    public StereoSphereProjection(MDDirection direction) {
        this.direction = direction;
    }

    @Override
    public void turnOnInGL(Context context) {
        object3D = new MDStereoSphere3D(direction);
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
    public MDAbsObject3D getObject3D() {
        return object3D;
    }

    @Override
    public MDPosition getModelPosition() {
        return MDPosition.getOriginalPosition();
    }

    @Override
    protected MD360DirectorFactory hijackDirectorFactory() {
        return new FixedDirectorFactory();
    }

    @Override
    public MDAbsPlugin buildMainPlugin(MDMainPluginBuilder builder) {
        return new MDPanoramaPlugin(builder);
    }
}
