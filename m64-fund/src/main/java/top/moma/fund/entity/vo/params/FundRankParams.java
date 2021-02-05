package top.moma.fund.entity.vo.params;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FundRandParams
 *
 * <p>//根据条件获取基金排行(包含基本基金信息),不合法的参数会被直接忽略。
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/12/21.
 */
@Data
@Builder
public class FundRankParams implements Serializable {
  private static final long serialVersionUID = 3442427111937008294L;

  /**
   * r(日涨幅) z(周涨幅),1y(最近一个月涨幅),jn(今年涨幅),1n(近一年涨幅)
   *
   * <p>Default value: 日涨幅
   *
   * <p>Allowed values: {"r", "z", "1y", "3y", "6y", "jn", "1n", "2n", "3n"}
   */
  private String sort;
  /**
   * 页码
   *
   * <p>Default value: 1
   */
  private Integer pageIndex;
  /**
   * 每页条目数
   *
   * <p>Default value: 10
   */
  private Integer pageSize;
  /**
   * 基金gp(股票型),hh(混合型),zq(债券型),zs(指数型)(可以接受多个参数)
   *
   * <p>Default value: 所有类型
   *
   * <p>Allowed values: {"gp", "hh", "zq", "zs", "qdii", "fof"}
   */
  private List<String> fundType;
  /**
   * 华夏("80000222"), 嘉实("80000223"), 易方达("80000229"),南方("80000220"), 中银("80048752"), 广发("80000248"),
   * 工银("80064225"), 博时("80000226"), 华安("80000228"),汇添富("80053708");(接受多个参数)
   *
   * <p>Default value: 所有基金公司
   *
   * <p>Allowed values: {"80000222", "80000223", "80000229", "80000220", "80048752", "80000248",
   * "80064225", "80000226", "80000228", "80053708"}
   */
  private List<String> fundCompany;

  /**
   * 基金成立时间限制1:小于一年》2:小于两年.依次类推(非必需)
   *
   * <p>Default value: 无限制
   */
  private Integer creatTimeLimit;

  /**
   * 基金规模单位亿,10表示10亿以下,100表示100亿以下,1001表示100亿以上(非必需)
   *
   * <p>Allowed values: [10, 100, 1001]
   */
  private Integer fundScale;

  /**
   * 排序方式0:降序:1升序
   *
   * <p>Default value: 0
   *
   * <p>Allowed values: {0，1}
   */
  private Integer asc;
}
