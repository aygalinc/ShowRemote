package aygalinc.fr.showremote.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by colin on 20/01/2018.
 */

public class HttpCallUtil {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getShows(String showNameSearch, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.add("search",showNameSearch);
        client.get(TvServerPath.SHOWS_SEARCH_PATH, params, responseHandler);
    }

    public static void getShow(String showId,  AsyncHttpResponseHandler responseHandler) {
        client.get(TvServerPath.SHOW_PATH+showId, responseHandler);
    }

    public static void getShowImage(String showId,  AsyncHttpResponseHandler responseHandler) {
        client.get(TvServerPath.SHOW_PATH+showId+TvServerPath.SHOW_IMAGE_RELATIVE_PATH, responseHandler);
    }
}
