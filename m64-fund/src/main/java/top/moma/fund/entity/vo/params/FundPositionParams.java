package top.moma.fund.entity.vo.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FundPositionParams
 *
 * <p>//获取基金持仓详情
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/12/21.
 */
@Data
@Builder
@AllArgsConstructor
public class FundPositionParams implements java.io.Serializable {
  private static final long serialVersionUID = -3430029330463850460L;
  /** 基金代码(必填) */
  private String code;
}
