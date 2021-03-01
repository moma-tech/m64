package top.moma.fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.fund.entity.model.Fund;
import top.moma.fund.entity.vo.params.FundDetailParams;
import top.moma.fund.entity.vo.response.NonMonetaryFundDetailResponse;
import top.moma.fund.repo.FundRepository;
import top.moma.fund.service.FundApiService;
import top.moma.m64.core.helper.StringHelper;

import java.util.List;

/**
 * ManageController
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 3/1/21.
 */
@RestController
@RequestMapping("/man")
public class ManageController {
  @Autowired FundRepository fundRepository;
  @Autowired FundApiService fundApiService;

  @GetMapping(value = "/addNon")
  public String addNonMonetaryFund(String fundCode) {
    if (StringHelper.isNotBlank(fundCode)) {
      NonMonetaryFundDetailResponse nonMonetaryFundDetailResponse =
          fundApiService.getNonMonetaryFundDetailResponse(
              FundDetailParams.builder().code(fundCode).build());

      Fund newFund =
          Fund.builder()
              .fundCode(nonMonetaryFundDetailResponse.getData().getCode())
              .fundName(nonMonetaryFundDetailResponse.getData().getName())
              .fundType(nonMonetaryFundDetailResponse.getData().getType())
              .fundManager(nonMonetaryFundDetailResponse.getData().getManager())
              .fundScale(nonMonetaryFundDetailResponse.getData().getFundScale())
              .build();

      Fund fundQuery = Fund.builder().fundCode(fundCode).build();
      Example<Fund> fundExample = Example.of(fundQuery);
      if (fundRepository.exists(fundExample)) {
        List<Fund> fundList = fundRepository.findAll(fundExample);
        newFund.setId(fundList.get(0).getId());
      }

      fundRepository.save(newFund);
    }
    return null;
  }
}
