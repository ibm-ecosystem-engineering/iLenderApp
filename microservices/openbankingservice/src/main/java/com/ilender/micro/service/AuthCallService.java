package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.ConversionUtil;
import com.ilender.micro.common.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


import org.springframework.web.client.RestTemplate;


@Configuration
@Service
public class AuthCallService {

    @Value("${oauthBigbankScopes}")
    private String bigBankScope;

    @Value("${oautBigbankClientId}")
    private String bigBankClientId;

    @Value("${oauthBigbankClientSecret}")
    private String bigBankClientSecret;

    @Value("${oauthBigbankTokenUri}")
    private String bigBankTokenUrl;

    @Value("${oauthBigbankServiceUriTransHistory}")
    private String bigBankServiceUrlTransHistory;

    @Value("${oauthBigbankServiceUriLoanHistory}")
    private String bigBankServiceUrlLoanHistory;

    @Value("${oauthBigbankServiceUriLoanOffer}")
    private String bigBankServiceUrlLoanOffer;

    @Value("${oauthBigbankServiceUriLoanDetail}")
    private String bigBankServiceUrlLoanDetail;

    @Value("${oauthGreatbankscopes}")
    private String greatBankScope;

    @Value("${oauthGreatbankClientId}")
    private String greatBankClientId;

    @Value("${oauthGreatbankClientSecret}")
    private String greatBankClientSecret;

    @Value("${oauthGreatbankTokenUri}")
    private String greatBankTokenUrl;

    @Value("${oauthGreatbankServiceUriTransHistory}")
    private String greatBankServiceUrlTransHistory;

    @Value("${oauthGreatbankServiceUriLoanHistory}")
    private String greatBankServiceUrlLoanHistory;

    @Value("${oauthGreatbankServiceUriLoanOffer}")
    private String greatBankServiceUrlLoanOffer;

    @Value("${oauthGreatbankServiceUriLoanDetail}")
    private String greatBankServiceUrlLoanDetail;



    @Autowired
    RestTemplate restTemplate;

    public String callPost(int bankId, int apiId, Object inputInfo) {
        String serviceUrl = getServiceUrl(bankId, apiId);
        LogUtil.logDebug("AuthCallService : callPost 1: serviceUrl: " + serviceUrl);
        return callAPI(HttpMethod.POST, bankId, serviceUrl, inputInfo);
    }

    public String callGet(int bankId, int apiId, int accountId) {
        String serviceUrl = getServiceUrl(bankId, apiId);
        serviceUrl = serviceUrl.replace("{accountId}", String.valueOf(accountId));
        LogUtil.logDebug("AuthCallService : callGet 1: serviceUrl: " + serviceUrl);
        return callAPI(HttpMethod.GET, bankId, serviceUrl, null);
    }

    public String callGetForOpenBanking(int bankId, int apiId, int accountId) {
        String serviceUrl = getServiceUrl(bankId, apiId);
        serviceUrl = serviceUrl.replace("{accountId}", String.valueOf(accountId));
        LogUtil.logDebug("AuthCallService : callGetForOpenBanking 1: serviceUrl: " + serviceUrl);
        return callAPIForOpenBanking(HttpMethod.GET, bankId, serviceUrl, null);
    }

    private String callAPI(HttpMethod httpmethod, int bankId, String apiUrl, Object inputInfo) {
        LogUtil.logDebug("AuthCallService : callAPI : started ");

        HttpHeaders headers = getHttpHeadersWithAccessToken(bankId);

        HttpEntity<String> entity = null;
        if (inputInfo == null) {
            entity = new HttpEntity(headers);
        } else {
            entity = new HttpEntity(inputInfo, headers);
        }
        LogUtil.logDebug("AuthCallService : callAPI : bankId " + bankId);
        LogUtil.logDebug("AuthCallService : callAPI : apiUrl " + apiUrl);
        LogUtil.logDebug("AuthCallService : callAPI : entity " + entity);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, httpmethod, entity, String.class);
        LogUtil.logDebug("AuthCallService : callAPI : response " + response);

        String result = response.getBody();
        LogUtil.logDebug("AuthCallService : callAPI : completed :" + result);
        return result;
    }


    private String callAPIForOpenBanking(HttpMethod httpmethod, int bankId, String apiUrl, Object inputInfo) {
        LogUtil.logDebug("AuthCallService : callAPIForOpenBanking : started ");

        HttpHeaders headers = getHttpHeadersWithAccessTokenForOpenBanking(bankId);

        HttpEntity<String> entity = null;
        if (inputInfo == null) {
            entity = new HttpEntity(headers);
        } else {
            entity = new HttpEntity(inputInfo, headers);
        }
        LogUtil.logDebug("AuthCallService : callAPIForOpenBanking : bankId " + bankId);
        LogUtil.logDebug("AuthCallService : callAPIForOpenBanking : apiUrl " + apiUrl);
        LogUtil.logDebug("AuthCallService : callAPIForOpenBanking : entity " + entity);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, httpmethod, entity, String.class);
        LogUtil.logDebug("AuthCallService : callAPIForOpenBanking : response " + response);

        String result = response.getBody();
        LogUtil.logDebug("AuthCallService : callAPIForOpenBanking : completed :" + result);
        return result;
    }

    private HttpHeaders getHttpHeaders(int bankId, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add ("Authorization", "Bearer " + accessToken);
        headers.add ("X-IBM-Client-Id", getClientId(bankId));
        headers.add ("X-IBM-Client-Secret", getClientSecret(bankId));
        return headers;
    }

    private HttpHeaders getHttpHeadersForOpenBanking(int bankId, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add ("Authorization", "Bearer " + accessToken);
        headers.add ("clientId", getClientId(bankId));
        headers.add ("clientSecret", getClientSecret(bankId));
        return headers;
    }

    private HttpHeaders getHttpHeadersWithAccessTokenForOpenBanking(int bankId) {
        HttpEntity entity = createTokenRequestHttpEntity(bankId);
        String accessToken = getAccessToken(bankId, entity);
        HttpHeaders headers = getHttpHeadersForOpenBanking(bankId, accessToken);
        return  headers;
    }

    private HttpHeaders getHttpHeadersWithAccessToken(int bankId) {
        HttpEntity entity = createTokenRequestHttpEntity(bankId);
        String accessToken = getAccessToken(bankId, entity);
        HttpHeaders headers = getHttpHeaders(bankId, accessToken);
        return  headers;
    }

    private String getAccessToken(int bankId, HttpEntity entity) {
        LogUtil.logDebug("AuthCallService : getAccessToken : started ");

        String tokenUrl = getTokenUrl(bankId);
        LogUtil.logDebug("AuthCallService : getAccessToken : tokenUrl :  " + tokenUrl);

        ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, String.class);

        String responseBodyAsJson = response.getBody();
        LogUtil.logDebug("responseBodyAsJson: " + responseBodyAsJson);

        String accessToken = ConversionUtil.readValue(responseBodyAsJson, "access_token");
        LogUtil.logDebug("AuthCallService : getAccessToken : completed :  " + accessToken);
        return accessToken;
    }

    private HttpEntity createTokenRequestHttpEntity(int bankId) {
        LogUtil.logDebug("AuthCallService : createTokenRequestHttpEntity : started : bankId :" + bankId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        StringBuffer bodyValue = new StringBuffer();
        bodyValue.append("grant_type=client_credentials");
        bodyValue.append("&audience=account-and-transaction-api-specification");
        bodyValue.append("&scope=" + getScope(bankId));
        bodyValue.append("&client_id=" + getClientId(bankId));
        bodyValue.append("&client_secret=" + getClientSecret(bankId));

        String bodyText = bodyValue.toString();
        LogUtil.logDebug("AuthCallService: createTokenRequestHttpEntity: Body Text: " + bodyText);

        HttpEntity formEntity = new HttpEntity<>(bodyText, headers);
        LogUtil.logDebug("AuthCallService : createTokenRequestHttpEntity : formEntity :" + formEntity);

        return formEntity;
    }

    private String getScope(int bankId) {
        if (bankId == CommonConstants.BANK_BIG) {
            return bigBankScope;
        } else {
            return greatBankScope;
        }
    }

    private String getClientId(int bankId) {
        if (bankId == CommonConstants.BANK_BIG) {
            return bigBankClientId;
        } else {
            return greatBankClientId;
        }
    }

    private String getClientSecret(int bankId) {
        if (bankId == CommonConstants.BANK_BIG) {
            return bigBankClientSecret;
        } else {
            return greatBankClientSecret;
        }
    }

    private String getTokenUrl(int bankId) {
        if (bankId == CommonConstants.BANK_BIG) {
            return bigBankTokenUrl;
        } else {
            return greatBankTokenUrl;
        }
    }

    private String getServiceUrl(int bankId, int apiId) {
        LogUtil.logDebug("AuthCallService: getServiceUrl: bankId : " + bankId);
        LogUtil.logDebug("AuthCallService: getServiceUrl: apiId : " + apiId);

        String result = null;
        if (bankId == CommonConstants.BANK_BIG) {
            if (apiId == CommonConstants.BANK_TRANS_HISTORY) {
                result = bigBankServiceUrlTransHistory;
            } else if (apiId == CommonConstants.BANK_LOAN_HISTORY) {
                result = bigBankServiceUrlLoanHistory;
            } else if (apiId == CommonConstants.BANK_LOAN_OFFER) {
                result = bigBankServiceUrlLoanOffer;
            } else if (apiId == CommonConstants.BANK_LOAN_DETAIL) {
                result = bigBankServiceUrlLoanDetail;
            }
        } else {
            if (apiId == CommonConstants.BANK_TRANS_HISTORY) {
                result = greatBankServiceUrlTransHistory;
            } else if (apiId == CommonConstants.BANK_LOAN_HISTORY) {
                result = greatBankServiceUrlLoanHistory;
            } else if (apiId == CommonConstants.BANK_LOAN_OFFER) {
                result = greatBankServiceUrlLoanOffer;
            } else if (apiId == CommonConstants.BANK_LOAN_DETAIL) {
                result = greatBankServiceUrlLoanDetail;
            }
        }

        LogUtil.logDebug("AuthCallService: getServiceUrl: result 1: " + result);
     LogUtil.logDebug("AuthCallService: getServiceUrl: result 2: " + result);
        return result;
    }




}
