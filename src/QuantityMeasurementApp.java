public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    public static class Feet {
        private final double value; // encapsulated, immutable

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals for value-based equality
        @Override
        public boolean equals(Object obj) {
            // Reflexive check
            if (this == obj) return true;

            // Null check
            if (obj == null) return false;

            // Type check
            if (this.getClass() != obj.getClass()) return false;

            // Safe cast
            Feet other = (Feet) obj;

            // Compare double values safely
            return Double.compare(this.value, other.value) == 0;
        }

        // Optional: Override hashCode to maintain equality contract
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method for quick verification
    public static void main(String[] args) {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Are they equal? " + f1.equals(f2));
    }
}
