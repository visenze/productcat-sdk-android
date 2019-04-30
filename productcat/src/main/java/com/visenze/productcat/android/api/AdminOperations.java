package com.visenze.productcat.android.api;

import com.visenze.productcat.android.ProductCat;
import com.visenze.productcat.android.StoreParams;

public interface AdminOperations {
    public void getStores(StoreParams params, ProductCat.StoreResultListener storeResultListener);
}
