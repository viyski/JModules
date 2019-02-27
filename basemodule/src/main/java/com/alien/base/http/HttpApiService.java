package com.alien.base.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface HttpApiService {

    @GET
    public Observable<Object> getFromHttpServer(@Url String interfaceUrl, @QueryMap Map<String, Object> options); 

    @POST
    public Observable<Object> postFromHttpServer(@Url String interfaceUrl, @Body Map<String, Object> options); 

    @PUT
    public Observable<Object> putFromHttpServer(@Url String interfaceUrl, @Body Map<String, Object> options);

    @DELETE
    public Observable<Object> deleteFromHttpServer(@Url String interfaceUrl);

    @GET
    public Observable<Void> getFromHttpServerVoid(@Url String interfaceUrl, @QueryMap Map<String, Object> options);

    @POST
    public Observable<Void> postFromHttpServerVoid(@Url String interfaceUrl, @Body Map<String, Object> options);

    @PUT
    public Observable<Void> putFromHttpServerVoid(@Url String interfaceUrl, @Body Map<String, Object> options);

    @DELETE
    public Observable<Void> deleteFromHttpServerVoid(@Url String interfaceUrl); 
}