package com.ilender.micro.util;

import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.DateUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.LndBankTransHistory;
import com.ilender.micro.model.openbank.OpenBankingTransHistoryInfo;
import com.ilender.micro.model.openbank.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OpenBankingDataConverterUtil {

    public static List<LndBankTransHistory> convertTransHistory(String jsonString, int bankId) {
        LogUtil.logDebug("OpenBankingDataConverterUtil :convertTransHistory : jsonString : " + jsonString);
        LogUtil.logDebug("OpenBankingDataConverterUtil :convertTransHistory : bankId : " + bankId);

//        jsonString = jsonString.replace(":{\"Transaction\":", " :{\"T111ransaction\":[");
//        jsonString = jsonString.replaceAll("\"Transaction\":", "");
//        jsonString = jsonString.replace("}}}}}", "}}}]}}");
//        jsonString = jsonString.replace("T111ransaction", "Transaction");


        LogUtil.logDebug("OpenBankingDataConverterUtil :convertTransHistory : modified jsonString : " + jsonString);

        List<LndBankTransHistory> list = new ArrayList();

        OpenBankingTransHistoryInfo info = (OpenBankingTransHistoryInfo) ConversionUtil.jsonToObject(jsonString, OpenBankingTransHistoryInfo.class);
        LogUtil.logDebug("OpenBankingDataConverterUtil :convertTransHistory : info : " + info);

        double amount;
        LndBankTransHistory lndBankTransHistory = null;
        if (info.getData() != null && info.getData().getTransaction() != null) {
            for (Transaction transaction : info.getData().getTransaction()) {

                lndBankTransHistory = new LndBankTransHistory();
                list.add(lndBankTransHistory);

                lndBankTransHistory.setId(transaction.getTransactionId());
                lndBankTransHistory.setLndCustomerId(transaction.getAccountId());
                lndBankTransHistory.setTransDate(DateUtil.openBankDateStringToDate(transaction.getValueDateTime()));
                lndBankTransHistory.setTransDateString(DateUtil.convertDDMMMYYY(lndBankTransHistory.getTransDate()));

                lndBankTransHistory.setBankId(bankId);
                if (transaction.getProprietaryBankTransactionCode() != null) {
                    lndBankTransHistory.setBankName(transaction.getProprietaryBankTransactionCode().getIssuer());
                }
                if (transaction.getBalance() != null && transaction.getBalance().getAmount() != null) {
                    lndBankTransHistory.setBalance(transaction.getBalance().getAmount().getAmount());
                }

                if (transaction.getBalance() != null && transaction.getBalance().getAmount() != null) {

                    lndBankTransHistory.setBalance(transaction.getBalance().getAmount().getAmount());
                }

                lndBankTransHistory.setParticulars(transaction.getCreditDebitIndicator());
                amount = 0;
                if (transaction.getAmount() != null) {
                    amount = transaction.getAmount().getAmount();
                }
                if ("Withdraw".equalsIgnoreCase(transaction.getCreditDebitIndicator())) {
                    lndBankTransHistory.setWithdrawl(amount);
                } else {
                    lndBankTransHistory.setDeposit(amount);
                }
            }
        }
        return list;
    }
}
