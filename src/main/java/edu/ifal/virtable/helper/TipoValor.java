package edu.ifal.virtable.helper;

public enum TipoValor {
    String("STR"),
    Int("INT");

    private final String label;

    TipoValor(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static TipoValor valueOfLabel(String label) {
        for (TipoValor status : values()) {
            if (status.label.equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}