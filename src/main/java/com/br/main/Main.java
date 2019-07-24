package com.br.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.br.main.dto.UsuarioDTO;
import com.br.main.model.Usuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CONFIG-DATABASE-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// consultandoUsuarios(entityManager);
		// escolhendoRetorno(entityManager);
		// retornandoProjecoes(entityManager);
		// findByCpf("09104537488", entityManager);
		//orderBy("cpf", entityManager); // Parameters type String: nome, login and cpf
		pagination(0, 9, entityManager); // Parameters type int: firstResult and maxResult

		entityManager.close();
		entityManagerFactory.close();
	}

	private static void pagination(int firstResult, int maxResult, EntityManager entityManager) {
		// TODO Auto-generated method stub
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);

		query.select(root);
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(query)
				.setFirstResult(firstResult) // first = (firstResult - 1) * maxResult;
				.setMaxResults(maxResult);    
		
		List<Usuario> resultList = typedQuery.getResultList();
		resultList.forEach(u -> System.out.println(u.getNome() + "||| " + u.getLogin()));
	}

	private static void orderBy(String ordeBy, EntityManager entityManager) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);
		query.orderBy(builder.asc(root.get(ordeBy))); 

		query.select(root);
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(query);
		List<Usuario> resultList = typedQuery.getResultList();
		resultList.forEach(u -> System.out.println(u.getNome() + "||| " + u.getLogin()));

	}

	private static void findByCpf(String cpf, EntityManager entityManager) {
		// TODO Auto-generated method stub

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root);
		criteriaQuery.where(builder.equal(root.get("cpf"), cpf));

		TypedQuery<Usuario> typedQuery = entityManager.createQuery(criteriaQuery);
		Usuario user = typedQuery.getSingleResult();

		System.out.println(user.getNome());

	}

	private static void retornandoProjecoes(EntityManager entityManager) {
		// TODO Auto-generated method stub

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		///////////////// EXEMPLO 1/////////////////////////////////////
		/*
		 * CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		 * Root<Usuario> root = criteriaQuery.from(Usuario.class);
		 * criteriaQuery.multiselect(root.get("id"), root.get("login"),
		 * root.get("nome"));
		 * 
		 * TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		 * List<Object[]> list = typedQuery.getResultList(); list.forEach(dados ->
		 * System.out.println(String.format("%s, %s, %s", dados)));
		 */

		///////////////// EXEMPLO 2/////////////////////////////////////
		CriteriaQuery<UsuarioDTO> criteriaQuery = builder.createQuery(UsuarioDTO.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(builder.construct(UsuarioDTO.class, root.get("id"), root.get("login"), root.get("nome")));

		TypedQuery<UsuarioDTO> typedQuery = entityManager.createQuery(criteriaQuery);
		List<UsuarioDTO> list = typedQuery.getResultList();

		list.forEach(d -> System.out.println(d.getId() + "|<>|<>| " + d.getNome() + "|<>|<>| " + d.getLogin()));

	}

	private static void escolhendoRetorno(EntityManager entityManager) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

//      CriteriaQuery<Dominio> criteriaQuery = criteriaBuilder.createQuery(Dominio.class);
//      Root<Usuario> root = criteriaQuery.from(Usuario.class);
//
//      criteriaQuery.select(root.get("dominio"));
//
//      TypedQuery<Dominio> typedQuery = entityManager.createQuery(criteriaQuery);
//      List<Dominio> lista = typedQuery.getResultList();
//      lista.forEach(d -> System.out.println(d.getId() + ", " + d.getNome()));

		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);

		criteriaQuery.select(root.get("nome"));

		TypedQuery<String> typedQuery = entityManager.createQuery(criteriaQuery);
		List<String> lista = typedQuery.getResultList();
		lista.forEach(nome -> System.out.println("Nome: " + nome));

	}

	private static void consultandoUsuarios(EntityManager entityManager) {
		// TODO Auto-generated method stub

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);

		query.select(root);
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(query);
		List<Usuario> resultList = typedQuery.getResultList();
		resultList.forEach(u -> System.out.println(u.getNome() + "||| " + u.getLogin()));

	}

}
