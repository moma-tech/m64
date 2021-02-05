package top.moma.fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.fund.entity.vo.params.FundInfoParams;
import top.moma.fund.entity.vo.response.FundInfoResponse;
import top.moma.fund.service.FundApiService;

/**
 * TestController
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@RestController
public class TestController {
  @Autowired FundApiService fundApiService;

  @GetMapping(value = "/info")
  public FundInfoResponse test(FundInfoParams fundInfoParams) {
    return fundApiService.getFundInfoResponse(fundInfoParams);
  }
}
