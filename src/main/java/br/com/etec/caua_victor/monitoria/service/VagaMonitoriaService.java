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

    @Transactional
    public VagaMonitoria inscreverAluno(Long monitoriaId) {


        VagaMonitoria monitoria = repository.findById(monitoriaId).orElseThrow(() -> new RuntimeException("Monitoria não encontrada."));


        if (!monitoria.getAberta() || monitoria.getVagasOcupadas() >= monitoria.getVagasTotais()) {

            throw new IllegalStateException("Inscrições encerradas para esta monitoria.");
        }


        monitoria.setVagasOcupadas(monitoria.getVagasOcupadas() + 1);


        if (monitoria.getVagasOcupadas().equals(monitoria.getVagasTotais())) {
            // monitoria.aberta = Falso
            monitoria.setAberta(false);
        }


        return repository.save(monitoria);
    }
}
