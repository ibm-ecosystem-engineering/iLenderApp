#!/bin/bash
echo "Process started ...  $(date)"

SERVER_URL=ilender-ocp-ns.gsidevint-aaaaaa.us-south.containers.appdomain.cloud
API_URL=http://ilender-frontweb-$SERVER_URL

# Customer - Sandy
CUSTOMER_ID=40003
CUSTOMER_USERID=sandy
CUSTOMER_PWD=sandy
BUSINESS_MANAGER_USERID=sandy
BUSINESS_MANAGER_PWD=sandy

for i in {1..500}
  do
        # Params
      SUFFIX_Timestamp=$(date +%s)
      LOAN_PURPOSE="Loan-$SUFFIX_Timestamp"

      echo "------------------------------------------------------------------------"
      echo "Iteration ... $i  : $LOAN_PURPOSE  :  $(date)"
      echo ""

      # Login as SMB Customer (sandy)
      echo "Login as SMB Customer (sandy)"
      curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
        \"loginId\": \"$CUSTOMER_USERID\",
        \"password\": \"$CUSTOMER_PWD\"
      }" "$API_URL/user/public/login"  &> /dev/null

      # Create New Loan request
      echo "Create New Loan request : $LOAN_PURPOSE"
      curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
        \"lndCustomerId\": $CUSTOMER_ID,
        \"loanAmount\": 500000,
        \"purpose\": \"$LOAN_PURPOSE\",
        \"startDate\": \"2021-04-05\",
        \"duration\": 5
      }" "$API_URL/api/core/addNewLoan/$CUSTOMER_ID"  &> /dev/null

      # Login as Business Manager (sam)
      echo "Login as Business Manager (sam)"
      curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
        \"loginId\": \"$BUSINESS_MANAGER_USERID\",
        \"password\": \"$BUSINESS_MANAGER_PWD\"
      }" "$API_URL/user/public/login"  &> /dev/null

      ## Load Loans by Purpose
      echo "Load Loan by $LOAN_PURPOSE"
      LOAN_ID=$(curl --silent -X GET --header "Accept: */*" "$API_URL/api/core/loanByPurpose/$LOAN_PURPOSE" | jq ."id" )
      echo "Loan Id  : $LOAN_ID "

      ## Get Credit Score by customerid and loadid
      echo "Get Credit Score"
      curl -X GET --header "Accept: */*" "$API_URL/api/core/creditScore/$CUSTOMER_ID/$LOAN_ID" &> /dev/null

      ## approve by load id
      echo "Approve loan request"
      curl -X GET --header "Accept: */*" "$API_URL/api/core/loanRequestApproved/$LOAN_ID" &> /dev/null

      # Login as SMB Customer (sandy)
      echo "Login as SMB Customer (sandy)"
      curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
        \"loginId\": \"$CUSTOMER_USERID\",
        \"password\": \"$CUSTOMER_PWD\"
      }" "$API_URL/user/public/login"  &> /dev/null

      ## Retrieve loan offer id
      echo "Get loan Offer Id"
      LOAN_OFFER_ID=$(curl --silent -X GET --header "Accept: */*" "$API_URL/api/core/loanByPurpose/$LOAN_PURPOSE" | jq ."loanOfferList"[0].id )

      ## accept load offer
      echo "Accept Loan Offer"
      curl -X GET --header "Accept: */*" "$API_URL/api/core/loanOfferAccepted/$LOAN_OFFER_ID" &> /dev/null

  done

echo "Process completed ...  $(date)"