package br.com.etec.caua_victor.monitoria.service;

import br.com.etec.caua_victor.monitoria.entity.VagaMonitoria;
import br.com.etec.caua_victor.monitoria.repository.VagaMonitoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VagaMonitoriaService {

    @Autowired
    private VagaMonitoriaRepository repository;

    public VagaMonitoria inscreverAluno(Long monitoriaId) {

        // monitoria = repository.buscarPorId(monitoriaId)
        VagaMonitoria monitoria = repository.findById(monitoriaId).orElseThrow(() -> new RuntimeException("Monitoria não encontrada."));

        // Se Não monitoria.aberta Ou monitoria.vagasOcupadas >= monitoria.vagasTotais Então
        if (!monitoria.getAberta() || monitoria.getVagasOcupadas() >= monitoria.getVagasTotais()) {
            // LançarErro("Inscrições encerradas para esta monitoria.")
            throw new IllegalStateException("Inscrições encerradas para esta monitoria.");
        }

        // monitoria.vagasOcupadas = monitoria.vagasOcupadas + 1
        monitoria.setVagasOcupadas(monitoria.getVagasOcupadas() + 1);

        // Se monitoria.vagasOcupadas == monitoria.vagasTotais Então
        if (monitoria.getVagasOcupadas().equals(monitoria.getVagasTotais())) {
            // monitoria.aberta = Falso
            monitoria.setAberta(false);
        }

        // Retornar repository.salvar(monitoria)
        return repository.save(monitoria);
    }
}
