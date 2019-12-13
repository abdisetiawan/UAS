package com.example.SI_KRS;

import com.example.SI_KRS.Model.Dosen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataMahasiswaService {
    @GET("/api/progmob/mhs/{nim_progmob}")
    Call<ArrayList<Dosen>> getMasiswaAll(@Path("nim_progmob") String nim_progmob);

    @FormUrlEncoded
    @POST("/api/progmob/mhs/create")
    Call<Dosen> insert_mahasiswa(
            @Field("nama") String nama,
            @Field("nim") String nidn,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("foto") String foto,
            @Field("nim_progmob") String nim_progmob
    );
}
