public class QuantityMeasurementApp {

    // Enum for supported length units
    public enum LengthUnit {
        FEET(12.0),   // 1 foot = 12 inches
        INCH(1.0);    // base unit is inch

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

            // Convert both values to base unit (inches) before comparison
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

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("Inches equality: " + q3.equals(q4));
    }
}
