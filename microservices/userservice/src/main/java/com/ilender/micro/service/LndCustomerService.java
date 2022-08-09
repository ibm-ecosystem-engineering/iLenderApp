package com.ilender.micro.service;

import com.ilender.micro.common.DateUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.*;
import com.ilender.micro.jpa.LndCustomerRepository;
import com.ilender.micro.entity.LndCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LndCustomerService {

    @Autowired
    LndUserService userService;

    @Autowired
    LndCustomerRepository repository;

    @Autowired
    ImageService imageService;

    @Autowired
    LndFieldAgentService fieldAgentService;

    public List<LndCustomer> findAll() {
        List<LndCustomer> list = (List<LndCustomer>) repository.findAll();
        processAfterLoad(list);
        return list;
    }

    public Object create(LndCustomer wcCustomer) {
        return null;
    }

    public LndCustomer update(LndCustomer wcCustomer) {
        return null;
    }

    public LndCustomer find(Integer id) {
        LndCustomer lndCustomer = null;
        try {
            lndCustomer = repository.findById(id).get();
            processAfterLoad(lndCustomer);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        LogUtil.logDebug("LndCustomerService : find : lndCustomer : " + lndCustomer);
        populateFieldAgentName(lndCustomer);
        return lndCustomer;
    }


    public LndCustomer delete(int id) {
        return null;
    }

    public LndCustomer findOneByLndUserId(Integer lndUserId) {
        LndCustomer info = repository.findOneByLndUserId(lndUserId);
        processAfterLoad(info);
        return info;
    }

    private void processAfterLoad(List<LndCustomer> list) {
        for (LndCustomer info : list) {
            processAfterLoad(info);
        }
    }

    private void processAfterLoad(LndCustomer info) {
        info.setEstablishmentDateString(DateUtil.convertDDMMMYYY(info.getEstablishmentDate()));
        info.setImage(imageService.createImageUrl("male", info.getId(), true));
        populateFieldAgentName(info);
    }

    private void populateFieldAgentName(LndCustomer lndCustomer) {
        LogUtil.logDebug("LndCustomerService : populateFieldAgentName : Started ; " + lndCustomer);
        LogUtil.logDebug("LndCustomerService : populateFieldAgentName : FieldAgentId ; " + lndCustomer.getLndFieldAgentId());
        String name = "";
        if (lndCustomer.getLndFieldAgentId() > 0) {
            LndFieldAgent lndFieldAgent = fieldAgentService.find(lndCustomer.getLndFieldAgentId());
            LogUtil.logDebug("LndCustomerService : populateFieldAgentName : lndFieldAgent ; " + lndFieldAgent);
            if (lndFieldAgent != null) {
                name = lndFieldAgent.getFirstName() + " " + lndFieldAgent.getLastName();
            }
            LogUtil.logDebug("LndCustomerService : populateFieldAgentName : name ; " + name);
            lndCustomer.setFieldAgentName(name);
        }
    }
}
