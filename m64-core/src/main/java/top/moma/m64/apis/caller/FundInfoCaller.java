package top.moma.m64.apis.caller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import top.moma.m64.apis.ApiURIConstatns;
import top.moma.m64.apis.entity.vo.params.FundInfoParams;
import top.moma.m64.apis.entity.vo.response.FundInfoResponse;

/**
 * FundInfoCaller
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@FeignClient(value = ApiURIConstatns.BASE_URL)
public interface FundInfoCaller {
  @GetMapping(value = ApiURIConstatns.FUND_INFO)
  FundInfoResponse getFundInfo(FundInfoParams fundInfoParams);
}
