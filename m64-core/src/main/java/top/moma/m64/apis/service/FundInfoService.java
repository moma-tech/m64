package top.moma.m64.apis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.m64.apis.caller.FundInfoCaller;
import top.moma.m64.apis.entity.vo.params.FundInfoParams;
import top.moma.m64.apis.entity.vo.response.FundInfoResponse;
import top.moma.m64.core.helper.json.JsonHelper;

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
  @Autowired FundInfoCaller fundInfoCaller;

  public String getFundInfo(String code) {
    FundInfoParams fundInfoParams = FundInfoParams.builder().code(code).build();
    FundInfoResponse fundInfoResponse = fundInfoCaller.getFundInfo(fundInfoParams);
    return JsonHelper.toJson(fundInfoParams);
  }
}
