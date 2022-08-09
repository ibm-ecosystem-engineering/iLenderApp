import base64

from locust import HttpLocust, TaskSet, task
from random import randint, choice
from datetime import datetime
from locust import HttpUser, task, between
import calendar;
import time;
import os

class QuickstartUser(HttpUser):
    wait_time = between(1, 2.5)
    @task
    def executeOneFlow(self):

        RUN_MODE = os.environ.get('RUN_MODE', 'training')

        # Customer - Sandy
        CUSTOMER_ID="40003"
        CUSTOMER_USERID="sandy"
        CUSTOMER_PWD="sandy"
        BUSINESS_MANAGER_USERID="sam"
        BUSINESS_MANAGER_PWD="sam"
        LOAN_ID_DEFAULT="50001"

        # Login as SMB Customer (sandy)
        self.client.post("/user/public/login",
                         json={"loginId": CUSTOMER_USERID, "password": CUSTOMER_PWD})

        ## Create New Loan request
        MY_URL= "/api/core/addNewLoan/" + CUSTOMER_ID
        json_response_dict = self.client.post(MY_URL, json={
                         "lndCustomerId": CUSTOMER_ID, 
                         "loanAmount": 500000,
                         "purpose": "Personal",
                         "startDate": "2022-01-01",
                         "duration": 5 }).json()
        LOAN_ID = json_response_dict['id']

        ## Login as Business Manager (sam)
        self.client.post("/user/public/login",
                         json={"loginId": BUSINESS_MANAGER_USERID, "password": BUSINESS_MANAGER_PWD })

        ## Load Loans by Purpose
        MY_URL= "/api/core/loanById/" + str(LOAN_ID)
        self.client.get(MY_URL)

        ## Get Credit Score by customerid and loadid
        MY_URL= "/api/core/creditScore/" + CUSTOMER_ID + "/" + str(LOAN_ID)
        self.client.get(MY_URL)

        if RUN_MODE == 'training':
            ## approve by load id
            MY_URL= "/api/core/loanRequestApproved/" + str(LOAN_ID)
            self.client.get(MY_URL)

            ## Login as SMB Customer (sandy)
            self.client.post("/user/public/login",
                        json={"loginId": CUSTOMER_USERID, "password": CUSTOMER_PWD})

            ## Retrieve loan offer id
            MY_URL= "/api/core/loanById/" + str(LOAN_ID)
            json_response_dict = self.client.get(MY_URL).json()
            LOAN_OFFER_ID = json_response_dict['loanOfferList'][0]['id']
            
            ## accept loan offer
            MY_URL= "/api/core/loanOfferAccepted/" + str(LOAN_OFFER_ID)
            self.client.get(MY_URL)
            

        

