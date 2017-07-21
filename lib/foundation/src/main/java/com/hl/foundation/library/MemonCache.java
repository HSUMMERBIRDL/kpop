package com.hl.foundation.library;

import java.util.List;

/**
 * Created by ${Stephen} on 2017-05-25.
 * 基本缓存类
 */

public class MemonCache<D> {
    private static MemonCache memonCache;

    private MemonCache() {
    }

    public static synchronized MemonCache getIntance() {
        if (null == memonCache) {
            memonCache = new MemonCache();
        }
        return memonCache;
    }

    public List<D> list;
}
