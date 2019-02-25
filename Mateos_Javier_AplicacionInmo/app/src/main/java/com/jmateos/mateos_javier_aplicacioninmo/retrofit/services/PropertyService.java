package com.jmateos.mateos_javier_aplicacioninmo.retrofit.services;

import com.jmateos.mateos_javier_aplicacioninmo.model.PropertyDTO;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponse;
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
    Call<PropertyResponse> oneProperty(@Path("id") String id);

    @DELETE("properties/{id}")
    Call<PropertyResponse> deleteProperty(@Path("id") String id);

    @POST("properties")
    Call<PropertyResponse> addProperty(@Body PropertyDTO Property);

}