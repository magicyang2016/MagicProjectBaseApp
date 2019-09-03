package com.magic.magicprojectbaseapp.utils.exception;

import android.text.TextUtils;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/05
 *     desc   :url无效异常
 * </pre>
 */
public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String url) {
        super("You've configured an invalid url : " + (TextUtils.isEmpty(url) ? "EMPTY_OR_NULL_URL" : url));
    }
}
