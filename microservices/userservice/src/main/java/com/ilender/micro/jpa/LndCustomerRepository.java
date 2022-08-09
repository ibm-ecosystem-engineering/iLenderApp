package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LndCustomerRepository extends CrudRepository<LndCustomer, Integer> {

    LndCustomer findOneByLndUserId(Integer lndUserId);

}

