package top.moma.fund.entity.model;

import javax.persistence.*;

/**
 * FundInfo
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 2/26/21.
 */
@Entity
@Table(name = "tbl_fund_info")
public class FundInfo {
  @Id // 这是一个主键
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
  private Integer id;

  // 基金代码
  @Column private String code;

  // 基金名称
  @Column private String name;

  // 当前基金单位净值
  @Column private Number netWorth;

  // 当前基金单位净值估算
  @Column private Number expectWorth;

  // 当前基金单位净值估算日涨幅,单位为百分比
  @Column private String expectGrowth;

  // 单位净值日涨幅,单位为百分比
  @Column private String dayGrowth;

  //  单位净值周涨幅,单位为百分比
  @Column private String lastWeekGrowth;

  //  单位净值月涨幅,单位为百分比
  @Column private String lastMonthGrowth;

  // 单位净值三月涨幅,单位为百分比
  @Column private String lastThreeMonthsGrowth;

  // 单位净值六月涨幅,单位为百分比
  @Column private String lastSixMonthsGrowth;

  // 单位净值年涨幅,单位为百分比
  @Column private String lastYearGrowth;

  // 净值更新日期,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
  @Column private String netWorthDate;

  // 净值估算更新日期,,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
  @Column private String expectWorthDate;
}
