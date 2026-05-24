import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ==========================================
// 1. INTERFACE MEDIATOR
// ==========================================
interface HospitalMediator {
    void registrarMedico(Medico medico);
    void registrarPaciente(Paciente paciente);
    void solicitarExame(String medicoNome, String pacienteNome, String exame);
    void exibirHistoricoExames();
}

// ==========================================
// 2. MEDIATOR CONCRETO
// Centraliza a comunicação e o registro de exames
// ==========================================
class CentralHospitalar implements HospitalMediator {
    private Map<String, Medico> medicos = new HashMap<>();
    private Map<String, Paciente> pacientes = new HashMap<>();
    private List<String> registroDeTestes = new ArrayList<>();

    @Override
    public void registrarMedico(Medico medico) {
        medicos.put(medico.getNome(), medico);
    }

    @Override
    public void registrarPaciente(Paciente paciente) {
        pacientes.put(paciente.getNome(), paciente);
    }

    @Override
    public void solicitarExame(String medicoNome, String pacienteNome, String exame) {
        Paciente paciente = pacientes.get(pacienteNome);
        
        if (paciente != null) {
            String log = "Exame [" + exame + "] solicitado pelo(a) Dr(a). " + medicoNome + " para o(a) paciente " + pacienteNome + ".";
            registroDeTestes.add(log);
            
            // O Mediator avisa o paciente sobre o exame solicitado
            paciente.receberNotificacaoExame(medicoNome, exame);
        } else {
            System.out.println("⚠️ Erro: Paciente '" + pacienteNome + "' não encontrado no sistema.");
        }
    }

    @Override
    public void exibirHistoricoExames() {
        System.out.println("\n--- 📋 HISTÓRICO DE TESTES REGISTRADOS ---");
        if (registroDeTestes.isEmpty()) {
            System.out.println("Nenhum exame registrado até o momento.");
        } else {
            for (String registro : registroDeTestes) {
                System.out.println(registro);
            }
        }
        System.out.println("-----------------------------------------\n");
    }
}

// ==========================================
// 3. CLASSE ABSTRATA COLLEAGUE (Participante)
// ==========================================
abstract class Participante {
    protected HospitalMediator mediator;
    protected String nome;

    public Participante(HospitalMediator mediator, String nome) {
        this.mediator = mediator;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

// ==========================================
// 4. COLLEAGUES CONCRETOS
// ==========================================
class Medico extends Participante {
    
    public Medico(HospitalMediator mediator, String nome) {
        super(mediator, nome);
    }

    public void prescreverExame(String pacienteNome, String exame) {
        System.out.println("⚕️ Dr(a). " + nome + " solicitou o exame '" + exame + "' para " + pacienteNome + ".");
        // O médico não fala com o paciente direto, ele pede ao Mediator
        mediator.solicitarExame(this.nome, pacienteNome, examen);
    }
}

class Paciente extends Participante {

    public Paciente(HospitalMediator mediator, String nome) {
        super(mediator, nome);
    }

    public void receberNotificacaoExame(String medicoNome, String exame) {
        // O paciente reage ao comando vindo do Mediator
        System.out.println("🔔 [Notificação] Paciente " + nome + ": Recebi o pedido de " + exame + " feito pelo(a) Dr(a). " + medicoNome + ".");
    }
}

// ==========================================
// 5. CLASSE DE TESTE (Execução)
// ==========================================
public class Main {
    public static void main(String[] args) {
        // Criamos o mediador (a central do hospital)
        HospitalMediator central = new CentralHospitalar();

        // Criamos os médicos e pacientes passando o mediador no construtor
        Medico drHouse = new Medico(central, "Gregory House");
        Medico drGrey = new Medico(central, "Meredith Grey");

        Paciente paciente1 = new Paciente(central, "Fulano da Silva");
        Paciente paciente2 = new Paciente(central, "Ciclana de Souza");

        // Registramos todos na central
        central.registrarMedico(drHouse);
        central.registrarMedico(drGrey);
        central.registrarPaciente(paciente1);
        central.registrarPaciente(paciente2);

        // Fluxo de interações
        System.out.println("--- Início dos Atendimentos ---\n");
        
        drHouse.prescreverExame("Fulano da Silva", "Ressonância Magnética");
        System.out.println();
        
        drGrey.prescreverExame("Ciclana de Souza", "Exame de Sangue Completo");
        System.out.println();
        
        // Tentativa de passar um paciente não registrado
        drHouse.prescreverExame("John Doe", "Raio-X");

        // Exibindo o relatório que o Mediator guardou
        central.exibirHistoricoExames();
    }
}
