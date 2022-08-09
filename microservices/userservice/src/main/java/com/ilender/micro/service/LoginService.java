package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.common.LoginUtil;
import com.ilender.micro.controller.LndUserController;
import com.ilender.micro.entity.LndBusinessManager;
import com.ilender.micro.entity.LndCustomer;
import com.ilender.micro.entity.LndUser;
import com.ilender.micro.entity.LndFieldAgent;
import com.ilender.micro.model.LoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class LoginService {

    public static final Logger logger = LoggerFactory.getLogger(LndUserController.class);
    @Autowired
    LogService logService;

    @Autowired
    LndUserService usersService;

    @Autowired
    LndBusinessManagerService businessManagerService;

    @Autowired
    LndCustomerService customerService;

    @Autowired
    LndFieldAgentService fieldAgentService;

    public void processLogin (LoginInfo loginInfo) {
        LogUtil.logDebug("LoginService : doLogin started : " + loginInfo.getLoginId());
        logService.logInfo(logger,"LoginService : login process started : " + loginInfo.getLoginId());

        loginInfo.clear();

        LndUser lndUser = null;
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while processing login");
        } else {
            lndUser = usersService.fetchUserByLoginId(loginInfo.getLoginId());
        }

        if (lndUser == null) {
            LogUtil.logDebug("LoginService : login failed : User doesn't exists");
            logService.logError(logger,"LoginService : login failed : User doesn't exists : " +  loginInfo.getLoginId());

            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            if (LoginUtil.match(loginInfo.getPassword(), lndUser.getPassword())) {
                LogUtil.logDebug("LoginService : login success");
                logService.logInfo(logger,"LoginService : login success");

                populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_200, CommonConstants.LOGIN_RETURN_MSG_Sucess);

                //Process
                processLoginSuccess(loginInfo, lndUser);
            } else {
                LogUtil.logDebug("LoginService : login failed : Invalid password");
                logService.logError(logger,"LoginService : login failed : Invalid password");

                populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_401, CommonConstants.LOGIN_RETURN_MSG_Invalid_Password);
            }
        }

        logService.logInfo(logger,"LoginService : login process completed : " + loginInfo.getLoginId());

    }

    private void processLoginSuccess(LoginInfo loginInfo, LndUser wcUsers) {
        LoginUtil.populateRole(wcUsers.getRole(), loginInfo);

        loginInfo.setUserLoginEmailId(wcUsers.getEmailId());
        loginInfo.setUserLoginName(wcUsers.getUserName());
        loginInfo.setUserRole(wcUsers.getRole());

        if (loginInfo.isUserRoleCustomer()) {
            populateCustomer(loginInfo, wcUsers);
        } else if (loginInfo.isUserRoleBuisnessManager()) {
            populateBusinessManager(loginInfo, wcUsers);
        } else if (loginInfo.isUserRoleFieldAgent()) {
            populateFieldAgent(loginInfo, wcUsers);
        }
    }

    private void populateCustomer(LoginInfo loginInfo, LndUser lndUser) {
        LndCustomer entity = customerService.findOneByLndUserId(lndUser.getId());
        if (entity == null) {
            LogUtil.logDebug("LoginService : login failed : User/Customer doesn't exists");
            logService.logError(logger,"LoginService : login failed : User/Customer doesn't exists");

            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            loginInfo.setUserDisplayId(String.valueOf(entity.getId()));
            loginInfo.setUserDisplayName(entity.getOrgName());

            loginInfo.setValidLogin(true);
        }
    }

    private void populateFieldAgent(LoginInfo loginInfo, LndUser lndUser) {
        LndFieldAgent entity = fieldAgentService.findOneByLndUserId(lndUser.getId());
        if (entity == null) {
            LogUtil.logDebug("LoginService : login failed : User/FieldAgent doesn't exists");
            logService.logError(logger,"LoginService : login failed : User/FieldAgent doesn't exists");

            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            loginInfo.setUserDisplayId(String.valueOf(entity.getId()));
            loginInfo.setUserDisplayName(entity.getFirstName() + " " + entity.getLastName());

            loginInfo.setValidLogin(true);
        }
    }

    private void populateBusinessManager(LoginInfo loginInfo, LndUser lndUser) {
        LndBusinessManager entity = businessManagerService.findOneByLndUserId(lndUser.getId());
        if (entity == null) {
            LogUtil.logDebug("LoginService : login failed : User/BusinessManager doesn't exists");
            logService.logError(logger,"LoginService : login failed : User/BusinessManager doesn't exists");
            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            loginInfo.setUserDisplayId(String.valueOf(entity.getId()));
            loginInfo.setUserDisplayName(entity.getFirstName() + " " + entity.getLastName());

            loginInfo.setValidLogin(true);
        }
    }

    private void populateReturnCode(LoginInfo loginInfo, String returnCode, String returnMessage) {
        loginInfo.setReturnCode(returnCode);
        loginInfo.setReturnMessage(returnMessage);
    }


}
