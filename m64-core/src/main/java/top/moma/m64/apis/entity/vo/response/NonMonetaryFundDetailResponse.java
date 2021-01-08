package top.moma.m64.apis.entity.vo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * NonMonetaryFundDetailResponse
 *
 * <p>//获取基金详情(非货币基金)
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/8/21.
 */
@NoArgsConstructor
@Data
public class NonMonetaryFundDetailResponse {
  /**
   * code : 200 message : 操作成功 data :
   * {"code":"000001","name":"华夏成长","type":"混合型","netWorth":1.09,"expectWorth":1.104,"totalWorth":3.501,"expectGrowth":"1.29","dayGrowth":"0.1838","lastWeekGrowth":"0.1828","lastMonthGrowth":"3.02","lastThreeMonthsGrowth":"-0.55","lastSixMonthsGrowth":"18.87","lastYearGrowth":"3.12","buyMin":"100","buySourceRate":"1.5","buyRate":"0.15","manager":"董阳阳","fundScale":"49.91亿(2019-03-31)","netWorthDate":"2019-06-26","expectWorthDate":"2019-06-27
   * 15:00","netWorthData":[["2001-12-18",1,0,""],["2001-12-21",1,0,""]]} meta : 000001
   */
  private Integer code;

  private String message;
  private DataDTO data;
  private String meta;

  @NoArgsConstructor
  @Data
  public static class DataDTO {
    /**
     * code : 000001 name : 华夏成长 type : 混合型 netWorth : 1.09 expectWorth : 1.104 totalWorth : 3.501
     * expectGrowth : 1.29 dayGrowth : 0.1838 lastWeekGrowth : 0.1828 lastMonthGrowth : 3.02
     * lastThreeMonthsGrowth : -0.55 lastSixMonthsGrowth : 18.87 lastYearGrowth : 3.12 buyMin : 100
     * buySourceRate : 1.5 buyRate : 0.15 manager : 董阳阳 fundScale : 49.91亿(2019-03-31) netWorthDate
     * : 2019-06-26 expectWorthDate : 2019-06-27 15:00 netWorthData :
     * [["2001-12-18",1,0,""],["2001-12-21",1,0,""]]
     */
    private String code;

    private String name;
    private String type;
    private Double netWorth;
    private Double expectWorth;
    private Double totalWorth;
    private String expectGrowth;
    private String dayGrowth;
    private String lastWeekGrowth;
    private String lastMonthGrowth;
    private String lastThreeMonthsGrowth;
    private String lastSixMonthsGrowth;
    private String lastYearGrowth;
    private String buyMin;
    private String buySourceRate;
    private String buyRate;
    private String manager;
    private String fundScale;
    private String netWorthDate;
    private String expectWorthDate;
    private List<List<String>> netWorthData;
  }
}
