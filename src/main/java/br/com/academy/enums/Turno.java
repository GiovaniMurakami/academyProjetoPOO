package br.com.academy.enums;

public enum Turno {
    MANHA("Manha"),
    TARDE("Tarde"),
    NOITE("Noite");

    private String turno;

    private Turno(String turno) {
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public static Turno fromString(String turnoString) {
        for (Turno turno : Turno.values()) {
            if (turno.getTurno().equalsIgnoreCase(turnoString)) {
                return turno;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + turnoString);
    }
}