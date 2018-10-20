package ay3524.com.popularmovies.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ashish on 28-10-2016.
 */

public class Constants {

    //static final String LOG_TAG = "HELLO";
    public static final int MOVIES_LOADER_ID_ONE = 1;
    //public static final int MOVIES_LOADER_ID_ZERO = 0;
    public static final int GRID_TWO = 2;
    public static final int GRID_TRHEE = 3;

    public static final String BASE_URI = "http://api.themoviedb.org/3/";
    //private static final String TAG_POPULAR = "popular?";
    //private static final String TAG_TOP_RATED = "top_rated?";
    //public static final String TAG_VIDEOS = "/videos?";
    public static final String API_KEY = "c1283ff5cab3dfe3fd27e8a041795e4b";
    //public static final String FINAL_POPULAR_URI = BASE_URI+TAG_POPULAR+API_KEY;
    //public static final String FINAL_TOP_RATED_URI = BASE_URI+TAG_TOP_RATED+API_KEY;

    private static final String POSTER_URI = "http://image.tmdb.org/t/p/";
    private static final String POSTER_SIZE_W185 = "w185/";
    private static final String POSTER_SIZE_W342 = "w500/";
    public static final String FINAL_POSTER_URI = POSTER_URI+POSTER_SIZE_W185;
    public static final String FINAL_VIDEO_POSTER_URI = POSTER_URI+POSTER_SIZE_W342;

    public static final String BASE_YOUTUBE_URI = "https://www.youtube.com/watch?v=";

   // public static final String IMDB_TITLE_BASE_URI ="www.imdb.com/title/";

    public final static String ARG_POSITION = "position";

    //public final static String JSON_ARRAY_RESULT = "results";
    public final static String JSON_OBJECT_POSTER_PATH = "poster_path";
    public final static String JSON_OBJECT_OVERVIEW = "overview";
    public final static String JSON_OBJECT_RELEASE_DATE = "release_date";
    public final static String JSON_OBJECT_ID = "id";
    public final static String JSON_OBJECT_TITLE = "original_title";
    public final static String JSON_OBJECT_VOTE = "vote_average";
    public final static String JSON_OBJECT_BACKDROP_PATH = "backdrop_path";
    public final static String JSON_ARRAY_GENRE_ID = "genre_ids";

    public final static int ACTION_NUMBER = 28;
    public final static int ADVENTURE_NUMBER = 12;
    public final static int ANIMATION_NUMBER = 16;
    public final static int COMEDY_NUMBER = 35;
    public final static int CRIME_NUMBER = 80;
    public final static int DOCUMENTARY_NUMBER = 99;
    public final static int DRAMA_NUMBER = 18;
    public final static int FAMILY_NUMBER = 10751;
    public final static int FANTASY_NUMBER = 14;
    public final static int HISTORY_NUMBER = 36;
    public final static int HORROR_NUMBER = 27;
    public final static int MUSIC_NUMBER = 10402;
    public final static int MYSTERY_NUMBER = 9648;
    public final static int ROMANCE_NUMBER = 10749;
    public final static int SCIENCE_FICTION_NUMBER = 878;
    public final static int TV_MOVIE_NUMBER = 10770;
    public final static int THRILLER_NUMBER = 53;
    public final static int WAR_NUMBER = 10752;
    public final static int WESTERN_NUMBER = 37;

    public final static String ACTION_STRING = "Action";
    public final static String ADVENTURE_STRING = "Adventure";
    public final static String ANIMATION_STRING = "Animation";
    public final static String COMEDY_STRING = "Comedy";
    public final static String CRIME_STRING = "Crime";
    public final static String DOCUMENTARY_STRING = "Documentary";
    public final static String DRAMA_STRING = "Drama";
    public final static String FAMILY_STRING = "Family";
    public final static String FANTASY_STRING = "Fantasy";
    public final static String HISTORY_STRING = "History";
    public final static String HORROR_STRING = "Horror";
    public final static String MUSIC_STRING = "Music";
    public final static String MYSTERY_STRING = "Mystery";
    public final static String ROMANCE_STRING = "Romance";
    public final static String SCIENCE_FICTION_STRING = "Science Fiction";
    public final static String TV_MOVIE_STRING = "TV Movie";
    public final static String THRILLER_STRING = "Thriller";
    public final static String WAR_STRING = "War";
    public final static String WESTERN_STRING = "Western";

    public static boolean isConnected(Context context){
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    public static String convertDate(String date){
        String[] splitDate = date.split("-");
        String year = splitDate[0];
        String month = splitDate[1];
        String day = splitDate[2];
        int mm = Integer.parseInt(month);
        String monthString = getMonth(mm);
        return day+" "+monthString+", "+year;
    }
    private static String getMonth(int month){
        switch (month){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return String.valueOf(month);
    }
}
