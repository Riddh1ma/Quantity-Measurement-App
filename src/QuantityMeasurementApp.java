public class QuantityMeasurementApp {

    // Step 1: Enum for units
    enum LengthUnit {
        FEET(1.0),          // base unit
        INCH(1.0 / 12.0);   // 1 inch = 1/12 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Step 2: Generic Quantity class
    static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        // Override equals()
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null + type check
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // Compare after converting to common unit
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println("1 ft vs 12 inch: " + q1.equals(q2));

        Quantity q3 = new Quantity(1.0, LengthUnit.INCH);
        Quantity q4 = new Quantity(1.0, LengthUnit.INCH);

        System.out.println("1 inch vs 1 inch: " + q3.equals(q4));
    }
}