/*************************************************************************
 * Title: 
 * File: .java
 * Author: James Eli
 * Date: 2/6/2017
 *
 * This program defines a checking account class based upon the abstract
 * account class.
 *
 * Notes: 
 *  (1) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 * 
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   02/06/2017: Initial release. JME
 *************************************************************************/
public class CheckingAccount extends Account {
  /*********************************************************************
   * Class constructor.
   *********************************************************************/
  public CheckingAccount( int customerID, int accountNumber, double balance ) {
    super( customerID, accountNumber, 'c', 0.0d, balance );
  }

  /*********************************************************************
   * Overridden methods.
   *********************************************************************/
  @Override
  protected void processTransaction( Transaction t ) {
	if ( t.getTransactionType() == Transaction.CHECK ) {
      setBalance( balance - t.getTransactionAmount() );
      transactionList.add( t );
	}
	else if ( t.getTransactionType() == Transaction.DEPOSIT ) {
      setBalance( balance + t.getTransactionAmount() );
      transactionList.add( t );
    }
	else
      throw new IllegalArgumentException( "Invalid transaction type: " + t.getTransactionType() );
  }
}
