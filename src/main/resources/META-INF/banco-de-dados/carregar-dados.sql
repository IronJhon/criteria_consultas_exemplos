insert into dominio (id, nome) values (1, 'Banco de Dados');
insert into dominio (id, nome) values (2, 'LDAP');

insert into usuario (id, nome, login, cpf, dominio_id, ultimoAcesso) values (1, 'João Pedro', 'cal', '09104537488', 1, sysdate());
insert into usuario (id, nome, login, cpf, dominio_id, ultimoAcesso) values (2, 'Gillian Foster', 'gillian', '09104537483', 1, sysdate());
insert into usuario (id, nome, login, cpf, dominio_id, ultimoAcesso) values (3, 'Ria Torres', 'ria', '09104537789', 1, sysdate());
insert into usuario (id, nome, login, cpf, dominio_id, ultimoAcesso) values (4, 'Eli Locker', 'eli', '09104789789', 1, sysdate());
insert into usuario (id, nome, login, cpf, dominio_id, ultimoAcesso) values (5, 'Emily Lightman', 'emily', '09104788888', 1, sysdate());

insert into configuracao (usuario_id, receberNotificacoes, encerrarSessaoAutomaticamente) values (1, false, false);