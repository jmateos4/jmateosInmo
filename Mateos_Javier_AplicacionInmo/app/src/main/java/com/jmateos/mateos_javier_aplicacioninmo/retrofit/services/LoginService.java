package com.jmateos.mateos_javier_aplicacioninmo.retrofit.services;

import com.jmateos.mateos_javier_aplicacioninmo.model.LoginResponse;
import com.jmateos.mateos_javier_aplicacioninmo.model.Registro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth")
    Call<LoginResponse> doLogin(@Header("Authorization") String authorization);

    @POST("/users")
    Call<LoginResponse> doRegister(@Body Registro registro);




}
