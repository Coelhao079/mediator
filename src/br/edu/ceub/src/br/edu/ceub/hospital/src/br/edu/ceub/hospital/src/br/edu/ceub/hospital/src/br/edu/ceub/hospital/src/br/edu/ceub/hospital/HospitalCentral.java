package br.edu.ceub.hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalCentral implements HospitalMediator {
    private final List<UsuarioHospitalar> usuarios;

    public HospitalCentral() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void adicionarUsuario(UsuarioHospitalar usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public void registrarExame(String exame, UsuarioHospitalar remetente, String nomePaciente) {
        boolean pacienteEncontrado = false;

        // O mediador busca o paciente correto na lista para registrar o teste
        for (UsuarioHospitalar u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nomePaciente) && u instanceof Paciente) {
                u.receberNotificacao("Exame [" + exame + "] cadastrado com sucesso. Solicitante: Dr(a). " + remetente.getNome());
                pacienteEncontrado = true;
                break;
            }
        }

        if (!pacienteEncontrado) {
            // Caso o paciente não esteja no sistema, o mediador avisa o médico de volta
            remetente.receberNotificacao("Erro ao registrar exame. Paciente '" + nomePaciente + "' não encontrado no sistema.");
        }
    }
}
