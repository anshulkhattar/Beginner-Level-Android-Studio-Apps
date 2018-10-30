package com.gamatechno.vrlib.compact;

import com.gamatechno.vrlib.MDVRLibrary;
import com.gamatechno.vrlib.model.MDHitEvent;

/**
 * Created by hzqiujiadi on 2017/4/20.
 * hzqiujiadi ashqalcn@gmail.com
 */

public class CompactEyePickAdapter implements MDVRLibrary.IEyePickListener2 {

    private final MDVRLibrary.IEyePickListener listener;

    public CompactEyePickAdapter(MDVRLibrary.IEyePickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onHotspotHit(MDHitEvent hitEvent) {
        if (listener != null){
            listener.onHotspotHit(hitEvent.getHotspot(), hitEvent.getTimestamp());
        }
    }
}
