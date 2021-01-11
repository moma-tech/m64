package top.moma.m64.apis.service;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import top.moma.m64.apis.ApiURIConstants;
import top.moma.m64.apis.caller.CallerHelper;
import top.moma.m64.apis.caller.FundInfoCaller;
import top.moma.m64.apis.entity.vo.params.FundInfoParams;
import top.moma.m64.apis.entity.vo.response.FundInfoResponse;
import top.moma.m64.core.helper.bean.BeanHelper;
import top.moma.m64.core.helper.json.JsonHelper;

import java.io.IOException;
import java.util.Map;

/**
 * FundInfoService
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@Service
public class FundInfoService {

  public String getFundInfo(String code) throws IOException {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiURIConstants.BASE_URL).build();
    FundInfoCaller fundInfoCaller = retrofit.create(FundInfoCaller.class);

    FundInfoParams fundInfoParams = FundInfoParams.builder().code(code).build();
    Object fundInfoResponse =
        fundInfoCaller.getFundInfo(BeanHelper.beanToStringMap(fundInfoParams)).execute().body();
    return JsonHelper.toJson(fundInfoParams);
  }

  public static void main(String[] args) {
    Retrofit retrofit =
        new Retrofit.Builder()
            // .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(ApiURIConstants.BASE_URL)
            .build();
    FundInfoCaller fundInfoCaller = retrofit.create(FundInfoCaller.class);
    FundInfoParams fundInfoParams = FundInfoParams.builder().code("000001").build();
    FundInfoResponse fundInfoResponse = null;
    Map<String, String> a = BeanHelper.beanToStringMap(fundInfoParams);
    Call<ResponseBody> b = fundInfoCaller.getFundInfo(a);
    try {
      FundInfoResponse testResponse = CallerHelper.getResponse(b, FundInfoResponse.class);
      System.out.println(testResponse.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
    /*   b.enqueue(
    new Callback<ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
          // System.out.println(response.body().string());
          TestResponse testResponse =
              JsonHelper.readValue(response.body().string(), TestResponse.class);
          System.out.println(testResponse.getData().getCode());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t) {
        t.printStackTrace();
      }
    });*/
  }
}
