package moe.xing.baseutils.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.DecimalFormat;

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
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        return isGraphic(charSequence);
    }

    /**
     * copy from @see android.text.TextUtils
     */
    public static boolean isGraphic(CharSequence str) {
        final int len = str.length();
        for (int cp, i = 0; i < len; i += Character.charCount(cp)) {
            cp = Character.codePointAt(str, i);
            int gc = Character.getType(cp);
            if (gc != Character.CONTROL
                    && gc != Character.FORMAT
                    && gc != Character.SURROGATE
                    && gc != Character.UNASSIGNED
                    && gc != Character.LINE_SEPARATOR
                    && gc != Character.PARAGRAPH_SEPARATOR
                    && gc != Character.SPACE_SEPARATOR) {
                return true;
            }
        }
        return false;
    }

    /**
     * 为数字添加千分符
     *
     * @param number 数字
     * @return 加入了千分符号的数字
     */
    @NonNull
    public static String getNumberWithCommas(@Nullable String number) {
        try {
            double total = Double.parseDouble(number);
            DecimalFormat formatter;
            //noinspection ConstantConditions
            if (number.contains(".")) {
                formatter = new DecimalFormat("#,###.00");
            } else {
                formatter = new DecimalFormat("#,###");
            }

            return formatter.format(total);
        } catch (NullPointerException | NumberFormatException ignore) {
            return number == null ? "" : number;
        }
    }
}
