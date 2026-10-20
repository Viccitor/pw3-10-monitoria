package br.com.etec.caua_victor.monitoria.controller;

import br.com.etec.caua_victor.monitoria.entity.VagaMonitoria;
import br.com.etec.caua_victor.monitoria.repository.VagaMonitoriaRepository;
import br.com.etec.caua_victor.monitoria.service.VagaMonitoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VagaMonitoriaController {

        @Autowired
        private VagaMonitoriaRepository repository;

        @Autowired
        private VagaMonitoriaService service;

        @GetMapping
        public List<VagaMonitoria> listar() {
            return repository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<VagaMonitoria> buscarPorId(@PathVariable Long id) {
            return repository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public VagaMonitoria criar(@RequestBody VagaMonitoria monitoria) {
            return repository.save(monitoria);
        }

        @PutMapping("/{id}")
        public ResponseEntity<VagaMonitoria> editar(@PathVariable Long id, @RequestBody VagaMonitoria novosDados) {
            return repository.findById(id).map(monitoria -> {
                monitoria.setDisciplina(novosDados.getDisciplina());
                monitoria.setNomeMonitor(novosDados.getNomeMonitor());
                monitoria.setVagasTotais(novosDados.getVagasTotais());
                monitoria.setVagasOcupadas(novosDados.getVagasOcupadas());
                monitoria.setAberta(novosDados.getAberta());
                return ResponseEntity.ok(repository.save(monitoria));
            }).orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/{id}/inscrever")
        public ResponseEntity<?> inscrever(@PathVariable Long id) {
            try {
                VagaMonitoria atualizada = service.inscreverAluno(id);
                return ResponseEntity.ok(atualizada);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }
