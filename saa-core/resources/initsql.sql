--FUNCIONALIDADES
insert into funcionalidades values (1, 'AULA'), (2, 'AVALIAR'), (3, 'ACOMPANHAR');

--PERMISSOES DE ATENDIMENTO
insert into permissoes values (1, 'Cadastrar Aula', 'CADASTRAR_AULA', 1), (2, 'Atualizar Aula', 'ATUALIZAR_AULA', 1),
	(3, 'Excluir Aula', 'EXCLUIR_AULA', 1);
	
--PAPEIS
insert into papeis values (1, 0), (2,1);

--CONFIGURACOES DE ATENDENTE
insert into configuracoes values (1, 1), (1, 2), (1, 3);

--USUARIO ADM
insert into usuarios values (1, true, 'admin', false, '3C9909AFEC25354D551DAE21590BB26E38D53F2173B8D3DC3EEE4C047E7AB1C1EB8B85103E3BE7BA613B31BB5C9C36214DC9F14A42FD7A2FDB84856BCA5C44C2');

--PERFIS DE ADM
insert into perfis values (1, 1), (1,2);