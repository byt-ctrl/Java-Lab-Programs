# ATM System - Code Analysis

## Overview
The ATM System is a comprehensive Java application that simulates a complete Automated Teller Machine experience. It provides banking operations including account authentication, balance management, transactions, fund transfers, and statement generation through a modular, service-oriented architecture.

## System Architecture

### Core Design Pattern
- **Service-Oriented Architecture**: Each functionality is encapsulated in dedicated service classes
- **Separation of Concerns**: Clear division between data models, business logic, and user interface
- **Static Service Methods**: All service classes use static methods for stateless operations
- **Centralized Data Management**: Single ATM system manages all accounts and coordinates operations

## Code Block Analysis

### 1. Data Models

#### TransactionRecord Class
```java
class TransactionRecord {
    String type;
    double amount;
    String date;
    String accountNumber;
    double balanceAfter;
}
```
**Purpose**: Immutable record of financial transactions
- **Automatic Timestamping**: Uses `LocalDateTime.now()` for precise transaction timing
- **Flexible Display**: `toString()` method handles both internal and external transactions
- **Currency Formatting**: Consistent "Rs" formatting with 2 decimal places
- **Account Context**: Tracks associated account numbers for transfers

#### Account Class
```java
class Account {
    String accountNumber;
    int pin;
    double balance;
    TransactionRecord[] transactionHistory;
    int transactionCount;
}
```
**Purpose**: Core banking account representation
- **Transaction History**: Fixed-size array (50 transactions) with count tracking
- **Automatic Logging**: All operations automatically create transaction records
- **Balance Management**: Direct balance manipulation with transaction recording

### 2. Core Account Operations

#### Account Constructor
```java
public Account(String accNo, int p, double bal) {
    // ... initialization
    addTransaction("Account Created", balance, "", balance);
}
```
**Logic**: Immediately logs account creation as first transaction

#### Deposit Method
```java
public void deposit(double amount) {
    balance += amount;
    addTransaction("Deposit", amount, "", balance);
}
```
**Logic**: Simple balance increment with transaction logging

#### Withdrawal Method
```java
public boolean withdraw(double amount) {
    if (amount > balance) return false;
    balance -= amount;
    addTransaction("Withdrawal", amount, "", balance);
    return true;
}
```
**Logic**: Balance validation before withdrawal with boolean success indicator

#### Transfer Method
```java
public boolean transfer(Account toAccount, double amount) {
    if (amount > balance) return false;
    this.balance -= amount;
    toAccount.balance += amount;
    this.addTransaction("Transfer Out", amount, toAccount.accountNumber, this.balance);
    toAccount.addTransaction("Transfer In", amount, this.accountNumber, toAccount.balance);
    return true;
}
```
**Logic**: Atomic transfer operation creating paired transaction records

### 3. Service Layer Architecture

#### AuthenticationService
```java
class AuthenticationService {
    public static boolean authenticate(Account account, Scanner scanner) {
        for (int attempts = 0; attempts < 3; attempts++) {
            // PIN validation logic with attempt tracking
        }
    }
}
```
**Security Features**:
- **Three-Strike Rule**: Maximum 3 PIN attempts before lockout
- **Input Validation**: Handles `NumberFormatException` for invalid PIN formats
- **Attempt Feedback**: Shows remaining attempts to user
- **Account Lockout**: Prevents further access after failed attempts

#### BalanceService
```java
class BalanceService {
    public static void displayBalance(Account account) {
        System.out.println("Account Number : " + account.accountNumber);
        System.out.printf("Current Balance : Rs %.2f\n", account.balance);
    }
}
```
**Purpose**: Simple balance display with consistent formatting

#### PinService
```java
class PinService {
    public static void generatePin(Account account) {
        int newPin = 1000 + (int)(Math.random() * 9000);
        // PIN assignment and logging
    }
    
    public static void changePin(Account account, Scanner scanner) {
        // Current PIN validation + new PIN setting
    }
}
```
**PIN Management Features**:
- **Random Generation**: 4-digit PIN generation (1000-9999 range)
- **Validation**: Ensures 4-digit format for new PINs
- **Security**: Requires current PIN verification for changes
- **Transaction Logging**: Both operations create audit trail

#### TransactionService
```java
class TransactionService {
    public static void withdrawMoney(Account account, Scanner scanner) {
        // Amount validation + withdrawal execution
    }
    
    public static void depositMoney(Account account, Scanner scanner) {
        // Amount validation + deposit execution
    }
}
```
**Validation Logic**:
- **Positive Amount Check**: Ensures amounts are greater than zero
- **Balance Validation**: Prevents overdrafts in withdrawals
- **Input Sanitization**: Handles `NumberFormatException` for invalid amounts
- **User Feedback**: Provides success confirmation with updated balance

#### TransferService
```java
class TransferService {
    public static void transferMoney(Account account, Account[] accounts, 
                                   int accountCount, Scanner scanner) {
        // Recipient lookup + transfer validation + execution
    }
}
```
**Transfer Logic Flow**:
1. **Recipient Validation**: Searches account array for target account
2. **Self-Transfer Prevention**: Blocks transfers to same account
3. **Balance Verification**: Ensures sufficient funds before transfer
4. **Atomic Operation**: Uses Account.transfer() for consistency
5. **Confirmation**: Displays transfer details and remaining balance

#### StatementService
```java
class StatementService {
    public static void displayMiniStatement(Account account) {
        // Display last 10 transactions in reverse chronological order
    }
}
```
**Statement Features**:
- **Recent History**: Shows last 10 transactions
- **Reverse Chronological**: Most recent transactions first
- **Formatted Display**: Professional statement layout with headers
- **Current Balance**: Always shows current balance at top
- **Empty State Handling**: Graceful handling when no transactions exist

### 4. User Interface Layer

#### ATMMenu Class
```java
class ATMMenu {
    public static void showMenu(Account account, Account[] accounts, 
                              int accountCount, Scanner scanner) {
        while (true) {
            // Menu display + option processing
        }
    }
}
```
**Menu Structure**:
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit

**Input Handling**:
- **Exception Safety**: All input wrapped in try-catch blocks
- **Invalid Option Handling**: Clear error messages for invalid choices
- **Continuous Loop**: Menu redisplays until user exits
- **Service Delegation**: Each option calls appropriate service method

### 5. System Controller

#### ATMSystem Class
```java
class ATMSystem {
    Account[] accounts;
    int accountCount;
    Scanner scanner;
    
    public ATMSystem() {
        // Initialize with predefined test accounts
    }
}
```
**System Management**:
- **Account Storage**: Fixed array of 10 accounts maximum
- **Predefined Data**: Three test accounts with different balances
- **Account Lookup**: Linear search through account array
- **Session Management**: Handles login/logout flow

#### Core System Methods

##### Account Management
```java
public void addAccount(Account acc) {
    if (accountCount < accounts.length) {
        accounts[accountCount] = acc;
        accountCount++;
    }
}

public Account findAccount(String accNo) {
    for (int a = 0; a < accountCount; a++) {
        if (accounts[a].accountNumber.equals(accNo)) {
            return accounts[a];
        }
    }
    return null;
}
```
**Logic**: Simple array-based account management with bounds checking

##### Main System Loop
```java
public void start() {
    while (true) {
        // Account number input
        // Account lookup
        // Authentication
        // Menu display
    }
}
```
**Session Flow**:
1. **Account Input**: Request account number or EXIT command
2. **Account Validation**: Check if account exists in system
3. **Authentication**: PIN verification with lockout protection
4. **Menu Access**: Full ATM functionality upon successful login
5. **Session Termination**: Return to login prompt after menu exit

### 6. Main Application

#### Main Class
```java
class Main {
    public static void main(String[] args) {
        ATMSystem atm = new ATMSystem();
        atm.start();
    }
}
```
**Purpose**: Simple application entry point that initializes and starts the ATM system

## Key Algorithms and Logic

### Transaction History Management
```java
public void addTransaction(String type, double amount, String accountNumber, double balanceAfter) {
    if (transactionCount < transactionHistory.length) {
        transactionHistory[transactionCount] = new TransactionRecord(type, amount, accountNumber, balanceAfter);
        transactionCount++;
    }
}
```
**Logic**: Circular buffer with overflow protection (stops at 50 transactions)

### Authentication Algorithm
**Three-Layer Security**:
1. **Account Existence**: Verify account number exists in system
2. **PIN Verification**: Maximum 3 attempts with lockout
3. **Session Management**: Successful authentication grants menu access

### Fund Transfer Algorithm
**Multi-Step Validation**:
1. Recipient account exists
2. Not transferring to self
3. Sufficient balance available
4. Execute atomic transfer
5. Log paired transactions

## Data Flow Architecture

### Input Processing Flow
```
User Input → Validation → Service Method → Account Update → Transaction Log → User Feedback
```

### Transaction Flow
```
Menu Selection → Input Gathering → Validation → Business Logic → Data Update → Confirmation
```

### Authentication Flow
```
Account Number → Account Lookup → PIN Request → PIN Validation → Menu Access/Lockout
```

## System Features

### Security Measures
- **PIN Protection**: 4-digit PIN with attempt limiting
- **Account Lockout**: Prevents brute force attacks
- **Input Validation**: Prevents invalid data entry
- **Session Isolation**: Each session is independent

### Transaction Management
- **Automatic Logging**: Every operation creates audit trail
- **Timestamp Precision**: Exact date/time for each transaction
- **Balance Consistency**: All operations maintain balance integrity
- **Transaction History**: Persistent record of all activities

### User Experience
- **Clear Navigation**: Numbered menu options
- **Error Handling**: Informative error messages
- **Confirmation Messages**: Success feedback for all operations
- **Formatted Output**: Professional display formatting

### Data Persistence
- **In-Memory Storage**: All data stored in runtime memory
- **Session-Based**: Data persists only during application runtime
- **Predefined Accounts**: System starts with test accounts
- **Transaction Continuity**: History maintained throughout session

## Technical Implementation Notes

### Array Management
- **Fixed-Size Arrays**: Both accounts and transaction history use fixed arrays
- **Bounds Checking**: All array operations include overflow protection
- **Linear Search**: Simple sequential search for account lookup

### Exception Handling
- **Input Validation**: All user inputs wrapped in try-catch blocks
- **Graceful Degradation**: Invalid inputs don't crash the system
- **User-Friendly Messages**: Technical exceptions converted to user messages

### Memory Management
- **Static Methods**: Service classes don't maintain state
- **Minimal Object Creation**: Efficient use of system resources
- **Garbage Collection Friendly**: No circular references or memory leaks