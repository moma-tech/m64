package top.moma.m64.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.m64.apis.service.FundInfoService;

/**
 * TestController
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@RestController
public class TestController {
  @Autowired FundInfoService fundInfoService;

  @GetMapping(value = "/test")
  public String test() {
    return fundInfoService.getFundInfo("202015,007339");
  }
}