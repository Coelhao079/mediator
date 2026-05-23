package br.edu.ceub.hospital;

public abstract class UsuarioHospitalar {
    protected HospitalMediator mediador;
    protected String nome;

    public UsuarioHospitalar(HospitalMediator mediador, String nome) {
        this.mediador = mediador;
        this.nome = nome;
    }

    /**
     * Método abstrato que define como o usuário recebe alertas ou registros do mediador.
     */
    public abstract void receberNotificacao(String mensagem);

    public String getNome() {
        return nome;
    }
}
