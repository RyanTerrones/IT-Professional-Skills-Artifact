package converter;

public class TemperatureConverter extends Converter {

    @Override
    public String getCategoryName() {
        return "Temperature";
    }

    @Override
    public String[] getUnits() {
        return new String[]{"Celsius", "Fahrenheit", "Kelvin"};
    }

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        //convert input to Celsius (base unit)
        double inCelsius = toCelsius(value, fromUnit);
        //convert Celsius to target unit
        return fromCelsius(inCelsius, toUnit);
    }

    private double toCelsius(double value, String unit) {
        switch (unit) {
            case "Celsius":   
            	return value;
            case "Fahrenheit": 
            	return (value - 32) * 5.0 / 9.0;
            case "Kelvin":     
            	return value - 273.15;
            default:           
            	return value;
        }
    }

    private double fromCelsius(double celsius, String unit) {
        switch (unit) {
            case "Celsius":    
            	return celsius;
            case "Fahrenheit": 
            	return (celsius * 9.0 / 5.0) + 32;
            case "Kelvin":     
            	return celsius + 273.15;
            default:           
            	return celsius;
        }
    }
}


