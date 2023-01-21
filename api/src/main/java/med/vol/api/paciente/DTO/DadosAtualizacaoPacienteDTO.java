package med.vol.api.paciente.DTO;

import jakarta.validation.constraints.NotNull;
import med.vol.api.endereco.DTO.DadosEnderecoDTO;

public record DadosAtualizacaoPacienteDTO(

        @NotNull
        Long id,
        String telefone,
        String email,
        String nome,
        DadosEnderecoDTO endereco
) {
}
