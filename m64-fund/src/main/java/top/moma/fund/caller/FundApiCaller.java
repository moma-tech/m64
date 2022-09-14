package top.moma.fund.caller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import top.moma.fund.ApiURIConstants;
import top.moma.m64.common.helper.json.JsonHelper;
import top.moma.m64.core.helper.StringHelper;
import top.moma.m64.core.helper.bean.BeanHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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

  FundApiCaller fundApiCaller =
      new Retrofit.Builder().baseUrl(ApiURIConstants.BASE_URL).build().create(FundApiCaller.class);

  static <T> T getResponse(
      Class<? extends Annotation> httpMethod,
      String callMethod,
      Object callParams,
      Class<T> targetClass)
      throws Exception {
    Method[] methods = FundApiCaller.class.getMethods();
    Call<ResponseBody> responseBodyCall = null;
    for (Method method : methods) {
      if (method.isAnnotationPresent(httpMethod)) {
        if (StringHelper.equals(method.getAnnotation(GET.class).value(), callMethod)) {
          responseBodyCall =
              (Call<ResponseBody>)
                  method.invoke(fundApiCaller, BeanHelper.beanToStringMap(callParams));
          return JsonHelper.readValue(responseBodyCall.execute().body().string(), targetClass);
        }
      }
    }
    return null;
  }
}
