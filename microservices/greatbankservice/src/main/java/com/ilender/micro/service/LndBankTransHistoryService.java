package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.DateUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.LndBankTransHistory;
import com.ilender.micro.jpa.LndBankTransHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class LndBankTransHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Autowired
    LndBankTransHistoryRepository repository;

    public List<LndBankTransHistory> findAll() {
        List<LndBankTransHistory> list = (List<LndBankTransHistory>) repository.findAll();
        processAfterLoad(list);
        return list;
    }

    public LndBankTransHistory create(LndBankTransHistory infoObject) {
        LogUtil.logDebug("LndBankTransHistoryService : create : LndCustomerId :" + infoObject.getLndCustomerId());

        return repository.save(infoObject);
    }

    public LndBankTransHistory update(LndBankTransHistory infoObject) {
        LogUtil.logDebug("LndBankTransHistoryService : update :" + infoObject.getId());

        repository.save(infoObject);
        infoObject = find(infoObject.getId());
        processAfterLoad(infoObject);
        return infoObject;
    }

    public LndBankTransHistory find(Integer id) {
        LogUtil.logDebug("LndBankTransHistoryService : find :" + id);
        LndBankTransHistory info = null;
        try {
            info = repository.findById(id).get();
            processAfterLoad(info);
        } catch (Exception e) {

        }
        return info;
    }

    public LndBankTransHistory delete(int id) {
        LogUtil.logDebug("LndBankTransHistoryService : delete :" + id);

        LndBankTransHistory infoObject = find(id);
        if(infoObject != null){
            repository.delete(infoObject);
        }
        return infoObject;
    }

    public List<LndBankTransHistory> findAllByLndCustomerId(Integer lndCustomerId) {
        logService.logInfo(logger, "TransHistory Retrieve started");

        List<LndBankTransHistory> list = repository.findAllByLndCustomerId(lndCustomerId, Sort.by("transDate").descending());

        //Trim to 5
        if (list.size() > 5) {
            list = list.subList(0,5);
        }

        processAfterLoad(list);

        logService.logInfo(logger, "LoanHistory Retrieved for customer : " + lndCustomerId);

        return list;
    }

    private void processAfterLoad(List<LndBankTransHistory> list) {
        for (LndBankTransHistory info : list) {
            processAfterLoad(info);
        }
    }

    private void processAfterLoad(LndBankTransHistory info) {
        info.setTransDateString(DateUtil.convertDDMMMYYY(info.getTransDate()));
    }
}
