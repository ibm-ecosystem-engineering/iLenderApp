package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndLoanHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LndLoanHistoryRepository extends PagingAndSortingRepository<LndLoanHistory, Integer> {

    List<LndLoanHistory> findAllByLndCustomerId(Integer lndCustomerId, Sort sort);

}

