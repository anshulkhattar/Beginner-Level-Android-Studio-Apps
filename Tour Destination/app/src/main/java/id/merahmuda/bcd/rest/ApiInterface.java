package id.merahmuda.bcd.rest;

import id.merahmuda.bcd.model.response.ResponseEvent;
import id.merahmuda.bcd.model.response.ResponseGallery;
import id.merahmuda.bcd.model.response.ResponsePengaduan;
import id.merahmuda.bcd.model.response.ResponseProduk;
import id.merahmuda.bcd.model.response.ResponseReview;
import id.merahmuda.bcd.model.response.ResponseTenant;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ujang Wahyu on 02,Oktober,2018
 */
public interface ApiInterface {
    @GET("cemPSBdgCW")
    Call<ResponseTenant> tenant();

    @GET("cgBxOcVfma")
    Call<ResponseGallery> gallery();

    @GET("cgAsaZzSSq")
    Call<ResponseProduk> produk();

    @GET("cfPAdrkqBe")
    Call<ResponseEvent> event();

    @GET("cfkWoZgjFK")
    Call<ResponseReview> review();

    @GET("clGuzfUvLS")
    Call<ResponsePengaduan> pengaduan();
}
