package converter;

public class DistanceConverter extends Converter {

    @Override
    public String getCategoryName() {
        return "Distance";
    }

    @Override
    public String[] getUnits() {
        return new String[]{"Kilometres", "Miles", "Metres"};
    }

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        //convert input to metres (base unit)
        double inMetres = toMetres(value, fromUnit);
        //convert metres to target unit
        return fromMetres(inMetres, toUnit);
    }

    private double toMetres(double value, String unit) {
        switch (unit) {
            case "Kilometres": return value * 1000;
            case "Miles":      return value * 1609.344;
            case "Metres":     return value;
            default:           return value;
        }
    }

    private double fromMetres(double metres, String unit) {
        switch (unit) {
            case "Kilometres": return metres / 1000;
            case "Miles":      return metres / 1609.344;
            case "Metres":     return metres;
            default:           return metres;
        }
    }

}
