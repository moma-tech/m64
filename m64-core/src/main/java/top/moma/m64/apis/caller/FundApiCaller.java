package top.moma.m64.apis.caller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import top.moma.m64.apis.ApiURIConstants;

import java.util.Map;

/**
 * FundApiCaller *
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
public interface FundApiCaller {
  @GET(ApiURIConstants.FUND_INFO)
  Call<ResponseBody> getFundInfo(@QueryMap Map<String, String> fundInfoParams);

  @GET(ApiURIConstants.FUND_POSITION)
  Call<ResponseBody> getFundPosition(@QueryMap Map<String, String> fundPositionParams);

  @GET(ApiURIConstants.FUND_RANK)
  Call<ResponseBody> getFundRank(@QueryMap Map<String, String> fundRankParams);

  @GET(ApiURIConstants.FUND_DETAIL)
  Call<ResponseBody> getFundDetail(@QueryMap Map<String, String> fundDetailParams);

  @GET(ApiURIConstants.FUND_ALL)
  Call<ResponseBody> getFundAll(@QueryMap Map<String, String> fundAllParams);

  @GET(ApiURIConstants.FUND_HOT)
  Call<ResponseBody> getFundHot(@QueryMap Map<String, String> fundHotParams);
}
