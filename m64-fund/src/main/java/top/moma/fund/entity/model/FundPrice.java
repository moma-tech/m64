package top.moma.fund.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * FundPrice
 *
 * @author ivan
 * @version 1.0 Created by ivan at 3/2/21.
 */
@Entity
@Table(name = "tbl_fund_price")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class FundPrice {

  @Id // 这是一个主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "fund_code", length = 50)
  private String fundCode;

  // 当前基金单位净值
  @Column(name = "net_worth")
  private String netWorth;

  // 净值更新日期,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
  @Column(name = "net_worth_date")
  private LocalDate netWorthDate;
}
