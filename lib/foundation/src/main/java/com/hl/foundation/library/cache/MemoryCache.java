package com.hl.foundation.library.cache;

import java.util.List;

/**
 * Created by ${Stephen} on 2017-05-23.
 */

public class MemoryCache<D> {
    private MemoryCache() {
    }

    private static MemoryCache memoryCache;

    public static synchronized MemoryCache getInstance() {
        if (null == memoryCache) {
            memoryCache = new MemoryCache();
        }
        return memoryCache;
    }

    public List<D> deviceItemVOs;
}
