/*************************************************************************
 * Title: 
 * File: .java
 * Author: James Eli
 * Date: 2/6/2017
 *
 * Banking account driver program. Tests account, mortgage, checking 
 * account and transaction classes.
 * 
 * Notes: 
 *  (1) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 * 
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   02/06/2017: Initial release. JME
 *************************************************************************/
import java.util.LinkedList;

public class AccountDriver {
  public static void main(String[] args) {
    final char PAYMENT_TYPE = 'P'; // Mortgage account, Payment activity.
    final char CHECK_TYPE   = 'C'; // Checking account, debit activity.
    final char DEPOSIT_TYPE = 'D'; // Checking account, deposit activity.

    // Here we declare and instantiate an empty LinkedList of the base class.
    LinkedList<Account> accountList = new LinkedList<Account>();

    // Create 2 checking account objects.
    CheckingAccount[] checking = new CheckingAccount[2];
    // Instantiate checking accounts.
    checking[0] = new CheckingAccount( 1000, 10000, 100. );
    checking[1] = new CheckingAccount( 2000, 20000, 200. );
    // Record 8 transactions (4 each account).
    checking[0].processTransaction( new Transaction( 11, 20170125, 25.01, DEPOSIT_TYPE ) );
    checking[1].processTransaction( new Transaction( 21, 20160125, 50.02, DEPOSIT_TYPE ) );
    checking[1].processTransaction( new Transaction( 22, 20160126, 50.02, CHECK_TYPE ) );
    checking[0].processTransaction( new Transaction( 12, 20170126, 53.28, CHECK_TYPE ) );
    checking[1].processTransaction( new Transaction( 23, 20160127, 29.88, CHECK_TYPE ) );
    checking[0].processTransaction( new Transaction( 13, 20170127, 72.67, DEPOSIT_TYPE ) );
    checking[0].processTransaction( new Transaction( 14, 20170128, 33.33, CHECK_TYPE ) );
    checking[1].processTransaction( new Transaction( 24, 20160128, 187.13, CHECK_TYPE ) );
    accountList.addLast( checking[0] );
    accountList.addLast( checking[1] );
    // Assert checking account balances.
    assert( checking[0].getBalance() == UtilityMethods.round( 100.00 + 25.01 - 53.28 + 72.67 - 33.33 ) ) : "Checking Account #1 balance fail.";
    assert( checking[1].getBalance() == UtilityMethods.round( 200.0 + 50.02 -50.02 - 29.88 - 187.13 ) ) : "Checking Account #2 balance fail.";

    // Create 2 mortgage account objects.
    Mortgage[] mortgage = new Mortgage[2];
    // Instantiate mortgage accounts.
    mortgage[0] = new Mortgage( 500, 50000, 0.06875, 95000, 15 );
    mortgage[1] = new Mortgage( 600, 60000, 0.0375, 260000, 30 );
    // Record 8 transactions (4 each account).
    mortgage[0].processTransaction( new Transaction( 5001, 20150515, 847.26, PAYMENT_TYPE ) );
    mortgage[0].processTransaction( new Transaction( 5002, 20150615, 847.26, PAYMENT_TYPE ) );
    mortgage[0].processTransaction( new Transaction( 5003, 20150715, 847.26, PAYMENT_TYPE ) );
    mortgage[0].processTransaction( new Transaction( 5004, 20150815, 847.26, PAYMENT_TYPE ) );
    mortgage[1].processTransaction( new Transaction( 6001, 20160515, 1204.10, PAYMENT_TYPE ) );
    mortgage[1].processTransaction( new Transaction( 6002, 20160615, 1204.10, PAYMENT_TYPE ) );
    mortgage[1].processTransaction( new Transaction( 6003, 20160715, 1204.10, PAYMENT_TYPE ) );
    mortgage[1].processTransaction( new Transaction( 6004, 20160815, 1204.10, PAYMENT_TYPE ) );
	accountList.addLast( mortgage[0] );
	accountList.addLast( mortgage[1] );
    // Assert mortgage balances (values from excel spreadsheet).
    assert( mortgage[0].getBalance() == 93777.58 ) : "Mortgage Account #1 balance fail.";
    assert( mortgage[1].getBalance() == 258426.25 ) : "Mortgage Account #2 balance fail.";

    // Loop through accounts displaying information.
	for ( Account accountObj : accountList ) {
      System.out.println( accountObj.toString() ) ;
      System.out.println( accountObj.listTransactions() ) ;
      System.out.println( "The current account balance is " + accountObj.getBalance() + "\n\n" );
    }

	// Normal program terminates here.

  } // End of main module.
  
} // End of EliJames_HW4 class.
