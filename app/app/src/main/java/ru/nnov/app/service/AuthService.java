package ru.nnov.app.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.nnov.app.dto.AuthRequestDTO;
import ru.nnov.app.dto.JwtResponse;

public interface AuthService {
    @POST("/login")
    Call<JwtResponse> login(@Body AuthRequestDTO requestDTO);
}
