package top.moma.support.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import top.moma.support.entity.model.OfficialHoliday;

/**
 * OfficialHolidayRepository Official Holiday Repo
 *
 * @author ivan
 * @version 1.0 Created by ivan at 3/18/21.
 */
public interface OfficialHolidayRepository extends JpaRepository<OfficialHoliday, Integer> {}
