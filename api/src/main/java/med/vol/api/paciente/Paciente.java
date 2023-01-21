package med.vol.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.vol.api.endereco.Endereco;
import med.vol.api.paciente.DTO.DadosAtualizacaoPacienteDTO;
import med.vol.api.paciente.DTO.DadosCadastroPacienteDTO;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String RG_CPF;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;


    public Paciente(DadosCadastroPacienteDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.RG_CPF = dados.RG_CPF();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPacienteDTO dados) {

        if (dados.nome() != null){
            this.nome = dados.nome();
        }

        if (dados.email() != null){
            this.email = dados.email();
        }

        if (dados.telefone() != null){
            this.nome = dados.telefone();
        }

        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
