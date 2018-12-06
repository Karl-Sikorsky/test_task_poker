package com.example.zemiapplication.openLinkPackage.network;


import com.example.zemiapplication.openLinkPackage.POJO.LinkResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("/openLink")
    Single<LinkResponse> getAllowResult();
}
