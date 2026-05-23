package br.edu.ceub.hospital;

public class Paciente extends UsuarioHospitalar {

    public Paciente(HospitalMediator mediador, String nome) {
        super(mediador, nome);
    }

    @Override
    public void receberNotificacao(String mensagem) {
        System.out.println("-> [PRONTUÁRIO] Paciente " + this.nome + " recebeu atualização: " + mensagem);
    }
}
