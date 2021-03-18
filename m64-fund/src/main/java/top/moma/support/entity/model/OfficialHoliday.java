package top.moma.support.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * OfficialHoliday Official Holidays
 *
 * @author ivan
 * @version 1.0 Created by ivan at 3/18/21.
 */
@Entity
@Table(name = "tbl_official_holiday")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfficialHoliday {
  @Id // 这是一个主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "official_date", length = 50)
  private LocalDate officialDate;

  @Column(name = "day_type", length = 2)
  private int dayType;
}
