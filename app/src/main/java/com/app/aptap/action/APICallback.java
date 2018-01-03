package com.app.aptap.action;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by ke41342 on 08-10-2017.
 */

public interface APICallback {
    public void onSuccess(Response<ResponseBody> response);
    public void onFailure(Throwable throwable);
}
