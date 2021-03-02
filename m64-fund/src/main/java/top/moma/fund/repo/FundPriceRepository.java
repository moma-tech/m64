package top.moma.fund.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import top.moma.fund.entity.model.FundPrice;

/**
 * FundPriceRepository
 *
 * @author ivan
 * @version 1.0 Created by ivan at 3/2/21.
 */
public interface FundPriceRepository extends JpaRepository<FundPrice, Integer> {}
