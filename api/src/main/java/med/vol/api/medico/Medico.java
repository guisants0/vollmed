package med.vol.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.endereco.Endereco;
import med.vol.api.medico.DTO.DadosAtualizacaoMedicoDTO;
import med.vol.api.medico.DTO.DadosCadastroMedicoDTO;


@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedicoDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedicoDTO dados) {

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





