package com.visenze.productcat.android.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testStringEmpty() {
        String blank = "";
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(blank));
        assertFalse(StringUtils.isEmpty("abc"));

        assertFalse(StringUtils.isNotEmpty(null));
        assertFalse(StringUtils.isNotEmpty(blank));
        assertTrue(StringUtils.isNotEmpty("x"));


    }
}
