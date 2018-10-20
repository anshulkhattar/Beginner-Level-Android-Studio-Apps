package ay3524.com.popularmovies.response;

/**
 * Created by Ashish on 10-11-2016.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ay3524.com.popularmovies.model.Movies;


public class MovieResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private ArrayList<Movies> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movies> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movies> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
