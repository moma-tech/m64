package top.moma.fund.entity.vo.params;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * FundDetailParams
 *
 * <p>//获取基金详情
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/12/21.
 */
@Data
@Builder
public class FundDetailParams implements java.io.Serializable {
  private static final long serialVersionUID = 970226879801773230L;

  /** 基金代码(必填) */
  private String code;
  /** 开始时间,标准时间格式yyyy-MM-dd */
  private LocalDate startDate;
  /** 截至时间,标准时间格式yyyy-MM-dd */
  private LocalDate endDate;

  /** 是否为货币基金，0：非货币，1：货币 */
  private Integer ifMonetary;
}
