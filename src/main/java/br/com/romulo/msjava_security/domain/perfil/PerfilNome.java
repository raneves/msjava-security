package br.com.romulo.msjava_security.domain.perfil;

public enum PerfilNome {
	//visitante nao precisa de perfil
	ESTUDANTE, //Visitante, cria tópicos e respostas, marca seus tópicos como solução e edita e exclui seus posts.
    INSTRUTOR, //Visitante, responde tópicos, edita e exclui suas respostas, marca qualquer tópico como solução.
    MODERADOR, //Instrutor, estudante, fechar tópicos, editar e excluir posts de outras pessoas usuárias.
    ADMIN //Moderador, banir uma pessoa usuária ou reativar sua conta
}
