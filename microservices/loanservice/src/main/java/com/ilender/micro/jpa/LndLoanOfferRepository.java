package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndLoanOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LndLoanOfferRepository extends CrudRepository<LndLoanOffer, Integer> {

    List<LndLoanOffer> findAllByLndLoanId(Integer lndLoanId);

}

