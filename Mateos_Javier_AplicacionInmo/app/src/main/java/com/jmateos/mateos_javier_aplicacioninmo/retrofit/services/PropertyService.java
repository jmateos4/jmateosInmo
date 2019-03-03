package com.jmateos.mateos_javier_aplicacioninmo.retrofit.services;

import com.jmateos.mateos_javier_aplicacioninmo.model.PropertyDTO;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponse;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponseOne;
import com.jmateos.mateos_javier_aplicacioninmo.response.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PropertyService {

    @GET("properties")
    Call<ResponseContainer<PropertyResponse>> listProperties();

    @GET("properties/{id}")
    Call<PropertyResponseOne> oneProperty(@Path("id") String id);

    @DELETE("properties/{id}")
    Call<PropertyResponse> deleteProperty(@Path("id") String id);

    @POST("properties")
    Call<PropertyResponseOne> addProperty(@Body PropertyDTO Property);

    @GET("/properties/mine")
    Call<ResponseContainer<PropertyResponse>> getUserProperties();

    @GET("/properties/fav")
    Call<ResponseContainer<PropertyResponse>> getFavouritesProperties();

    @POST("/properties/fav/{id}")
    Call<PropertyResponse> addPropertiesFav(@Path("id") String id);

    @DELETE("/properties/fav/{id}")
    Call<PropertyResponse> deletePropertiesFav(@Path("id") String id);


}
