package top.moma.fund.entity.model;

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
public class Fund {
  @Id // 这是一个主键
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
  private Integer id;

  @Column(name = "last_name", length = 50) // 这是和数据表对应的一个列
  private String lastName;
}
