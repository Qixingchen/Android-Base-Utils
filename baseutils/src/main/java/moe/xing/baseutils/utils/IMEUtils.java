package moe.xing.baseutils.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import moe.xing.baseutils.Init;

/**
 * Created by Qi Xingchen on 16-10-14.
 */

public class IMEUtils {

    public static void hideIME(View view) {
        InputMethodManager imm = (InputMethodManager) Init.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
