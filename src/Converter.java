package converter;
 
public abstract class Converter {
 
	//each subclass must provide its list of unit names
	public abstract String[] getUnits();
 
	//each subclass must implement the conversion logic
	public abstract double convert(double value, String fromUnit, String toUnit);
 
	//returns the category name
	public abstract String getCategoryName();
 
}
