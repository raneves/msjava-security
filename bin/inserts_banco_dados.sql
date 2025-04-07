INSERT INTO cursos(categoria, nome) values('PROGRAMACAO','Java e OO');



//senha maria123
INSERT INTO usuarios(email, senha, nome_completo, nome_usuario) VALUES ('maria@email.com', '$2y$10$PuVtb//6d///JSQXlVSQou5z34LQj88jjmsFEA3qnmV9lDSZf6lxm', 'Maria', 'maria');


INSERT INTO usuarios(email, senha, nome_completo, nome_usuario, verificado,ativo) VALUES ('rodrigo@email.com', '$2y$10$/8EepTsy2reD5vKLPGn.gOXfFs5b6jjoaaWx/79kvdAfu0U0f4vmS', 'Rodrigo', "rodrigo", 1, 1); 

INSERT INTO usuarios(email, senha, nome_completo, nome_usuario, verificado,ativo) VALUES ('joao@email.com', '$2y$10$/8EepTsy2reD5vKLPGn.gOXfFs5b6jjoaaWx/79kvdAfu0U0f4vmS', 'João', "jao", 1, 1);           

INSERT INTO usuarios(email, senha, nome_completo, nome_usuario, verificado,ativo) VALUES ('ana@email.com', '$2y$10$/8EepTsy2reD5vKLPGn.gOXfFs5b6jjoaaWx/79kvdAfu0U0f4vmS', 'Ana', "ana", 1, 1);

INSERT INTO usuarios_perfis(usuario_id, perfil_id) VALUES(1,2);

INSERT INTO usuarios_perfis(usuario_id, perfil_id) VALUES(9,1);

INSERT INTO usuarios_perfis(usuario_id, perfil_id) VALUES(10,3);


INSERT INTO usuarios_perfis(usuario_id, perfil_id) VALUES(11,4);




//Configuramos 3 novos usuários: Rodrigo, João e Ana, todos com a senha 12345678. Assim, temos a seguinte configuração de usuários e perfis:

//Maria -> instrutora

//Iasmin -> instrutora, estudante

//Rodrigo -> estudante

//João -> moderador

//Ana -> admin