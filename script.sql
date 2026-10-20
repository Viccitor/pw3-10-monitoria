create database monitoria;
GO
use monitoria;
GO

create table VagaMonitoria(
	id int identity primary key,
	disciplina varchar(20) not null,
	nomeMonitor varchar(50) not null,
	vagasTotais int not null,
	vagasOcupadas int not null,
	abertas bit not null
);
GO