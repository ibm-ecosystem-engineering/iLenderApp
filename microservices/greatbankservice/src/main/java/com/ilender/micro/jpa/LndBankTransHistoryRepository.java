package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndBankTransHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LndBankTransHistoryRepository extends PagingAndSortingRepository<LndBankTransHistory, Integer> {

    List<LndBankTransHistory> findAllByLndCustomerId(Integer lndCustomerId, Sort sort);

}

