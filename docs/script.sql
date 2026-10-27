create database monitoria;
GO
use monitoria;
GO

create table vagas_monitoria(
	id bigint identity primary key,
	disciplina varchar(50) not null,
	nomeMonitor varchar(50) not null,
	vagasTotais int not null,
	vagasOcupadas int not null,
	aberta bit not null
);
GO