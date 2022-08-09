package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndBusinessManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LndBusinessManagerRepository extends CrudRepository<com.ilender.micro.entity.LndBusinessManager, Integer> {

    LndBusinessManager findOneByLndUserId(Integer lndUserId);

}

