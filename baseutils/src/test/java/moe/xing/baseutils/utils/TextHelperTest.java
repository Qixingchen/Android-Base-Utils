package moe.xing.baseutils.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Qi Xingchen on 17-4-24.
 */
public class TextHelperTest {
    @Test
    public void isVisible() throws Exception {
        assert TextHelper.isVisible("233");
        assertFalse(TextHelper.isVisible(""));
        assertFalse(TextHelper.isVisible("   "));
        assertFalse(TextHelper.isVisible("   \n"));
        assertFalse(TextHelper.isVisible(null));
    }

    @Test
    public void getNumberWithCommas() throws Exception {
        assertEquals("233", TextHelper.getNumberWithCommas("233"));
        assertEquals("233.01", TextHelper.getNumberWithCommas("233.01"));
        assertEquals("1,233", TextHelper.getNumberWithCommas("1233"));
        assertEquals("1,233.02", TextHelper.getNumberWithCommas("1233.02"));
        assertEquals("1,233.20", TextHelper.getNumberWithCommas("1233.20"));
        assertEquals("1,123,123,233", TextHelper.getNumberWithCommas("1123123233"));
    }

}