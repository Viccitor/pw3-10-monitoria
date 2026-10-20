package br.com.etec.caua_victor.monitoria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vagas_monitoria")
public class VagaMonitoria {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String disciplina;
        private String nomeMonitor;
        private Integer vagasTotais;
        private Integer vagasOcupadas;
        private Boolean aberta;

        public void inscreverAluno(){
            if(!this.aberta || this.vagasOcupadas >= this.vagasTotais){
                throw new RuntimeException("inscrições encerradas para essa monitoria");
            }

            this.vagasOcupadas++;

            if(this.vagasOcupadas == this.vagasTotais){
                this.aberta = false;
            }

        }



    }

