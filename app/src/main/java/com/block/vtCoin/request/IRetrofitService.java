package com.block.vtCoin.request;

import com.block.vtCoin.entity.Page;
import com.block.vtCoin.entity.PageEntity;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface IRetrofitService {

    @GET
    Observable<ResponseBody> getAction(
            @Url String url);

    @GET
    Observable<ResponseBody> getPara(
            @Url String url,
            @QueryMap Map<String, Object> map);

    @Multipart
    @POST
    Observable<ResponseBody> uploadImage(
            @Url String url,
            @Query("ImageType") String imageType,
            @Part("file\"; filename=\"Images\"") RequestBody file);

    @Multipart
    @POST
    Observable<ResponseBody> uploadImage(
            @Url String url,
            @Query("ImageType") String imageType,
            @Part MultipartBody.Part file);

    // @Part 和 @PartMap 多用于上传文件，需要添加@Multipart 标记
    @Multipart
    @POST("upload")
    Observable<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    // 上传多个文件
    @Multipart
    @POST("upload")
    Observable<ResponseBody> uploadMultipleFiles(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2);

    /**
     * @FieldMap 放在RequestBody请求体里面，需要添加标记，@FormUrlEncoded，
     * 如：EmailOrPhoneNumber=252916344%40qq.com&Password=admin1234 以这种形式读出来
     */
    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> postPara(
            @Url String url,
            @FieldMap Map<String, Object> parameters);

    /**
     * @QueryMap 拼接在url后面, 与@FieldMap其实是一样的，
     * 如：http://1711255wc1.iok.la/Account/Login？EmailOrPhoneNumber=252916344%40qq.com&Password=admin1234
     */
    @POST()
    Observable<ResponseBody> postParaQuery(
            @Url String url,
            @QueryMap Map<String, Object> parameters);


    /**
     * 传数组，必须用@Field,
     */
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postArray(@Url String url, @Field("Names[]") String[] names);

    @POST()
    Observable<ResponseBody> postAction(
            @Url String url);

    /**
     * 被@Body注解的的paging将会被gson 转换成json字符串发送到服务器。
     * 如：{"ListNumber":10,"PageIndex":1}
     */
    @POST
    Observable<ResponseBody> postBodyPageEntity(
            @Url String url,
            @Body PageEntity page);

    /**
     * 被@Body注解的的paging将会被gson 转换成json字符串发送到服务器。
     * 如：{"ListNumber":10,"PageIndex":1}
     */
    @POST
    Observable<ResponseBody> postBodyPage(
            @Url String url,
            @Body Page page);

    /**
     * 被@Body注解的的page将会被gson 转换成RequestBody发送到服务器。
     * 如：{"ListNumber":10,"PageIndex":1}
     *
     * @Body注解不能和 @FormUrlEncoded标记 以及 @Multipart 标记一起使用，所以此处传参只能使用@QueryMap或者@Query
     */
    @POST
    Observable<ResponseBody> postParaBody(
            @Url String url,
            @QueryMap Map<String, Object> parameters,
            @Body Page page);

}
