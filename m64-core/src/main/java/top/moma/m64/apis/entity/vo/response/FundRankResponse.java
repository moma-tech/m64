package top.moma.m64.apis.entity.vo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FundRankResponse
 *
 * <p>//获取基金排行
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/8/21.
 */
@NoArgsConstructor
@Data
public class FundRankResponse {

  /**
   * code : 200 message : 操作成功 data :
   * {"pageIndex":1,"pageSize":2,"allPages":3156,"rank":[{"code":"980003","name":"太平洋六个月滚动持有债","netWorth":"1.4019","netWorthDate":"2020-07-22","fundType":"债券型","dayGrowth":"0.0285","lastWeekGrowth":"0.2503","lastMonthGrowth":"0.2144","lastThreeMonthsGrowth":"","lastSixMonthsGrowth":"","lastYearGrowth":"","thisYearGrowth":""},{"code":"970005","name":"安信瑞鸿中短债C","netWorth":"1.0229","netWorthDate":"2020-07-21","fundType":"债券型","dayGrowth":"0.0293","lastWeekGrowth":"0.1175","lastMonthGrowth":"-0.0293","lastThreeMonthsGrowth":"","lastSixMonthsGrowth":"","lastYearGrowth":"","thisYearGrowth":""}]}
   * meta : null
   */
  private Integer code;

  private String message;
  private DataDTO data;
  private Object meta;

  @NoArgsConstructor
  @Data
  public static class DataDTO {
    /**
     * pageIndex : 1 pageSize : 2 allPages : 3156 rank :
     * [{"code":"980003","name":"太平洋六个月滚动持有债","netWorth":"1.4019","netWorthDate":"2020-07-22","fundType":"债券型","dayGrowth":"0.0285","lastWeekGrowth":"0.2503","lastMonthGrowth":"0.2144","lastThreeMonthsGrowth":"","lastSixMonthsGrowth":"","lastYearGrowth":"","thisYearGrowth":""},{"code":"970005","name":"安信瑞鸿中短债C","netWorth":"1.0229","netWorthDate":"2020-07-21","fundType":"债券型","dayGrowth":"0.0293","lastWeekGrowth":"0.1175","lastMonthGrowth":"-0.0293","lastThreeMonthsGrowth":"","lastSixMonthsGrowth":"","lastYearGrowth":"","thisYearGrowth":""}]
     */
    private Integer pageIndex;

    private Integer pageSize;
    private Integer allPages;
    private List<RankDTO> rank;

    @NoArgsConstructor
    @Data
    public static class RankDTO {
      /**
       * code : 980003 name : 太平洋六个月滚动持有债 netWorth : 1.4019 netWorthDate : 2020-07-22 fundType : 债券型
       * dayGrowth : 0.0285 lastWeekGrowth : 0.2503 lastMonthGrowth : 0.2144 lastThreeMonthsGrowth :
       * lastSixMonthsGrowth : lastYearGrowth : thisYearGrowth :
       */
      private String code;

      private String name;
      private String netWorth;
      private String netWorthDate;
      private String fundType;
      private String dayGrowth;
      private String lastWeekGrowth;
      private String lastMonthGrowth;
      private String lastThreeMonthsGrowth;
      private String lastSixMonthsGrowth;
      private String lastYearGrowth;
      private String thisYearGrowth;
    }
  }
}
