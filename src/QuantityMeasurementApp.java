public class QuantityMeasurementApp {

    // Enum for supported length units
    public enum LengthUnit {
        FEET(12.0),          // 1 foot = 12 inches
        INCH(1.0),           // base unit is inch
        YARD(36.0),          // 1 yard = 3 feet = 36 inches
        CENTIMETER(0.393701); // 1 cm = 0.393701 inches

        private final double conversionFactorToInch;

        LengthUnit(double conversionFactorToInch) {
            this.conversionFactorToInch = conversionFactorToInch;
        }

        public double toBaseUnit(double value) {
            return value * conversionFactorToInch;
        }
    }

    // Generic QuantityLength class
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            double thisValueInInches = this.unit.toBaseUnit(this.value);
            double otherValueInInches = other.unit.toBaseUnit(other.value);

            return Double.compare(thisValueInInches, otherValueInInches) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(unit.toBaseUnit(value));
        }
    }

    // Demo main method
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println("Feet vs Inches equality: " + q1.equals(q2));

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println("Yard vs Feet equality: " + q3.equals(q4));

        QuantityLength q5 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength q6 = new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println("Centimeter vs Inch equality: " + q5.equals(q6));
    }
}
