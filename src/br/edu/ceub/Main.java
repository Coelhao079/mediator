package br.edu.ceub;

import br.edu.ceub.hospital.HospitalCentral;
import br.edu.ceub.hospital.HospitalMediator;
import br.edu.ceub.hospital.Medico;
import br.edu.ceub.hospital.Paciente;

public class Main {
    public static void main(String[] args) {
        // 1. Instancia o Mediador Central
        HospitalMediator hospital = new HospitalCentral();

        // 2. Instancia os Colegas passando o Mediador no construtor
        Medico medico1 = new Medico(hospital, "Ana Silva");
        Medico medico2 = new Medico(hospital, "Carlos Souza");
        
        Paciente paciente1 = new Paciente(hospital, "Rodrigo Santos");
        Paciente paciente2 = new Paciente(hospital, "Maria Oliveira");

        // 3. Cadastra os colegas no Mediador
        hospital.addUsuario(medico1);
        hospital.addUsuario(medico2);
        hospital.addUsuario(paciente1);
        hospital.addUsuario(paciente2);

        // 4. Simulação do fluxo de APS
        System.out.println("--- INÍCIO DA SIMULAÇÃO HOSPITALAR ---");
        
        // Fluxo correto: Médico solicita exame para paciente existente
        medico1.solicitarExame("Hemograma Completo", "Rodrigo Santos");
        medico2.solicitarExame("Ressonância Magnética", "Maria Oliveira");

        // Fluxo alternativo: Médico tenta solicitar exame para paciente não cadastrado
        medico1.solicitarExame("Eletrocardiograma", "João Ninguém");
        
        System.out.println("\n--- FIM DA SIMULAÇÃO ---");
    }
}
