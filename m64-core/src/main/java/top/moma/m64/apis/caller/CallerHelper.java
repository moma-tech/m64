package top.moma.m64.apis.caller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import top.moma.m64.core.helper.json.JsonHelper;

/**
 * CallerHelper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/11/21.
 */
public class CallerHelper<T> {
  public static <T> T getResponse(Call<ResponseBody> responseBodyCall, Class<T> targetClass)
      throws Exception {
    return JsonHelper.readValue(responseBodyCall.execute().body().string(), targetClass);
  }
}
