package top.moma.fund.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Fund
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 2/25/21.
 */
@Entity
@Table(name = "tbl_fund")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fund {

  @Id // 这是一个主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "fund_code", length = 50)
  private String fundCode;

  @Column(name = "fund_name", length = 255)
  private String fundName;

  @Column(name = "fund_type", length = 255)
  private String fundType;

  @Column(name = "fund_manager", length = 255)
  private String fundManager;

  @Column(name = "fund_scale", length = 255)
  private String fundScale;
}
