package ay3524.com.popularmovies.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ay3524.com.popularmovies.model.Casts;

/**
 * Created by Ashish on 04-12-2016.
 */

public class MovieResponseCasts {
    @SerializedName("cast")
    private List<Casts> casts;

    public List<Casts> getCasts() {
        return casts;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }
}
