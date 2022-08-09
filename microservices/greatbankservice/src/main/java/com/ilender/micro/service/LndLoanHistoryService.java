package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.DateUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.LndLoanHistory;
import com.ilender.micro.jpa.LndLoanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class LndLoanHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Autowired
    LndLoanHistoryRepository repository;

    public List<LndLoanHistory> findAll() {
        List<LndLoanHistory> list = (List<LndLoanHistory>) repository.findAll();
        processAfterLoad(list);
        return list;
    }

    public LndLoanHistory create(LndLoanHistory infoObject) {
        LogUtil.logDebug("LndLoanHistoryService : create : LndCustomerId :" + infoObject.getId());

        return repository.save(infoObject);
    }

    public LndLoanHistory update(LndLoanHistory infoObject) {
        LogUtil.logDebug("LndLoanHistoryService : update :" + infoObject.getId());

        repository.save(infoObject);
        infoObject = find(infoObject.getId());
        return infoObject;
    }

    public LndLoanHistory find(Integer id) {
        LogUtil.logDebug("LndLoanHistoryService : find :" + id);
        LndLoanHistory info = null;
        try {
            info = repository.findById(id).get();
            processAfterLoad(info);
        } catch (Exception e) {

        }
        return info;
    }

    public LndLoanHistory delete(int id) {
        LogUtil.logDebug("LndLoanHistoryService : delete :" + id);

        LndLoanHistory infoObject = find(id);
        if(infoObject != null){
            repository.delete(infoObject);
        }
        return infoObject;
    }

    public List<LndLoanHistory> findAllByLndCustomerId(Integer lndCustomerId) {

        logService.logInfo(logger, "LoanHistory Retrieve started");


        List<LndLoanHistory> list = repository.findAllByLndCustomerId(lndCustomerId, Sort.by("closeDate").descending());
        processAfterLoad(list);

        logService.logInfo(logger, "LoanHistory Retrieved for customer : " + lndCustomerId);

        return list;
    }

    private void processAfterLoad(List<LndLoanHistory> list) {
        for (LndLoanHistory info : list) {
            processAfterLoad(info);
        }
    }

    private void processAfterLoad(LndLoanHistory info) {
        info.setBankName(CommonConstants.BANK_NAME);
        info.setLoanDateString(DateUtil.convertDDMMMYYY(info.getLoanDate()));
        info.setCloseDateString(DateUtil.convertDDMMMYYY(info.getCloseDate()));
    }

}
