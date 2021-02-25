package top.moma.fund.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import top.moma.fund.entity.model.Fund;

/**
 * FundRepository
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 2/25/21.
 */
public interface FundRepository extends JpaRepository<Fund, Integer> {}
