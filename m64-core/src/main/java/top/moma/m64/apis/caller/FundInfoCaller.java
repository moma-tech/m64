package top.moma.m64.apis.caller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import top.moma.m64.apis.ApiURIConstants;

import java.util.Map;

/**
 * FundInfoCaller *
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
public interface FundInfoCaller {
  @GET(ApiURIConstants.FUND_INFO)
  Call<ResponseBody> getFundInfo(@QueryMap Map<String, String> fundInfoParams);
}
