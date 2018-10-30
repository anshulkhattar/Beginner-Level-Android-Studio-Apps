package com.gamatechno.vrlib.plugins;

import com.gamatechno.vrlib.model.MDHotspotBuilder;
import com.gamatechno.vrlib.plugins.hotspot.MDSimpleHotspot;

/**
 * Created by hzqiujiadi on 16/8/2.
 * hzqiujiadi ashqalcn@gmail.com
 *
 * @deprecated MDHotspotPlugin
 *
 * Please use {@link MDSimpleHotspot} instead.
 */
@Deprecated
public class MDHotspotPlugin extends MDSimpleHotspot {

    public MDHotspotPlugin(MDHotspotBuilder builder) {
        super(builder);
    }
}
