package com.visenze.productcat.android.util;

import com.visenze.productcat.android.model.ProductType;
import com.visenze.productcat.android.model.ResultList;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SecurityHelperTest {

    @Test
    public void MD5Test() {

        String input="TEST";

        String res = SecurityHelper.MD5(input);
        assertEquals("033bd94b1168d7e4f0d644c3c95e35bf", res);

    }
}
