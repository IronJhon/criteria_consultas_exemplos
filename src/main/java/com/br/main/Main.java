package com.br.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.br.main.model.Usuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CONFIG-DATABASE-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
				
		//consultandoUsuarios(entityManager);
		escolhendoRetorno(entityManager);
		
		entityManager.close();
		entityManagerFactory.close();
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
		resultList.forEach(u -> System.out.println(u.getNome() + "||| "+ u.getLogin()));
		
				
	}




}
