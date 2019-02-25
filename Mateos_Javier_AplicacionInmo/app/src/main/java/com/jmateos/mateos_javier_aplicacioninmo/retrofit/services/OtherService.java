package com.jmateos.mateos_javier_aplicacioninmo.retrofit.services;

import com.jmateos.mateos_javier_aplicacioninmo.response.ResponseContainer;
import com.jmateos.mateos_javier_aplicacioninmo.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OtherService {


    @GET("/users")
    Call<ResponseContainer<User>> listUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") Long id);

}
