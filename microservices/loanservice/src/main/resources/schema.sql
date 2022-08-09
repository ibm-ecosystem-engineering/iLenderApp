--    This sql is executed only when H2 db is enabled in application.properties. This will not be executed for DB2.
--  This sql file should be available in all the services that has db transactions

	drop table IF EXISTS LndUser;
	create table LndUser (
        id INTEGER IDENTITY(10001,1),
        UserName varchar(40),
        Password varchar(40),
        EmailId varchar(40),
        role varchar(40)
    );
    -- ALTER TABLE LndUser AUTO_INCREMENT = 10001;

    drop table IF EXISTS LndFieldAgent;
    create table LndFieldAgent (
        id INTEGER IDENTITY(20001,1),
        LndUserId INT,
        FirstName varchar(40),
        LastName varchar(40),
        StartDate date
    );
    -- ALTER TABLE LndFieldAgent AUTO_INCREMENT = 20001;

    drop table IF EXISTS LndBusinessManager;
    create table LndBusinessManager (
        id INTEGER IDENTITY(30001,1),
        LndUserId INT,
        FirstName varchar(40),
        LastName varchar(40)
    );
    -- ALTER TABLE LndBusinessManager AUTO_INCREMENT = 30001;

    drop table IF EXISTS LndCustomer;
    create table LndCustomer (
        id INTEGER IDENTITY(40001,1),
        LndUserId INT,
        LndFieldAgentId INT,
        OrgId varchar(40),
        OrgName varchar(40),
        Address varchar(80),
        EstablishmentDate date,
        Founder1FirstName varchar(40),
        Founder1LastName varchar(40),
        Founder2FirstName varchar(40),
        Founder2LastName varchar(40),
        AnnualRevenue double,
        AnnualProfit double,
        EmailId varchar(80)
    );
    -- ALTER TABLE LndCustomer AUTO_INCREMENT = 40001;

    drop table IF EXISTS LndLoan;
    create table LndLoan (
        id INTEGER IDENTITY(50001,1),
        LndCustomerId INT,
        LoanRequestDate date,
        LoanAmount double,
        Purpose varchar(200),
        StatusCode varchar(40),
        CreditScore double,
        StartDate date,
        Duration int
    );
    -- ALTER TABLE LndLoan AUTO_INCREMENT = 50001;

    drop table IF EXISTS LndLoanOffer;
    create table LndLoanOffer (
        id INTEGER IDENTITY(60001,1),
        LndLoanId INT,
        BankId INT, 
        BankName varchar(80),
        LoanAmount double,
        Tenure INT,
        InterestRate INT,
        OfferAccepted INT
    );
    -- ALTER TABLE LndLoanOffer AUTO_INCREMENT = 60001;

    drop table IF EXISTS LndLoanDetail;
    create table LndLoanDetail (
        id INTEGER IDENTITY(70001,1),
        LndLoanId INT,
        BankId INT,
        BankName varchar(80),
        LoanReferenceNo varchar(80),
        LoanGrantedDate date,
        Emi double,
        LoanStartDate date,
        LoanEndDate date,
        Tenure INT,
        InterestRate INT,
        NoOfInstallmentsRemaining INT,
        PrincipalRemaining INT,
        InterestRemaining INT,
        LastInstallmentAmount double,
        LastInstallmentDate date
    );
    -- ALTER TABLE LndLoanDetail AUTO_INCREMENT = 70001;

    drop table IF EXISTS LndLoanHistory;
    create table LndLoanHistory (
        id INTEGER IDENTITY(80001,1),
        LndCustomerId INT,
        LoanDate date,
        Purpose varchar(200),
        LoanAmount double,
        PaidAmount double,
        CloseDate date
    );
    -- ALTER TABLE LndLoanHistory AUTO_INCREMENT = 80001;

    drop table IF EXISTS LndBankTransHistory;
    create table LndBankTransHistory (
        id INTEGER IDENTITY(90001,1),
        LndCustomerId INT,
        BankId INT,
        BankName varchar(80),
        TransDate date,
        Particulars varchar(80),
        Withdrawl double,
        deposit double,
        Balance double
    );
    -- ALTER TABLE LndBankTransHistory AUTO_INCREMENT = 90001;

    drop table IF EXISTS LndLoanHistoryGreat;
    create table LndLoanHistoryGreat (
        id INTEGER IDENTITY(91001,1),
        LndCustomerId INT,
        LoanDate date,
        Purpose varchar(200),
        LoanAmount double,
        PaidAmount double,
        CloseDate date
    );
    -- ALTER TABLE LndLoanHistoryGreat AUTO_INCREMENT = 91001;

    drop table IF EXISTS LndBankTransHistoryGreat;
    create table LndBankTransHistoryGreat (
        id INTEGER IDENTITY(92001,1),
        LndCustomerId INT,
        BankId INT,
        BankName varchar(80),
        TransDate date,
        Particulars varchar(80),
        Withdrawl double,
        deposit double,
        Balance double
    );
    -- ALTER TABLE LndBankTransHistory AUTO_INCREMENT = 92001;