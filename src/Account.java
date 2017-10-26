/*************************************************************************
 * Title: 
 * File: .java
 * Author: James Eli
 * Date: 2/6/2017
 *
 * This is the abstract class which defines a banking account. 
 *
 * Notes: 
 *  (1) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 * 
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   02/6/2017: Initial release. JME
 *************************************************************************/
import java.util.LinkedList;

public abstract class Account {
  /*********************************************************************
   * Constant field limitations (all public)
   *********************************************************************/
  public static final int MIN_CUSTOMER_ID = 0;           // Minimum & maximum customer id range.
  public static final int MAX_CUSTOMER_ID = (int)Integer.MAX_VALUE;
  public static final int MIN_ACCOUNT_NUMBER = 0;        // Minimum & maximum account number range.
  public static final int MAX_ACCOUNT_NUMBER = (int)Integer.MAX_VALUE;
  public static final double MIN_INTEREST_RATE = 0.0d;   // Minimum interest rate.
  public static final double MAX_INTEREST_RATE = 100.0d; // Maximum interest rate.

  /*********************************************************************
   * Instance fields (all private)
   *********************************************************************/
  protected int customerID;             // Customer id.
  protected int accountNumber;          // Account number.
  protected char accountType;           // Account type: ‘c’ for checking, ‘m’ for mortgage 
  protected double interestRate;        // Interest rate (if applicable for account).
  protected double monthlyInterestRate; // (annual interest rate / 12) 
  protected double balance;             // Account balance.
  // Linked list of account transactions.
  protected LinkedList<Transaction> transactionList = new LinkedList<Transaction>();
  
  /*********************************************************************
   * Class constructors.
   *********************************************************************/
  public Account(
	    int customerID,      // Customer id.
	    int accountNumber,   // Account Number.
	    char accountType,    // Account type: ‘c’ for checking, ‘m’ for mortgage 
	    double interestRate, // Interest rate (if applicable to account).
	    double balance       // Account balance.
	  ) {
    setCustomerID( customerID );
    setAccountNumber( accountNumber );
    setAccountType( accountType );
    setInterestRate( interestRate );
    setBalance( balance );
  }

  /*********************************************************************
   * Class mutators.
   *********************************************************************/
  protected void setCustomerID( int id ) {
  	// Throw exception for invalid customer number.
    if ( id < MIN_CUSTOMER_ID || id > MAX_CUSTOMER_ID )
      throw new IllegalArgumentException( "Customer ID must be between " + MIN_CUSTOMER_ID + "and " + MAX_CUSTOMER_ID + "." );
    else
	  this.customerID = id; 
  }

  protected void setAccountNumber( int num ) {
	// Throw exception for invalid customer number.
    if ( num < MIN_ACCOUNT_NUMBER || num > MAX_ACCOUNT_NUMBER )
      throw new IllegalArgumentException( "Account number must be between " + MIN_ACCOUNT_NUMBER + "and " + MAX_ACCOUNT_NUMBER + "." );
    else
	  this.accountNumber = num; 
  }

  protected void setAccountType( char type ) {
    // 'c' indicates checking; 'm' indicates mortgage
	type = java.lang.Character.toLowerCase( type ); // Convert to lower case.
    if ( type != 'c' && type != 'm' ) 
      throw new IllegalArgumentException( "Account type must be 'c' or 'm'." );
    else
  	  this.accountType = type; 
  }

  protected void setInterestRate( double rate ) {
    if ( rate < MIN_INTEREST_RATE || rate > MAX_INTEREST_RATE ) 
      throw new IllegalArgumentException( "Interest rate must be between " + MIN_INTEREST_RATE + "and " + MAX_INTEREST_RATE + "." );
    else {
   	  this.interestRate = rate;
      this.monthlyInterestRate = (rate/12.);
    }
  }

  protected void setBalance( double bal ) {
	// Round balance before setting.
    this.balance = UtilityMethods.round( bal ); 
  }

  /*********************************************************************
   * Class accessors.
   *********************************************************************/
  protected int getCustomerID() { return customerID; } 
  protected int getAccountNumber() { return accountNumber; }
  protected char getAccountType() { return accountType; }
  protected double getInterestRate() { return interestRate; }
  protected double getBalance() { return balance; }

  /*********************************************************************
   * Class methods.
   *********************************************************************/
  abstract protected void processTransaction( Transaction t );

  protected String listTransactions() {
    StringBuffer strBuf = new StringBuffer();

	for ( Transaction t : transactionList )
      strBuf.append( t );
    return strBuf.toString();
  }
  
  /*********************************************************************
   * Overridden methods.
   *********************************************************************/
  @Override
  public String toString() {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append( "Account number     : " );
    strBuf.append( accountNumber );
    strBuf.append( "\nCustomer ID        : " );
    strBuf.append( customerID );
    strBuf.append( "\nAccount type       : " );
    strBuf.append( accountType );
    strBuf.append( "\nBalance            : " );
    strBuf.append( balance );
    return strBuf.toString();
  }

}
