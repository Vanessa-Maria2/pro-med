package br.edu.ufrn.promed.enums;

import java.time.DayOfWeek;
public enum DiaSemana {
    DOMINGO(1, DayOfWeek.SUNDAY),
    SEGUNDA(2, DayOfWeek.MONDAY),
    TERCA(3, DayOfWeek.TUESDAY),
    QUARTA(4, DayOfWeek.WEDNESDAY),
    QUINTA(5, DayOfWeek.THURSDAY),
    SEXTA(6, DayOfWeek.FRIDAY),
    SABADO(7, DayOfWeek.SATURDAY);

    private final int id;
    private final DayOfWeek diaJava;

    DiaSemana(int id, DayOfWeek diaJava) {
        this.id = id;
        this.diaJava = diaJava;
    }

    public int getId() {
        return id;
    }

    public DayOfWeek getDiaJava() {
        return diaJava;
    }

    public static DiaSemana fromId(int id) {
        for (DiaSemana dia : values()) {
            if (dia.id == id) {
                return dia;
            }
        }
        throw new IllegalArgumentException("Id inválido para DiaSemana: " + id);
    }
}