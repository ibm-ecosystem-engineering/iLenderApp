package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndLoanDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LndLoanDetailRepository extends CrudRepository<LndLoanDetail, Integer> {

    List<LndLoanDetail> findAllByLndLoanId(Integer lndLoanId);
}

