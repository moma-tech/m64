package top.moma.m64.apis.entity.vo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FundInfoResponse
 *
 * <p>获取基金基础信息 Response
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@NoArgsConstructor
@Data
public class FundInfoResponse {

  /**
   * code : 200 message : 操作成功 data :
   * [{"code":"202015","name":"南方沪深300联接基金A","type":"指数型","netWorth":1.51,"expectWorth":1.5095,"expectGrowth":"-0.31","dayGrowth":"-0.2774","lastWeekGrowth":"0.1828","lastMonthGrowth":"3.0998","lastThreeMonthsGrowth":"-1.6158","lastSixMonthsGrowth":"1.5535","lastYearGrowth":"8.6409","netWorthDate":"2020-05-15","expectWorthDate":"2020-05-15
   * 15:00:00"},{"code":"007339","name":"易方达沪深300ETF联接C","netWorth":1.3676,"expectWorth":1.3673,"expectGrowth":"-0.30","dayGrowth":"-0.2771","lastWeekGrowth":"0.1828","lastMonthGrowth":"3.0906","lastThreeMonthsGrowth":"-1.6256","lastSixMonthsGrowth":"1.2663","lastYearGrowth":"6.9942","netWorthDate":"2020-05-15","expectWorthDate":"2020-05-15
   * 15:00:00"}] meta : 202015,007339
   */
  private Integer code;

  private String message;
  private String meta;
  private List<DataDTO> data;

  @NoArgsConstructor
  @Data
  public static class DataDTO {
    /**
     * code : 202015 name : 南方沪深300联接基金A type : 指数型 netWorth : 1.51 expectWorth : 1.5095
     * expectGrowth : -0.31 dayGrowth : -0.2774 lastWeekGrowth : 0.1828 lastMonthGrowth : 3.0998
     * lastThreeMonthsGrowth : -1.6158 lastSixMonthsGrowth : 1.5535 lastYearGrowth : 8.6409
     * netWorthDate : 2020-05-15 expectWorthDate : 2020-05-15 15:00:00
     */
    // 基金代码
    private String code;

    // 基金名称
    private String name;

    // 当前基金单位净值
    private Number netWorth;

    // 当前基金单位净值估算
    private Number expectWorth;

    // 当前基金单位净值估算日涨幅,单位为百分比
    private String expectGrowth;

    // 单位净值日涨幅,单位为百分比
    private String dayGrowth;

    //  单位净值周涨幅,单位为百分比
    private String lastWeekGrowth;

    //  单位净值月涨幅,单位为百分比
    private String lastMonthGrowth;

    // 单位净值三月涨幅,单位为百分比
    private String lastThreeMonthsGrowth;

    // 单位净值六月涨幅,单位为百分比
    private String lastSixMonthsGrowth;

    // 单位净值年涨幅,单位为百分比
    private String lastYearGrowth;

    // 净值更新日期,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
    private String netWorthDate;

    // 净值估算更新日期,,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
    private String expectWorthDate;
  }
}
