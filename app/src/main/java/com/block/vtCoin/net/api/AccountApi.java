package com.block.vtCoin.net.api;
import com.block.vtCoin.entity1.HttpResult;
import com.block.vtCoin.entity1.LoginEntity;
import com.block.vtCoin.request.ApiAction;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface AccountApi {
    @POST(ApiAction.Account_Login)
    Observable<HttpResult<LoginEntity>> login(
            @Query("EmailOrPhoneNumber") String phone,
            @Query("Password") String password);

}