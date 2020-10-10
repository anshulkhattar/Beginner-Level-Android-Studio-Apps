package com.amary.app.data.moviecat.provider;

        import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amary.app.data.moviecat.data.database.local.LocalDatabase;
import com.amary.app.data.moviecat.data.database.local.MovieDAO;

import java.util.Objects;

@SuppressLint("Registered")
public class ProviderFavorite extends ContentProvider {
    public static final String AUTHORITY = "com.amary.app.data.moviecat.provider";

    private static final int CODE_DIR = 1;
    private static final int CODE_ITEM = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "MOVIE_TB", CODE_DIR);
        MATCHER.addURI(AUTHORITY, "MOVIE_TB" + "/*", CODE_ITEM);
    }

    @Override
    public boolean onCreate() {
        LocalDatabase database = LocalDatabase.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] projection,
            @Nullable String selection,
            @Nullable String[] selectionArgs,
            @Nullable String sortOrder) {

        final int code = MATCHER.match(uri);
        if (code == CODE_DIR || code == CODE_ITEM){
            final Context context = getContext();

            if (context == null) {
                return null;
            }

            MovieDAO movieDAO = LocalDatabase.getInstance(context).movieDAO();
            Cursor cursor = null;

            if (code == CODE_DIR) {
                cursor = movieDAO.selectAllMovieFav();
            }
            Objects.requireNonNull(cursor).setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        }else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
