package com.gamatechno.vrlib.strategy.projection;

import android.content.Context;
import android.graphics.RectF;

import com.gamatechno.vrlib.model.MDMainPluginBuilder;
import com.gamatechno.vrlib.model.MDPosition;
import com.gamatechno.vrlib.objects.MDAbsObject3D;
import com.gamatechno.vrlib.objects.MDDome3D;
import com.gamatechno.vrlib.objects.MDObject3DHelper;
import com.gamatechno.vrlib.plugins.MDAbsPlugin;
import com.gamatechno.vrlib.plugins.MDPanoramaPlugin;

/**
 * Created by hzqiujiadi on 16/6/25.
 * hzqiujiadi ashqalcn@gmail.com
 */
public class DomeProjection extends AbsProjectionStrategy {

    MDAbsObject3D object3D;

    private float mDegree;

    private boolean mIsUpper;

    private RectF mTextureSize;

    public DomeProjection(RectF textureSize, float degree, boolean isUpper) {
        this.mTextureSize = textureSize;
        this.mDegree = degree;
        this.mIsUpper = isUpper;
    }

    @Override
    public void turnOnInGL(Context context) {
        object3D = new MDDome3D(mTextureSize, mDegree, mIsUpper);
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
    public MDAbsPlugin buildMainPlugin(MDMainPluginBuilder builder) {
        return new MDPanoramaPlugin(builder);
    }
}
