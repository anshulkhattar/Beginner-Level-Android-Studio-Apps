package ay3524.com.popularmovies.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ay3524.com.popularmovies.model.Review;

/**
 * Created by Ashish on 12-11-2016.
 */

public class MovieResponseReviews {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Review> review;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public List<Review> getReview() {
        return review;
    }

    public void setVideos(List<Review> review) {
        this.review = review;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

