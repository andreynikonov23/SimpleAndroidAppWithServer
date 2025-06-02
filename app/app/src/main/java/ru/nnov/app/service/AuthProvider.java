package ru.nnov.app.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nnov.app.dto.AuthRequestDTO;
import ru.nnov.app.dto.JwtResponse;
import ru.nnov.app.model.User;

public class AuthProvider {

    public static void test() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws001:8089/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AuthService authService = retrofit.create(AuthService.class);

        AuthRequestDTO request = new AuthRequestDTO("nikonov.as", "$2a$12$E5XPrSC.z4V9tSh4NqGtDOf3uEJ6cGpj9cStkH2GlVXiyowMDxOHG");
        Call<JwtResponse> call = authService.login(request);

        try {
            JwtResponse response = call.execute().body();
            if (response == null) {
                Log.i("TEST", "response is null");
            } else {
                Log.i("TEST", response.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
