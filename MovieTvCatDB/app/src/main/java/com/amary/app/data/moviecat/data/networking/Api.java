package com.amary.app.data.moviecat.data.networking;

import com.amary.app.data.moviecat.data.networking.model.DetailMovie;
import com.amary.app.data.moviecat.data.networking.model.DetailTv;
import com.amary.app.data.moviecat.data.networking.model.DisMovieResponse;
import com.amary.app.data.moviecat.data.networking.model.DisTvResponse;
import com.amary.app.data.moviecat.data.networking.model.GetImageMovie;
import com.amary.app.data.moviecat.data.networking.model.GetImageTv;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("movie/now_playing")
    Observable<DisMovieResponse> getDisMovie(@Query("api_key") String api_key,
                                             @Query("language") String language);

    @GET("movie/now_playing")
    Call<DisMovieResponse> getMovieRelease(@Query("api_key") String api_key,
                                           @Query("language") String language);

    @GET("tv/on_the_air")
    Observable<DisTvResponse> getDisTv(@Query("api_key") String api_key,
                                 @Query("language") String language);

    @GET("movie/{movie_id}")
    Observable<DetailMovie> getDetailMovie(@Path("movie_id") int movie_id,
                                           @Query("api_key") String api_key,
                                           @Query("language") String language);

    @GET("tv/{tv_id}")
    Observable<DetailTv> getDetailTv(@Path("tv_id") int tv_id,
                                     @Query("api_key") String api_key,
                                     @Query("language") String language);

    @GET("movie/{movie_id}/images")
    Observable<GetImageMovie> getImageMovie(@Path("movie_id") int movie_id,
                                            @Query("api_key") String api_key,
                                            @Query("language") String language,
                                            @Query("include_image_language") String include_image_language);

    @GET("tv/{tv_id}/images")
    Observable<GetImageTv> getImageTv(@Path("tv_id") int tv_id,
                                      @Query("api_key") String api_key,
                                      @Query("language") String language,
                                      @Query("include_image_language") String include_image_language);

    @GET("search/movie")
    Observable<DisMovieResponse> getSearchMovie(@Query("api_key") String api_key,
                                          @Query("language") String language,
                                          @Query("query") String value);

    @GET("search/tv")
    Observable<DisTvResponse> getSearchTv(@Query("api_key") String api_key,
                                          @Query("language") String language,
                                          @Query("query") String value);
}
