package br.edu.ceub.hospital;

public class Medico extends UsuarioHospitalar {

    public Medico(HospitalMediator mediador, String nome) {
        super(mediador, nome);
    }

    /**
     * Solicita um teste mandando a ordem diretamente para o mediador.
     */
    public void solicitarExame(String exame, String nomePaciente) {
        System.out.println("\n[CRM] Dr(a). " + this.nome + " emitiu solicitação de: " + exame + " para o(a) paciente " + nomePaciente + ".");
        mediador.registrarExame(exame, this, nomePaciente);
    }

    @Override
    public void receberNotificacao(String mensagem) {
        System.out.println("-> Alerta para Dr(a). " + this.nome + ": " + mensagem);
    }
}
