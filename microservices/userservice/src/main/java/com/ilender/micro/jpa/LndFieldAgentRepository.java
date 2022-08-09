package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndFieldAgent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LndFieldAgentRepository extends CrudRepository<LndFieldAgent, Integer> {

    LndFieldAgent findOneByLndUserId(Integer lndUserId);

}

