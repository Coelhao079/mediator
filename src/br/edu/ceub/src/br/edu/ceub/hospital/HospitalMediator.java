package br.edu.ceub.hospital;

public interface HospitalMediator {
    /**
     * Registra um exame solicitado por um médico para um paciente específico.
     * * @param exame Nome do teste/exame solicitado.
     * @param remetente O usuário hospitalar que solicitou (Médico).
     * @param nomePaciente O nome do paciente que receberá o exame.
     */
    void registrarExame(String exame, UsuarioHospitalar remetente, String nomePaciente);

    /**
     * Adiciona um usuário (Médico ou Paciente) ao sistema do hospital.
     * * @param usuario Usuário a ser cadastrado na base de dados ativa.
     */
    void adicionarUsuario(UsuarioHospitalar usuario);
}
