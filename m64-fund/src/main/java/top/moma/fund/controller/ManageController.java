package top.moma.fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.fund.entity.model.Fund;
import top.moma.fund.entity.model.FundPrice;
import top.moma.fund.entity.vo.params.FundDetailParams;
import top.moma.fund.entity.vo.params.FundInfoParams;
import top.moma.fund.entity.vo.response.FundInfoResponse;
import top.moma.fund.entity.vo.response.NonMonetaryFundDetailResponse;
import top.moma.fund.repo.FundPriceRepository;
import top.moma.fund.repo.FundRepository;
import top.moma.fund.service.FundApiService;
import top.moma.m64.core.helper.CollectionHelper;
import top.moma.m64.core.helper.StringHelper;
import top.moma.m64.core.helper.date.DateTimeHelper;

import java.time.LocalDate;
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
  @Autowired FundPriceRepository fundPriceRepository;

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

  @GetMapping(value = "/netWorth")
  public String queryNetWorth(String fundCode) {
    if (StringHelper.isNotBlank(fundCode)) {
      FundPrice fundPriceQuery =
          FundPrice.builder().fundCode(fundCode).netWorthDate(LocalDate.now().plusDays(-1)).build();
      Example<FundPrice> fundPricedExample = Example.of(fundPriceQuery);
      if (!fundPriceRepository.exists(fundPricedExample)) {
        FundInfoResponse fundInfoResponse =
            fundApiService.getFundInfoResponse(FundInfoParams.builder().code(fundCode).build());
        if (CollectionHelper.isNotEmpty(fundInfoResponse.getData())) {}
        FundPrice fundPrice =
            FundPrice.builder()
                .fundCode(fundCode)
                .netWorth(fundInfoResponse.getData().get(0).getNetWorth().toString())
                .netWorthDate(
                    DateTimeHelper.parseDate(fundInfoResponse.getData().get(0).getNetWorthDate()))
                .build();
        fundPriceRepository.save(fundPrice);
      }
    }
    return null;
  }
}
