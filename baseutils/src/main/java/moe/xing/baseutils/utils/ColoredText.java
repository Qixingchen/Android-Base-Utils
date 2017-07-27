package moe.xing.baseutils.utils;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import moe.xing.baseutils.Init;

/**
 * Created by Qi Xingchen on 2017/7/27.
 * 获取染色的文字
 */

@SuppressWarnings("WeakerAccess")
public class ColoredText {
    /**
     * 获取染色的文字
     *
     * @param s        需要染色的文字
     * @param colorRes 颜色的资源
     */
    public static SpannableStringBuilder getColoredText(@NonNull String s, @ColorRes int colorRes) {
        return getColoredTextWithRawColor(s, ContextCompat.getColor(Init.getApplication(), colorRes));
    }

    /**
     * 获取染色的文字
     *
     * @param s     需要染色的文字
     * @param color 颜色(ColorInt)
     */
    public static SpannableStringBuilder getColoredTextWithRawColor(@NonNull String s, @ColorInt int color) {
        if (s.length() == 0) {
            return new SpannableStringBuilder();
        }
        SpannableStringBuilder message = new SpannableStringBuilder(s);
        message.setSpan(new ForegroundColorSpan(color),
                0, message.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return message;
    }
}
