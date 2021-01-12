package top.moma.m64.apis.entity.vo.params;

import lombok.Builder;
import lombok.Data;

/**
 * FundInfoParams
 *
 * <p>获取基金基础信息 Param
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@Data
@Builder
public class FundInfoParams implements java.io.Serializable {

  private static final long serialVersionUID = -4491982669097036941L;
  /** 基金代码(必填)逗号隔开支持多个查询 */
  private String code;
}
