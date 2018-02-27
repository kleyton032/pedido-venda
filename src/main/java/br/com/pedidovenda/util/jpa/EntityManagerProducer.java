package br.com.pedidovenda.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//criando a fábrica de conexão com EntityManger
@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	//factory do entitymanger
	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("PedidoPU");
	}
	
	//produtor de requesição do entity manager
	@Produces @RequestScoped
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}
	
	//fechando o entity manger após a requesição 
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
	
}