package med.vol.api.medico.DTO;

import med.vol.api.medico.Especialidade;
import med.vol.api.medico.Medico;

public record DadosListaMedicosDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListaMedicosDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
