package med.vol.api.paciente.DTO;

import med.vol.api.medico.Especialidade;
import med.vol.api.paciente.Paciente;

public record DadosListaPacienteDTO(Long id, String nome, String email, String RG_CPF, Especialidade especialidade) {

    public DadosListaPacienteDTO(Paciente paciente){
        this(paciente.getId());
    }
}
