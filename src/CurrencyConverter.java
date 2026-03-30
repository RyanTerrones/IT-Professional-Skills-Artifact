package converter;
 
public class CurrencyConverter extends Converter {
 
	//fixed exchange rates relative to EUR as the base currency
	//rates are approximate as of early 2025
	private static final double EUR_TO_USD = 1.08;
	private static final double EUR_TO_GBP = 0.86;
	private static final double EUR_TO_JPY = 162.50;
	private static final double EUR_TO_CHF = 0.96;
    private static final double EUR_TO_CAD = 1.46;
    private static final double EUR_TO_AUD = 1.65;
    private static final double EUR_TO_CNY = 7.78;
    private static final double EUR_TO_INR = 89.50;
    private static final double EUR_TO_MXN = 19.80;
 
	@Override
	public String getCategoryName() {
		return "Currency";
	}
 
	@Override
	public String[] getUnits() {
		return new String[]{"EUR", "USD", "GBP", "JPY", "CHF", "CAD", "AUD", "CNY", "INR", "MXN"};
	}
 
	@Override
	public double convert(double value, String fromUnit, String toUnit) {
		//convert input to EUR (base currency)
		double inEuro = toEuro(value, fromUnit);
		//convert EUR to target unit
		return fromEuro(inEuro, toUnit);
	}
 
	//converts any supported currency to EUR
	private double toEuro(double value, String unit) {
		switch (unit) {
			case "EUR": 
				return value;
			case "USD": 
				return value / EUR_TO_USD;
			case "GBP": 
				return value / EUR_TO_GBP;
			case "JPY": 
				return value / EUR_TO_JPY;
			case "CHF": 
				return value / EUR_TO_CHF;
            case "CAD": 
            	return value / EUR_TO_CAD;
            case "AUD": 
            	return value / EUR_TO_AUD;
            case "CNY": 
            	return value / EUR_TO_CNY;
            case "INR": 
            	return value / EUR_TO_INR;
            case "MXN": 
            	return value / EUR_TO_MXN;
			default:    
				return value;
		}
	}
 
	//converts EUR to any supported currency
	private double fromEuro(double euro, String unit) {
		switch (unit) {
			case "EUR": 
				return euro;
			case "USD": 
				return euro * EUR_TO_USD;
			case "GBP": 
				return euro * EUR_TO_GBP;
			case "JPY": 
				return euro * EUR_TO_JPY;    
			case "CHF": 
				return euro * EUR_TO_CHF;
            case "CAD": 
            	return euro * EUR_TO_CAD;
            case "AUD": 
            	return euro * EUR_TO_AUD;
            case "CNY": 
            	return euro * EUR_TO_CNY;
            case "INR": 
            	return euro * EUR_TO_INR;
            case "MXN": 
            	return euro * EUR_TO_MXN;
            default: return euro;
		}
	}
	
	//gets full name of currency
	public String getFullName(String unit) {
	    switch (unit) {
	        case "EUR": 
	        	return "Euro";
	        case "USD": 
	        	return "US Dollar";
	        case "GBP": 
	        	return "British Pound";
	        case "JPY": 
	        	return "Japanese Yen";
	        case "CHF": 
	        	return "Swiss Franc";
	        case "CAD":
	        	return "Canadian Dollar";
	        case "AUD": 
	        	return "Australian Dollar";
	        case "CNY": 
	        	return "Chinese Yuan";
	        case "INR": 
	        	return "Indian Rupee";
	        case "MXN": 
	        	return "Mexican Peso";
	        default:   
	        	return unit;
	    }
	}
 
}
