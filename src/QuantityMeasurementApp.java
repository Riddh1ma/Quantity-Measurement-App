public class QuantityMeasurementApp {

    // Updated Enum with new units
    enum LengthUnit {
        FEET(1.0),                 // base unit
        INCH(1.0 / 12.0),          // 1 inch = 1/12 feet
        YARD(3.0),                 // 1 yard = 3 feet
        CENTIMETER(0.393701 / 12.0); // 1 cm = 0.393701 inch → convert to feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Quantity class (UNCHANGED from UC3)
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

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main method (testing)
    public static void main(String[] args) {

        System.out.println("1 yard vs 3 feet: " +
                new Quantity(1.0, LengthUnit.YARD)
                        .equals(new Quantity(3.0, LengthUnit.FEET)));

        System.out.println("1 yard vs 36 inch: " +
                new Quantity(1.0, LengthUnit.YARD)
                        .equals(new Quantity(36.0, LengthUnit.INCH)));

        System.out.println("2 yard vs 2 yard: " +
                new Quantity(2.0, LengthUnit.YARD)
                        .equals(new Quantity(2.0, LengthUnit.YARD)));

        System.out.println("2 cm vs 2 cm: " +
                new Quantity(2.0, LengthUnit.CENTIMETER)
                        .equals(new Quantity(2.0, LengthUnit.CENTIMETER)));

        System.out.println("1 cm vs 0.393701 inch: " +
                new Quantity(1.0, LengthUnit.CENTIMETER)
                        .equals(new Quantity(0.393701, LengthUnit.INCH)));
    }
}