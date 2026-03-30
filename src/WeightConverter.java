package converter;

public class WeightConverter extends Converter {

    @Override
    public String getCategoryName() {
        return "Weight";
    }

    @Override
    public String[] getUnits() {
        return new String[]{"Kilograms", "Pounds", "Stone"};
    }

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        //convert input to kilograms (base unit)
        double inKg = toKilograms(value, fromUnit);
        //convert kilograms to target unit
        return fromKilograms(inKg, toUnit);
    }

    private double toKilograms(double value, String unit) {
        switch (unit) {
            case "Kilograms": 
            	return value;
            case "Pounds":    
            	return value * 0.453592;
            case "Stone":     
            	return value * 6.35029;
            default:          
            	return value;
        }
    }

    private double fromKilograms(double kg, String unit) {
        switch (unit) {
            case "Kilograms": 
            	return kg;
            case "Pounds":    
            	return kg / 0.453592;
            case "Stone":     
            	return kg / 6.35029;
            default:         
            	return kg;
        }
    }

}