package med.vol.api.medico.controller;

import jakarta.validation.Valid;
import med.vol.api.medico.DTO.DadosAtualizacaoMedicoDTO;
import med.vol.api.medico.DTO.DadosCadastroMedicoDTO;
import med.vol.api.medico.DTO.DadosListaMedicosDTO;
import med.vol.api.medico.Medico;
import med.vol.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar( @RequestBody @Valid DadosCadastroMedicoDTO dados){

        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListaMedicosDTO> listar(@PageableDefault(size = 5, sort = {"name"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListaMedicosDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }




}
