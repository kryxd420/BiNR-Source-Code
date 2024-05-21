package com.vungle.warren.network;

import com.google.gson.JsonObject;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface VungleApi {
    @POST("{ads}")
    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    Call<JsonObject> ads(@Header("User-Agent") String str, @Path(encoded = true, value = "ads") String str2, @Body JsonObject jsonObject);

    @POST("config")
    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    Call<JsonObject> config(@Header("User-Agent") String str, @Body JsonObject jsonObject);

    @GET
    Call<ResponseBody> pingTPAT(@Header("User-Agent") String str, @Url String str2);

    @POST("{report_ad}")
    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    Call<JsonObject> reportAd(@Header("User-Agent") String str, @Path(encoded = true, value = "report_ad") String str2, @Body JsonObject jsonObject);

    @GET("{new}")
    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    Call<JsonObject> reportNew(@Header("User-Agent") String str, @Path(encoded = true, value = "new") String str2, @QueryMap Map<String, String> map);

    @POST("{ri}")
    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    Call<JsonObject> ri(@Header("User-Agent") String str, @Path(encoded = true, value = "ri") String str2, @Body JsonObject jsonObject);

    @POST("{will_play_ad}")
    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    Call<JsonObject> willPlayAd(@Header("User-Agent") String str, @Path(encoded = true, value = "will_play_ad") String str2, @Body JsonObject jsonObject);
}
