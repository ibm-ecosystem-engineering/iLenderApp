package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndLoan;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LndLoanRepository extends PagingAndSortingRepository<LndLoan, Integer> {

    LndLoan findOneByLndCustomerId(Integer lndCustomerId);
    LndLoan findOneByPurpose(String purpose);
    List findAllByLndCustomerId(Integer lndCustomerId, Sort sort);

}

