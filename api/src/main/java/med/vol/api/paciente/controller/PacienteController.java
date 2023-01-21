package med.vol.api.paciente.controller;

import jakarta.validation.Valid;
import med.vol.api.medico.DTO.DadosListaMedicosDTO;
import med.vol.api.paciente.DTO.DadosAtualizacaoPacienteDTO;
import med.vol.api.paciente.DTO.DadosCadastroPacienteDTO;
import med.vol.api.paciente.DTO.DadosListaPacienteDTO;
import med.vol.api.paciente.Paciente;
import med.vol.api.repository.PacienteRepository;
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
@RequestMapping("pacientes")
public class PacienteController {


    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar( @RequestBody @Valid DadosCadastroPacienteDTO dados){

        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListaPacienteDTO> listar(@PageableDefault(size = 5, sort = {"name"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListaPacienteDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPacienteDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }





}
