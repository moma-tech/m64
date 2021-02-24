package top.moma.fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.fund.entity.vo.params.*;
import top.moma.fund.entity.vo.response.*;
import top.moma.fund.service.FundApiService;

/**
 * TestController
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@RestController
public class FundController {
  @Autowired FundApiService fundApiService;

  @GetMapping(value = "/test")
  public FundInfoResponse test(FundInfoParams fundInfoParams) {
    return fundApiService.getFundInfoResponse(fundInfoParams);
  }

  /** 获取所有基金 */
  @GetMapping(value = "/all")
  FundAllResponse getFundAllResponse(FundAllParams fundAllParams) {
    return fundApiService.getFundAllResponse(fundAllParams);
  }
  /** 获取热门基金 */
  @GetMapping(value = "/hot")
  FundHotResponse getFundHotResponse(FundHotParams fundHotParams) {
    return fundApiService.getFundHotResponse(fundHotParams);
  }
  /** 获取基金基础信息 */
  @GetMapping(value = "/info")
  FundInfoResponse getFundInfoResponse(FundInfoParams fundInfoParams) {
    return fundApiService.getFundInfoResponse(fundInfoParams);
  }
  /** 获取基金持仓详情 */
  @GetMapping(value = "/postion")
  FundPositionResponse getFundPositionResponse(FundPositionParams fundPositionParams) {
    return fundApiService.getFundPositionResponse(fundPositionParams);
  }
  /** 获取基金排行 */
  @GetMapping(value = "/rank")
  FundRankResponse getFundRankResponse(FundRankParams fundRankParams) {
    return fundApiService.getFundRankResponse(fundRankParams);
  }
  /** 获取基金详情(货币) */
  @GetMapping(value = "/monerary")
  MonetaryFundDetailResponse getMonetaryFundDetailResponse(FundDetailParams fundDetailParams) {
    return fundApiService.getMonetaryFundDetailResponse(fundDetailParams);
  }
  /** 获取基金详情(非货币) */
  @GetMapping(value = "/nonmonetary")
  NonMonetaryFundDetailResponse getNonMonetaryFundDetailResponse(
      FundDetailParams fundDetailParams) {
    return fundApiService.getNonMonetaryFundDetailResponse(fundDetailParams);
  }
}
