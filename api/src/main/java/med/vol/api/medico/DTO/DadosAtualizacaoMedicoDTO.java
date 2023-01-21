package med.vol.api.medico.DTO;

import jakarta.validation.constraints.NotNull;
import med.vol.api.endereco.DTO.DadosEnderecoDTO;

public record DadosAtualizacaoMedicoDTO(
        @NotNull
        Long id,
        String telefone,
        String email,
        String nome,
        DadosEnderecoDTO endereco) {
}
