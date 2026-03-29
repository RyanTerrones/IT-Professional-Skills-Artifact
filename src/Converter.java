package converter;
 
public abstract class Converter {
 
	// Each subclass must provide its list of unit names
	public abstract String[] getUnits();
 
	// Each subclass must implement the conversion logic
	public abstract double convert(double value, String fromUnit, String toUnit);
 
	// Returns the category name e.g. "Currency", "Temperature"
	public abstract String getCategoryName();
 
}
