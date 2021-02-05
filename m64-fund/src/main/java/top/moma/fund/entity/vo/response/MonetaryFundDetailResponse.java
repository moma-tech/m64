package top.moma.fund.entity.vo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FundDetailResponse
 *
 * <p>//获取基金详情(货币基金)
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/8/21.
 */
@NoArgsConstructor
@Data
public class MonetaryFundDetailResponse {

  /**
   * code : 200 message : 操作成功 data :
   * {"code":"003171","name":"信达澳银慧理财货币","type":"货币型","lastMonthGrowth":"0.1","lastThreeMonthsGrowth":"0.28","lastSixMonthsGrowth":"0.7","lastYearGrowth":"1.98","buyMin":100,"buySourceRate":0,"buyRate":0,"manager":"孔学峰","fundScale":"0.20亿(2019-03-31)","millionCopiesIncomeData":[["2016-09-23",0.4773],["2016-09-25",0.9546]],"millionCopiesIncomeDate":"2019-06-27
   * 00:00","sevenDaysYearIncome":1.073,"sevenDaysYearIncomeDate":[["2016-09-23",2.131],["2016-09-25",1.706]]}
   * meta : 003171
   */
  private Integer code;

  private String message;
  private DataDTO data;
  private String meta;

  @NoArgsConstructor
  @Data
  public static class DataDTO {
    /**
     * code : 003171 name : 信达澳银慧理财货币 type : 货币型 lastMonthGrowth : 0.1 lastThreeMonthsGrowth : 0.28
     * lastSixMonthsGrowth : 0.7 lastYearGrowth : 1.98 buyMin : 100 buySourceRate : 0 buyRate : 0
     * manager : 孔学峰 fundScale : 0.20亿(2019-03-31) millionCopiesIncomeData :
     * [["2016-09-23",0.4773],["2016-09-25",0.9546]] millionCopiesIncomeDate : 2019-06-27 00:00
     * sevenDaysYearIncome : 1.073 sevenDaysYearIncomeDate :
     * [["2016-09-23",2.131],["2016-09-25",1.706]]
     */
    private String code;

    private String name;
    private String type;
    private String lastMonthGrowth;
    private String lastThreeMonthsGrowth;
    private String lastSixMonthsGrowth;
    private String lastYearGrowth;
    private Integer buyMin;
    private Integer buySourceRate;
    private Integer buyRate;
    private String manager;
    private String fundScale;
    private String millionCopiesIncomeDate;
    private Double sevenDaysYearIncome;
    private List<List<String>> millionCopiesIncomeData;
    private List<List<String>> sevenDaysYearIncomeDate;
  }
}
