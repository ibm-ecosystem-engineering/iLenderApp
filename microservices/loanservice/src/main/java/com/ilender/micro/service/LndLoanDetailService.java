package com.ilender.micro.service;

import com.ilender.micro.common.DateUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.LndLoanDetail;
import com.ilender.micro.jpa.LndLoanDetailRepository;
import com.ilender.micro.model.LoanDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LndLoanDetailService {

    @Autowired
    LndLoanDetailRepository repository;

    public List<LndLoanDetail> findAll() {
        List<LndLoanDetail> list = (List<LndLoanDetail>) repository.findAll();
        processAfterLoad(list);
        return list;
    }

    public LndLoanDetail create(LndLoanDetail lndLoan) {
        LogUtil.logDebug("LndLoanDetailService : create LndLoanId:" + lndLoan.getLndLoanId());
        return repository.save(lndLoan);
    }

    public LndLoanDetail update(LndLoanDetail lndLoan) {
        LogUtil.logDebug("LndLoanDetailService : update :" + lndLoan.getId());

        repository.save(lndLoan);
        lndLoan = find(lndLoan.getId());
        return lndLoan;
    }

    public LndLoanDetail find(Integer id) {
        LndLoanDetail info = repository.findById(id).get();
        processAfterLoad(info);
        return info;
    }

    public LndLoanDetail delete(int id) {
        LogUtil.logDebug("LndLoanDetailService : delete :" + id);

        LndLoanDetail info = find(id);
        if(info != null){
            repository.delete(info);
        }
        return info;
    }

    public List<LndLoanDetail> findAllByLndLoanId(Integer lndLoanId) {
        List<LndLoanDetail> list = (List<LndLoanDetail>) repository.findAllByLndLoanId(lndLoanId);
        processAfterLoad(list);
        return list;
    }

    public LndLoanDetail createFromInfo(LoanDetailInfo info) {
        LogUtil.logDebug("LndLoanOfferService : createFromInfo LoanDetailInfo :" + info);

        LndLoanDetail lndLoanDetail = new LndLoanDetail();

        lndLoanDetail.setLndLoanId(info.getLndLoanId());
        lndLoanDetail.setBankId(info.getBankId());
        lndLoanDetail.setBankName(info.getBankName());
        lndLoanDetail.setLoanReferenceNo(info.getLoanReferenceNo());
        lndLoanDetail.setLoanGrantedDate(info.getLoanGrantedDate());
        lndLoanDetail.setEmi(info.getEmi());
        lndLoanDetail.setLoanStartDate(info.getLoanStartDate());
        lndLoanDetail.setLoanStartDate(info.getLoanStartDate());
        lndLoanDetail.setPrincipalRemaining(info.getPrincipalRemaining());
        lndLoanDetail.setLastInstallmentAmount(info.getLastInstallmentAmount());
        lndLoanDetail.setLastInstallmentDate(info.getLastInstallmentDate());
        lndLoanDetail.setNoOfInstallmentsRemaining(info.getNoOfInstallmentsRemaining());
        lndLoanDetail.setTenure(info.getTenure());

        processAfterLoad(lndLoanDetail);

        return repository.save(lndLoanDetail);
    }

    public void processAfterLoad(List<LndLoanDetail> list) {
        for (LndLoanDetail info : list) {
            processAfterLoad(info);
        }
    }

    public void processAfterLoad(LndLoanDetail info) {
        info.setLastInstallmentDateString(DateUtil.convertDDMMMYYY(info.getLastInstallmentDate()));
        info.setLoanGrantedDateString(DateUtil.convertDDMMMYYY(info.getLoanGrantedDate()));
        info.setLoanStartDateString(DateUtil.convertDDMMMYYY(info.getLoanStartDate()));
        info.setLoanEndDateString(DateUtil.convertDDMMMYYY(info.getLoanEndDate()));
    }
}

