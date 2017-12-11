package com.esegou.android;

import com.esegou.android.main.shop.Goods;
import com.esegou.android.test.Data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Shurrik on 2017/11/15.
 */

public interface HttpService {
    @GET("alipay_barcode_mc/test/json.do")
    Observable<HttpResult<Data>> test(@Query("message") String message);

    @POST("api/shop/goods/search.do")
    Observable<HttpResult<Goods>> search(@Query("message") String keyword);
}
