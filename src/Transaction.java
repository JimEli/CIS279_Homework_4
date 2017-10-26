/*************************************************************************
 * Title: 
 * File: .java
 * Author: James Eli
 * Date: 2/6/2017
 *
 * This class defines transaction for the abstract definition of a banking
 * account class. Used by Mortgage and CheckingAccount classes. 
 *
 * Notes: 
 *  (1) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 * 
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   02/06/2017: Initial release. JME
 *************************************************************************/
public class Transaction {
  /*********************************************************************
   * Constant field limitations (all public)
   *********************************************************************/
   public static final char PAYMENT = 'P';
   public static final char DEPOSIT = 'D';
   public static final char CHECK = 'C';
   public final static int MIN_TRANSACTION_ID = 0;                 // Minimum transaction id.
   public final static int MAX_TRANSACTION_ID = Integer.MAX_VALUE; // Maximum transaction id.
   
  /*********************************************************************
   * Instance fields (all private)
   *********************************************************************/
  // can be check number for a check or just an ID  number for
  // checking account deposits and mortgage payments.
  private int transactionID;
  private int transactionDate;
  private double amount;
  private char type;

  /*********************************************************************
   * Class constructors.
   *********************************************************************/
  // 4-parameter constructor.
  public Transaction( int transactionID, int transactionDate, double amount , char type ) {
    setTransactionID( transactionID );
    setTransactionDate( transactionDate );
    setTransactionAmount( amount );
    setTransactionType( type );
  }

  /*********************************************************************
   * Class mutators.
   *********************************************************************/
  public void setTransactionID( int transactionID ) {
	// Throw exception for invalid customer number.
    if ( transactionID < MIN_TRANSACTION_ID || transactionID > MAX_TRANSACTION_ID )
      throw new IllegalArgumentException( "Account number must be between " + MIN_TRANSACTION_ID + "and " + MAX_TRANSACTION_ID + "." );
    else
      this.transactionID = transactionID ;
  }

  public void setTransactionDate( int transactionDate ) {
 	this.transactionDate = transactionDate ;
  }

  public void setTransactionType( char type ) { 
    type = java.lang.Character.toUpperCase( type ); // Convert to upper case.
    this.type = type ; 
  }

  public void setTransactionAmount( double amount ) { 
    this.amount = amount; 
  }
  
  /*********************************************************************
   * Class accessors.
   *********************************************************************/
  public int getTransactionDate() { return transactionDate ; }
  public double getTransactionAmount() { return amount ; }
  public char getTransactionType() { return type ; }
  public int getTransactionID() { return transactionID; }

  /*********************************************************************
   * Overridden methods.
   *********************************************************************/
  @Override
  public String toString() {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append( "\nTransaction ID     : " );
    strBuf.append( transactionID );
    strBuf.append( "\nTransaction Date   : " );
    strBuf.append( transactionDate );
    strBuf.append( "\nTransaction amount : " );
    strBuf.append( amount );
    strBuf.append( "\nTransaction type   : " );
    strBuf.append( type );
    strBuf.append( "\n" ) ;
    return strBuf.toString() ;
  }
}
