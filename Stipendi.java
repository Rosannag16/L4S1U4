package Esercizio;

import java.util.Arrays;

enum Dipartimento {
    PRODUZIONE,
    AMMINISTRAZIONE,
    VENDITE
}

abstract class Dipendente implements CheckIn {
    private String matricola;
    private double stipendio;
    private Dipartimento dipartimento;

    public Dipendente(String matricola, double stipendio, Dipartimento dipartimento) {
        this.matricola = matricola;
        this.stipendio = stipendio;
        this.dipartimento = dipartimento;
    }

    public String getMatricola() {
        return matricola;
    }

    public double getStipendio() {
        return stipendio;
    }

    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    @Override
    public void checkIn() {
        System.out.println("Il dipendente " + getMatricola() + " ha iniziato il suo turno di lavoro.");
    }

    public abstract double calculateSalary();
}

class DipendenteFullTime extends Dipendente {
    public DipendenteFullTime(String matricola, double stipendio, Dipartimento dipartimento) {
        super(matricola, stipendio, dipartimento);
    }

    @Override
    public double calculateSalary() {
        return getStipendio();
    }
}

class DipendentePartTime extends Dipendente {
    private int oreLavorate;
    private double salarioOrario;

    public DipendentePartTime(String matricola, int oreLavorate, double salarioOrario, Dipartimento dipartimento) {
        super(matricola, 0, dipartimento);
        this.oreLavorate = oreLavorate;
        this.salarioOrario = salarioOrario;
    }

    @Override
    public double calculateSalary() {
        return oreLavorate * salarioOrario;
    }
}

class Dirigente extends Dipendente {
    private double bonus;

    public Dirigente(String matricola, double stipendio, Dipartimento dipartimento, double bonus) {
        super(matricola, stipendio, dipartimento);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getStipendio() + bonus;
    }
}

class Volontario implements CheckIn {
    private String nome;
    private int eta;
    private String cv;

    public Volontario(String nome, int eta, String cv) {
        this.nome = nome;
        this.eta = eta;
        this.cv = cv;
    }

    @Override
    public void checkIn() {
        System.out.println("Il volontario " + nome + " ha iniziato il suo servizio.");
    }
}

interface CheckIn {
    void checkIn();
}

public class Stipendi {
    public static void main(String[] args) {
        Dipendente dipendente1 = new DipendenteFullTime("001", 2500.00, Dipartimento.PRODUZIONE);
        Dipendente dipendente2 = new DipendentePartTime("002", 20, 15.00, Dipartimento.AMMINISTRAZIONE);
        Dipendente dipendente3 = new Dirigente("003", 3000.00, Dipartimento.VENDITE, 1500.00);

        Volontario volontario1 = new Volontario("Marco", 25, "Esperienza nel settore umanitario");

        CheckIn[] persone = {dipendente1, dipendente2, dipendente3, volontario1};

        for (CheckIn persona : persone) {
            persona.checkIn();
        }
    }
}
