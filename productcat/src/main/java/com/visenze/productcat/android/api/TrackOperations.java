package com.visenze.productcat.android.api;

import com.visenze.productcat.android.TrackParams;

/**
 * Created by visenze on 26/2/16.
 */
public interface TrackOperations {
    /**
     * Event Track (GET /__aq.gif)
     * Send tracking event
     *
     * @param trackParams tracking parameters
     */
    void track(TrackParams trackParams);
}
