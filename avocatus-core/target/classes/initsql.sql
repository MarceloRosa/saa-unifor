--FUNCIONALIDADES
insert into funcionalidades values (1, 'ATENDIMENTO'), (2, 'ACOMPANHAMENTO'), (3, 'AGENDA DE AUDIENCIAS'), (4, 'FUNCIONARIOS');

--PERMISSOES DE ATENDIMENTO
insert into permissoes values (1, 'Dashboard do Atendente', 'ATENDIMENTO_DASHBOARD', 1), (2, 'Listagem de Atendimentos', 'ATENDIMENTO_LISTAGEM', 1),
	(3, 'Cadastro de Atendimentos', 'ATENDIMENTO_CADASTRO', 1), (4, 'Altera Atendimento', 'ATENDIMENTO_ATUALIZA', 1), (5, 'Excluir Atendimento', 'ATENDIMENTO_EXCLUIR', 1);
	
--PAPEIS
insert into papeis values (1, 6), (2,0), (3, 1), (4, 2), (5, 3), (6, 4), (7, 5);

--CONFIGURACOES DE ATENDENTE
insert into configuracoes values (4, 1), (4, 2), (4, 3), (4, 4), (4, 5);

--USUARIO ADM
insert into usuarios values (1, true, 'admin', false, '3C9909AFEC25354D551DAE21590BB26E38D53F2173B8D3DC3EEE4C047E7AB1C1EB8B85103E3BE7BA613B31BB5C9C36214DC9F14A42FD7A2FDB84856BCA5C44C2');

--PERFIS DE ADM
insert into perfis values (1, 1);