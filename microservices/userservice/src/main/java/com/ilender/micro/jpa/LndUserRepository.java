package com.ilender.micro.jpa;

import com.ilender.micro.entity.LndUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LndUserRepository extends CrudRepository<LndUser, Integer> {

    public LndUser findOneByEmailId(String emailId);

    public LndUser findOneByUserName(String userName);


}

