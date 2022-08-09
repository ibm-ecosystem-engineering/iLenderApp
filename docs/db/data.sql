/*  
--  This sql is executed only when H2 db is enabled in application.properties. This will not be executed for DB2.
--  This sql file should be available in all the services that has db transactions
*/

/*
--    delete from Wcusers;
--    ALTER TABLE LndUser ALTER COLUMN id RESTART WITH 10001;
*/
    insert into LndUser (UserName, Password, EmailId, Role) values ('harry','harry','harry@ilender1.com', 'FA');
    insert into LndUser (UserName, Password, EmailId, Role) values ('charlie','charlie','charlie@ilender1.com', 'FA');
    insert into LndUser (UserName, Password, EmailId, Role) values ('oliver','oliver','oliver@ilender1.com', 'FA');
    insert into LndUser (UserName, Password, EmailId, Role) values ('tod','tod','tod@ilender1.com', 'BM');
    insert into LndUser (UserName, Password, EmailId, Role) values ('sam','sam','sam@ilender1.com', 'BM');
    
    insert into LndUser (UserName, Password, EmailId, Role) values ('sophia','sophia','sophia@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('william','william','nathan@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('sandy','sandy','sandy@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('david','david','david@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('richard','richard','richard@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('emma','emma','emma@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('tom','tom','tom@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('jerald','jerald','jerald@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('charlotte','charlotte','charlotte@ilender1.com', 'CU');
    insert into LndUser (UserName, Password, EmailId, Role) values ('mia','mia','mia@ilender1.com', 'CU');

/*
--    delete from LndFieldAgent;
--    ALTER TABLE LndFieldAgent ALTER COLUMN id RESTART WITH 20001;
*/

    insert into LndFieldAgent (LndUserId, FirstName, LastName, StartDate) values (10001, 'Harry', 'Jack', '2012-02-01');
    insert into LndFieldAgent (LndUserId, FirstName, LastName, StartDate) values (10002, 'Charlie', 'Jacob', '2013-02-01');
    insert into LndFieldAgent (LndUserId, FirstName, LastName, StartDate) values (10003, 'Oliver', 'Noah', '2014-02-01');

/*
--    delete from LndBusinessManager;
--    ALTER TABLE LndBusinessManager ALTER COLUMN id RESTART WITH 30001;
*/

    insert into LndBusinessManager (LndUserId, FirstName, LastName)
            values (10004, 'Tod', 'Peter');
    insert into LndBusinessManager (LndUserId, FirstName, LastName)
            values (10005, 'Sam', 'Kim');
/*
 --   delete from LndCustomer;
--    ALTER TABLE LndCustomer ALTER COLUMN id RESTART WITH 40001;
--    ALTER TABLE LndCustomer AUTO_INCREMENT = 40001;
*/

    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10006, 20001, 'Sophia ORG', 'Sophia Ltd', 'Blr', '2010-11-2', 'Sophia','Vega', 'Stoker','Joseph', 50000000, 10000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10007, 20002, 'William ORG', 'William Ltd', 'Blr', '2019-11-2', 'William','Joseph', 'Grace','William', 150000000, 40000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10008, 20003, 'Sandy ORG', 'Sandy Inc', 'Blr', '2008-11-2', 'Sandy','Thomas', 'Jason','Thomas', 250000000, 110000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10009, 20001, 'David ORG', 'David Ltd', 'Blr', '2007-11-2', 'David','John', 'Mila','David', 90000000, 40000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10010, 20002, 'Richard ORG', 'Richard Ltd', 'Blr', '2010-11-2', 'Richard','Alex', 'Natalie','Richard', 120000000, 30000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10011, 20003, 'Emma ORG', 'Emma Ltd', 'Blr', '2011-11-2', 'Emma','Francis', 'Eason','Zyaire', 350000000, 10000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10012, 20001, 'Tom ORG', 'Tom Ltd', 'Blr', '2000-11-2', 'Tom','Peter', 'Alex','Tom', 450000000, 120000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10013, 20002, 'Jerald ORG', 'Jerald Ltd', 'Blr', '2011-11-2', 'Jerald','Lewis', 'Mia','Jerald', 120000000, 130000000, 'aaa@ilender1.com');
    insert into LndCustomer (LndUserId, LndFieldAgentId, OrgId, OrgName, Address, EstablishmentDate, Founder1FirstName, Founder1LastName, Founder2FirstName, Founder2LastName, AnnualRevenue, AnnualProfit, EmailId)
           values (10013, 20002, 'Mia ORG', 'Mia Ltd', 'Blr', '2000-11-2', 'Mia','Alex', 'Vaneesa','Alex', 80000000, 20000000, 'aaa@ilender1.com');
/*
--    delete from LndLoan;
--    ALTER TABLE LndLoan ALTER COLUMN id RESTART WITH 50001;
*/
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40001, '2020-07-28', 3000000, 'For working capital during the challenging times of COVID-19', '01_LOAN_REQUESTED', 0, '2020-12-01', 6);
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40002, '2020-07-25', 5000000, 'To construct the phase 2 facilities', '02_CREDIT_SCORE_RECEIVED', 80, '2020-12-11', 6 );
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40003, '2020-07-20', 5000000, 'For establishing Shuttle facilities', '03_LOAN_REQUEST_REJECTED', 35, '2020-12-21', 6 );
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40004, '2020-07-17', 5000000, 'To improve secutiry features', '01_LOAN_REQUESTED', 0, '2020-12-21', 7);
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40005, '2020-07-10', 7000000, 'To automate the printing process', '05_LOAN_OFFERES_SENT_TO_SMB', 83, '2020-12-11', 6 );
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40006, '2020-07-06', 7500000, 'For moderizing the IT operations', '06_LOAN_ACCEPTED_BY_SMB', 86, '2020-12-22', 6);
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40007, '2018-07-05', 4500000, 'For improving the logistics', '06_LOAN_ACCEPTED_BY_SMB', 76, '2020-12-14', 5);
    insert into LndLoan (LndCustomerId, LoanRequestDate, LoanAmount, Purpose, StatusCode, CreditScore, StartDate, Duration)
           values (40007, '2020-07-15', 3500000, 'For setting up the recycling unit', '01_LOAN_REQUESTED', 0, '2020-12-15', 5);
/*
--    delete from LndLoanOffer;
--    ALTER TABLE LndLoanOffer ALTER COLUMN id RESTART WITH 60001;
*/

    insert into LndLoanOffer (LndLoanId, BankId, BankName, LoanAmount, Tenure, InterestRate, OfferAccepted)
           values (50005, 1, 'Big Bank', 7000000, 5, 15, 0);
    insert into LndLoanOffer (LndLoanId, BankId, BankName, LoanAmount, Tenure, InterestRate, OfferAccepted)
           values (50005, 2, 'Great Bank', 7000000, 6, 14, 0);
    insert into LndLoanOffer (LndLoanId, BankId, BankName, LoanAmount, Tenure, InterestRate, OfferAccepted)
           values (50007, 2, 'Great Bank', 4500000, 6, 12, 1);
    insert into LndLoanOffer (LndLoanId, BankId, BankName, LoanAmount, Tenure, InterestRate, OfferAccepted)
           values (50007, 3, 'Royal Bank', 4500000, 7, 17, 0);

/*
--    delete from LndLoanDetail;
--    ALTER TABLE LndLoanDetail ALTER COLUMN id RESTART WITH 70001;
*/

    insert into LndLoanDetail (LndLoanId, BankId, BankName, LoanReferenceNo, LoanGrantedDate, Emi, InterestRate, LoanStartDate, LoanEndDate, Tenure, NoOfInstallmentsRemaining, PrincipalRemaining, InterestRemaining, LastInstallmentAmount, LastInstallmentDate)
           values (50007, 2, 'Great Bank', 'Great-2010-07-05-001', '2018-07-15', 45000, 10.25, '2018-08-01', '2024-07-01', 6, 36, 3000000, 100000, 45000, '2020-07-01');

/*  
--    delete from LndLoanHistory;
--   ALTER TABLE LndLoanHistory ALTER COLUMN id RESTART WITH 80001;
*/

    insert into LndLoanHistory (LndCustomerId, LoanDate, Purpose, LoanAmount, PaidAmount, CloseDate)
           values (40001, '2010-01-01', 'To buy cutting machinery', 1000000, 1400000, '2015-01-01');
    insert into LndLoanHistory (LndCustomerId, LoanDate, Purpose, LoanAmount, PaidAmount, CloseDate)
           values (40002, '2011-02-01', 'Renovation', 500000, 600000, '2016-01-01');
    insert into LndLoanHistory (LndCustomerId, LoanDate, Purpose, LoanAmount, PaidAmount, CloseDate)
           values (40003, '2012-01-01', 'Painting', 2000000, 2400000, '2016-01-01');
    insert into LndLoanHistory (LndCustomerId, LoanDate, Purpose, LoanAmount, PaidAmount, CloseDate)
           values (40007, '2010-01-01', 'To buy cutting machinery', 1200000, 1700000, '2016-01-01');

/*  
    delete from LndBankTransHistory;
    ALTER TABLE LndBankTransHistory ALTER COLUMN id RESTART WITH 90001;
*/    
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-01', 'Withdraw', 100000, 0, 10000000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-02', 'Deposit',  0, 200000, 10200000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-03', 'Withdraw', 100000,  0, 10100000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-04', 'Withdraw', 200000,  0, 9900000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-05', 'Withdraw', 300000,  0, 9600000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-06', 'Withdraw', 400000,  0, 9200000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-07', 'Withdraw', 500000,  0, 8700000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 1, 'Big Bank', '2020-07-08', 'Withdraw', 600000,  0, 8100000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40003, 1, 'Big Bank', '2020-07-01', 'Withdraw', 1000,  0, 100000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40003, 1, 'Big Bank', '2020-07-02', 'Deposit',  0, 2000, 102000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40003, 1, 'Big Bank', '2020-07-03', 'Withdraw', 1000,  0, 101000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40003, 1, 'Big Bank', '2020-07-04', 'Withdraw', 2000,  0, 99000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40005, 1, 'Big Bank', '2020-07-01', 'Withdraw', 1000,  0, 100000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40005, 1, 'Big Bank', '2020-07-02', 'Deposit',  0, 2000, 102000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40005, 1, 'Big Bank', '2020-07-03', 'Withdraw', 1000,  0, 101000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40005, 1, 'Big Bank', '2020-07-04', 'Withdraw', 2000, 0,  99000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40007, 1, 'Big Bank', '2020-07-01', 'Withdraw', 1000,  0, 100000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40007, 1, 'Big Bank', '2020-07-02', 'Deposit',  0, 2000, 102000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40007, 1, 'Big Bank', '2020-07-03', 'Withdraw', 1000,  0, 101000);
    insert into LndBankTransHistory (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40007, 1, 'Big Bank', '2020-07-04', 'Withdraw', 2000,  0, 199000);
/*
--    delete from LndLoanHistoryGreat;
--   ALTER TABLE LndLoanHistoryGreat ALTER COLUMN id RESTART WITH 91001;
*/

    insert into LndLoanHistoryGreat (LndCustomerId, LoanDate, Purpose, LoanAmount, PaidAmount, CloseDate)
           values (40001, '2012-01-01', 'To build guest rooms', 2000000, 2400000, '2017-01-01');
    insert into LndLoanHistoryGreat (LndCustomerId, LoanDate, Purpose, LoanAmount, PaidAmount, CloseDate)
           values (40007, '2014-01-01', 'To buy cutting machinery', 14000000, 1900000, '2019-01-01');

/*
--    delete from LndBankTransHistoryyGreat;
--    ALTER TABLE LndBankTransHistoryyGreat ALTER COLUMN id RESTART WITH 92001;
*/

    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 2, 'Great Bank', '2020-07-01', 'Withdraw', 100000, 0,  5000000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40001, 2, 'Great Bank', '2020-07-02', 'Withdraw', 200000, 0,  4800000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40002, 2, 'Great Bank', '2020-07-01', 'Withdraw', 10000,  0, 500000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40002, 2, 'Great Bank', '2020-07-02', 'Withdraw', 20000,  0, 480000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40003, 2, 'Great Bank', '2020-07-01', 'Withdraw', 1000,  0, 50000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40003, 2, 'Great Bank', '2020-07-02', 'Withdraw', 2000,  0, 48000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40004, 2, 'Great Bank', '2020-07-01', 'Withdraw', 10000,  0, 500000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40004, 2, 'Great Bank', '2020-07-02', 'Withdraw', 20000,  0, 480000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40005, 2, 'Great Bank', '2020-07-01', 'Withdraw', 1000, 0,  50000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40005, 2, 'Great Bank', '2020-07-02', 'Withdraw', 2000,  0, 48000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40006, 2, 'Great Bank', '2020-07-01', 'Withdraw', 10000,  0, 500000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40006, 2, 'Great Bank', '2020-07-02', 'Withdraw', 20000,  0, 480000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40007, 2, 'Great Bank', '2020-07-01', 'Withdraw', 1000,  0, 150000);
    insert into LndBankTransHistoryGreat (LndCustomerId, BankId, BankName, TransDate, Particulars, Withdrawl, deposit, Balance)
           values (40007, 2, 'Great Bank', '2020-07-02', 'Withdraw', 2000,  0, 48000);