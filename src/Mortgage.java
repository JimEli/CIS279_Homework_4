/*************************************************************************
 * Title: 
 * File: .java
 * Author: James Eli
 * Date: 2/6/2017
 *
 * This program defines a mortgage account class based upon the abstract
 * account class.
 *
 * Notes: 
 *  (1) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 * 
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   02/6/2017: Initial release. JME
 *************************************************************************/
public class Mortgage extends Account {
  /*********************************************************************
   * Constant field limitations (all public)
   *********************************************************************/
  public static final int MIN_MORTGAGE_TERM = 3;  // Minimum allowable mortgage loan term (years).
  public static final int MAX_MORTGAGE_TERM = 30; // Maximum allowable mortgage loan term (years).
	
  /*********************************************************************
   * Instance fields (all private)
   *********************************************************************/
  private int termInYears;             // Mortgage loan term in years.
  private int termInMonths;            // Mortgage loan term in months.
  private double periodicPayment;      // Mortgage periodic payment amount.
  private double balanceRepaid;        // Mortgage loan amount repaid.
  private double currentMonthInterest; // Mortgage loan monthly interest repayment amount.
  
  /*********************************************************************
   * Class constructors.
   *********************************************************************/
  public Mortgage(
      int customerID,      // Customer ID.
      int accountNumber,   // Mortgage account number.
      double interestRate, // Mortgage annual interest rate.
      double balance,      // Mortgage account balance.
      int termInYears      // Mortgage term in years.
    ) {
      super( customerID, accountNumber, 'm', interestRate, balance );
      setTerm( termInYears );
      calcPeriodicPayment();
    }

  /*********************************************************************
   * Class mutators.
   *********************************************************************/
  private void setTerm( int term ) {
  	// Throw exception for invalid customer number.
    if ( term < MIN_MORTGAGE_TERM || term > MAX_MORTGAGE_TERM )
      throw new IllegalArgumentException( "Mortgage term must be between " + MIN_MORTGAGE_TERM + "and " + MAX_MORTGAGE_TERM + "." );
    else {
      this.termInMonths = term*12;
      this.termInYears = term;
    }
  }

  private void setBalanceRepaid( double amount ) {
	// Round balance before setting.
    balanceRepaid = UtilityMethods.round( amount );
  }

  private void setCurrentMonthInterest( double interest ) {
    if ( interest < 0.0d  ) 
      throw new IllegalArgumentException( "Current month interest cannot be negative." );
    else
      // Round amount before setting.
   	  this.currentMonthInterest = UtilityMethods.round( interest ); 
  }

  /*********************************************************************
   * Class accessors.
   *********************************************************************/
  public int getTerm() { return termInYears; }
  public double getBalancerepaid() { return balanceRepaid; }
  public double getCurrentMonthInterest() { return currentMonthInterest; }
  
  /*********************************************************************
   * Class methods.
   *********************************************************************/
  // Calculate loan periodic payment. 
  public void calcPeriodicPayment() {
    double annuityFactor = ( ( 1. - ( 1./Math.pow((1. + monthlyInterestRate ), termInMonths) ) )/monthlyInterestRate ); 
	// Round payment amount before setting.
    periodicPayment = UtilityMethods.round( balance/annuityFactor );  
  } 

  /*********************************************************************
   * Overridden methods.
   *********************************************************************/
  @Override
  public void processTransaction( Transaction t ) {
	// Calculate interest the amount repaid and change the account balance. 
    if ( t.getTransactionType() == Transaction.PAYMENT ) {
      setCurrentMonthInterest( balance*monthlyInterestRate ); 
      setBalanceRepaid( periodicPayment - currentMonthInterest );
      setBalance( balance - balanceRepaid );
      transactionList.add( t );
    } else
      throw new IllegalArgumentException( "Invalid transaction type: " + t.getTransactionType() );
  }

  // overrides base class toString(). It invokes the base class method
  // to display its data members then displays the values of the
  // members it has added.
  @Override
  public String toString() {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append( super.toString() );
    strBuf.append( "\nInterest rate      : " );
    strBuf.append( interestRate );
    strBuf.append( "\nTerm               : " );
    strBuf.append( termInYears );
    strBuf.append( "\nPeriodic Payment   : " );
    strBuf.append( periodicPayment );
    return strBuf.toString();
  }
}
