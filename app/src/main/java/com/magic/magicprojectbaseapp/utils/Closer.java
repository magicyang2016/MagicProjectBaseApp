package com.magic.magicprojectbaseapp.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/09
 *     desc   :关闭流相关工具类
 * </pre>
 */
public final class Closer {
    private Closer() {
        throw new UnsupportedOperationException("do not instantiation me"+"（Closer）");
    }

    /**
     * 关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                    closeable = null;
                } catch (IOException e) {
                    closeable = null;
                    LogUtils.e(e);
                }
            }
        }
    }

    /**
     * 安静关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIOQuietly(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                    closeable = null;
                } catch (IOException ignored) {
                    closeable = null;
                    LogUtils.e(ignored);
                }
            }
        }
    }
}
