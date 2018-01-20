package aygalinc.fr.showremote;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.Consumer;

import aygalinc.fr.showremote.util.ConnectivityUtil;
import aygalinc.fr.showremote.util.HttpCallUtil;
import cz.msebera.android.httpclient.Header;

/**
 * Created by colin on 20/01/2018.
 */

public class TvServerClient {

    private final Activity parent;
    private static final String TAG = "TvServerClient";

    public TvServerClient(Activity parent) {
        this.parent = parent;
    }

    public void searchShows(final String showId, final Consumer<Long> idConsumer){
        Log.i(TAG,"launch search "+ showId);
        HttpCallUtil.getShows(showId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (response == null){
                    Log.i(TAG,"Result of show search is empty");
                    return;
                }

                try {
                    for (int i =0; i < response.length(); i++){
                        JSONObject jsonResponse = response.getJSONObject(i);
                        Long showId = jsonResponse.getLong("id");
                        Log.i(TAG,"show find with " + showId);
                        if (showId !=null){
                            idConsumer.accept(showId);
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Error during search request parsing of show " + showId, e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, java.lang.String responseString, java.lang.Throwable throwable) {
                if (!ConnectivityUtil.checkConnectivity(parent)) {
                    Log.e(TAG, "Search Request Fail due to missing internet connexion");
                    ConnectivityUtil.toast(parent, "No internet connection!");
                } else {

                    Log.e(TAG, "Search Request Fail due to exception", throwable);
                }
            }

            public void onFailure(int statusCode, Header[] headers, java.lang.Throwable throwable, JSONArray errorResponse) {
                if (!ConnectivityUtil.checkConnectivity(parent)) {
                    Log.e(TAG, "Search Request Fail due to missing internet connexion");
                    ConnectivityUtil.toast(parent, "No internet connection!");
                } else {
                    Log.e(TAG, "Search Request Fail due to JSONArray error response");
                }
            }
        });
    }

    public void getShow(final Long showId, final Consumer<Show> showConsumer){
        HttpCallUtil.getShow(showId.toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String showName = response.getString("name");

                    String showDescription = response.getString("summary");

                    if (showName == null || showDescription == null) {
                        return;
                    }
                    Show show = new Show(showId, showName, showDescription);
                    showConsumer.accept(show);
                } catch (JSONException e) {
                    Log.e(TAG, "Error during Show request parsing of show " + showId, e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, java.lang.String responseString, java.lang.Throwable throwable) {
                if (!ConnectivityUtil.checkConnectivity(parent)) {
                    Log.e(TAG, "Show Request Fail due to missing internet connexion");
                    ConnectivityUtil.toast(parent, "No internet connection!");
                } else {

                    Log.e(TAG, "Show Request Fail due to e", throwable);
                }
            }

            public void onFailure(int statusCode, Header[] headers, java.lang.Throwable throwable, JSONObject errorResponse) {
                if (!ConnectivityUtil.checkConnectivity(parent)) {
                    Log.e(TAG, "Show Request Fail due to missing internet connexion");
                    ConnectivityUtil.toast(parent, "No internet connection!");
                } else {
                    Log.e(TAG, "Show Request Fail due to json error response");
                }
            }
        });
    }
}
