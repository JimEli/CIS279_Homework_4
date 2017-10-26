/***********************************************
 *  Program : UtilityMethods.java
 *  Purpose : Provides utility rounding method.
 **********************************************/
import java.math.BigDecimal;

class UtilityMethods {
  public static double round( double value ) {
	// Convert double to BigDecimal, round to 2 places, and convert back to double. 
    return BigDecimal.valueOf( value ).setScale( 2, BigDecimal.ROUND_HALF_UP ).doubleValue();
  }
}
