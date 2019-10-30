package com.gamatechno.vrlib.plugins.hotspot;

import com.gamatechno.vrlib.model.MDHitEvent;
import com.gamatechno.vrlib.model.MDHitPoint;
import com.gamatechno.vrlib.model.MDRay;

/**
 * Created by hzqiujiadi on 16/8/5.
 * hzqiujiadi ashqalcn@gmail.com
 */
public interface IMDHotspot {
    MDHitPoint hit(MDRay ray);

    void onEyeHitIn(MDHitEvent hitEvent);

    /**
     * @param timestamp down timestamp
     * */
    void onEyeHitOut(long timestamp);

    void onTouchHit(MDRay ray);

    String getTitle();

    String getTag();

    void rotateToCamera();
}
