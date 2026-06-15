package edu.ifal.virtable.helper;

public enum TipoCampo {
    Set("SET"), // pode ter mais de um valor de campo por personagem
    One("ONE"); // um valor por campo por personagem

    private final String label;

    TipoCampo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static TipoCampo valueOfLabel(String label) {
        for (TipoCampo status : values()) {
            if (status.label.equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}