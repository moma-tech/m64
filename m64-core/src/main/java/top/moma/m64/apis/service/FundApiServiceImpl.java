package top.moma.m64.apis.service;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import top.moma.m64.apis.ApiURIConstants;
import top.moma.m64.apis.caller.CallerHelper;
import top.moma.m64.apis.caller.FundApiCaller;
import top.moma.m64.apis.entity.vo.params.*;
import top.moma.m64.apis.entity.vo.response.*;
import top.moma.m64.core.helper.bean.BeanHelper;

import java.util.Map;

/**
 * FundApiServiceImpl
 *
 * <p>Implementation
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@Service
public class FundApiServiceImpl implements FundApiService {

  private FundApiCaller fundApiCaller;

  public FundApiServiceImpl() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiURIConstants.BASE_URL).build();
    fundApiCaller = retrofit.create(FundApiCaller.class);
  }

  @Override
  public FundAllResponse getFundAllResponse(FundAllParams fundAllParams) {
    return null;
  }

  @Override
  public FundHotResponse getFundHotResponse(FundHotParams fundHotParams) {
    return null;
  }

  @Override
  public FundInfoResponse getFundInfoResponse(FundInfoParams fundInfoParams) {
    FundInfoResponse fundInfoResponse = null;
    Map<String, String> a = BeanHelper.beanToStringMap(fundInfoParams);
    Call<ResponseBody> b = fundApiCaller.getFundInfo(a);
    try {
      fundInfoResponse = CallerHelper.getResponse(b, FundInfoResponse.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fundInfoResponse;
  }

  @Override
  public FundPositionResponse getFundPositionResponse(FundPositionParams fundPositionParams) {
    return null;
  }

  @Override
  public FundRankResponse getFundRankResponse(FundRankParams fundRankParams) {
    return null;
  }

  @Override
  public MonetaryFundDetailResponse getMonetaryFundDetailResponse(
      FundDetailParams fundDetailParams) {
    return null;
  }

  @Override
  public NonMonetaryFundDetailResponse getNonMonetaryFundDetailResponse(
      FundDetailParams fundDetailParams) {
    return null;
  }
}
