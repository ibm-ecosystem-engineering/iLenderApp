package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.controller.LndUserController;
import com.ilender.micro.jpa.LndUserRepository;
import com.ilender.micro.entity.LndUser;
import com.ilender.micro.model.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LndUserService {

    public static final Logger logger = LoggerFactory.getLogger(LndUserController.class);

    @Autowired
    LndUserRepository repository;

    public List<LndUser> findAll() {
        List<LndUser> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));
        return list;
    }

    public List<LndUser> findAllWithDelay(long timeout) {
        List<LndUser> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));

        try
        {
            Thread.sleep(timeout);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        return list;
    }

    public Object create(LndUser entity) {
        LogUtil.logDebug("LndUserService : create started : " + entity.getEmailId());

        Object result = null;

        if (findByEmail(entity.getEmailId()) != null) {
            logger.error("LndUserService : Email Already exist " + entity.getEmailId());
            LogUtil.logDebug("LndUserService : Email Already exist " + entity.getEmailId());
            result = new CustomError("user with username " + entity.getEmailId() + "already exist ");
        } else {
            if (entity.getRole() == null || entity.getRole().isEmpty()) {
                entity.setRole(CommonConstants.ROLE_CUSTOMER);
            }
            entity = repository.save(entity);
            LogUtil.logDebug("LndUserService : User created : " + entity.getId());
            result = entity;
        }


        LogUtil.logDebug("LndUserService : create completed");
        return result;
    }

    public LndUser update(LndUser wcUsers) {

        LogUtil.logDebug("LndUserService : update : " + wcUsers.getId());

        repository.save(wcUsers);
        wcUsers = find(wcUsers.getId());
        return wcUsers;
    }

    public LndUser update(int id, String emailId) {

        LogUtil.logDebug("LndUserService : update : " + id);

        LndUser wcUsers = find(id);
        wcUsers.setEmailId(emailId);
        return update(wcUsers);
    }

    public LndUser findByEmail(String email) {
        return repository.findOneByEmailId(email);
    }

    public LndUser findByUserName(String email) {
        return repository.findOneByUserName(email);
    }

    public LndUser find(Integer id) {
        LndUser lndUser = null;
        try {
            lndUser = repository.findById(id).get();
        } catch ( Exception e) {
        }
        return lndUser;
    }

    public LndUser delete(int id) {
        LndUser wcUsers = repository.findById(id).get();

        LogUtil.logDebug("LndUserService : delete : " + id);
        if(wcUsers != null){
            repository.delete(wcUsers);
        }
        return wcUsers;
    }


    public LndUser fetchUserByLoginId(String loginId) {
        LogUtil.logDebug("LndUserService : fetchUserByLoginId started : " + loginId);

        LndUser entity = findByEmail(loginId);
        if (entity == null) {
            LogUtil.logDebug("LndUserService : fetchUserByLoginId : login id doesn't match with email ids ");
            entity = findByUserName(loginId);
            if (entity == null) {
                LogUtil.logDebug("LndUserService : fetchUserByLoginId : login id doesn't match with user name as well");
            } else {
                LogUtil.logDebug("LndUserService : fetchUserByLoginId : login id match with user name ");
            }
        } else {
            LogUtil.logDebug("LndUserService : fetchUserByLoginId : login id match with email id ");
        }
        LogUtil.logDebug("LndUserService : create completed");
        return entity;
    }

}
