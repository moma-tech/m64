package top.moma.fund.entity.vo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FundHotResponse
 *
 * <p>//获取热门基金
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/8/21.
 */
@NoArgsConstructor
@Data
public class FundHotResponse {

  /**
   * code : 200 message : 操作成功 data :
   * [{"code":"110022","name":"易方达消费行业","fundType":"股票型","netWorthDate":"2020-07-01","netWorth":"2.7810","dayGrowth":"2.1675","lastWeekGrowth":"2.7716","lastMonthGrowth":"2.7716","lastThreeMonthsGrowth":"15.2507","lastSixMonthsGrowth":"57.3854","lastYearGrowth":"18.8970","thisYearGrowth":"55.8857"},{"code":"000991","name":"工银战略转型股票","fundType":"股票型","netWorthDate":"2020-07-01","netWorth":"1.2390","dayGrowth":"1.0604","lastWeekGrowth":"2.6512","lastMonthGrowth":"2.6512","lastThreeMonthsGrowth":"0.1617","lastSixMonthsGrowth":"28.5270","lastYearGrowth":"12.0253","thisYearGrowth":"28.5270"},{"code":"070018","name":"嘉实回报混合","fundType":"股票型","netWorthDate":"2020-07-01","netWorth":"1.2440","dayGrowth":"1.8837","lastWeekGrowth":"2.3868","lastMonthGrowth":"2.3868","lastThreeMonthsGrowth":"9.0272","lastSixMonthsGrowth":"34.4864","lastYearGrowth":"8.3624","thisYearGrowth":"33.9074"}]
   * meta : {"pageIndex":1,"pageSize":3}
   */
  private Integer code;

  private String message;
  private MetaDTO meta;
  private List<DataDTO> data;

  @NoArgsConstructor
  @Data
  public static class MetaDTO {
    /** pageIndex : 1 pageSize : 3 */
    private Integer pageIndex;

    private Integer pageSize;
  }

  @NoArgsConstructor
  @Data
  public static class DataDTO {
    /**
     * code : 110022 name : 易方达消费行业 fundType : 股票型 netWorthDate : 2020-07-01 netWorth : 2.7810
     * dayGrowth : 2.1675 lastWeekGrowth : 2.7716 lastMonthGrowth : 2.7716 lastThreeMonthsGrowth :
     * 15.2507 lastSixMonthsGrowth : 57.3854 lastYearGrowth : 18.8970 thisYearGrowth : 55.8857
     */
    private String code;

    private String name;
    private String fundType;
    private String netWorthDate;
    private String netWorth;
    private String dayGrowth;
    private String lastWeekGrowth;
    private String lastMonthGrowth;
    private String lastThreeMonthsGrowth;
    private String lastSixMonthsGrowth;
    private String lastYearGrowth;
    private String thisYearGrowth;
  }
}
