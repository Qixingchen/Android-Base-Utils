package moe.xing.baseutils.utils;

import android.support.annotation.Nullable;

/**
 * Created by Qi Xingchen on 16-11-7.
 */

public class TextHelper {

    /**
     * 判断字符串是否可见
     *
     * @param charSequence 字符串
     * @return {@code true} 可见
     */
    public static boolean isVisible(@Nullable CharSequence charSequence) {
        //noinspection SimplifiableIfStatement
        if (android.text.TextUtils.isEmpty(charSequence)) {
            return false;
        }
        return android.text.TextUtils.isGraphic(charSequence);
    }
}
