package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {
	EntityManager em = Persistence.createEntityManagerFactory("TestEntity").createEntityManager();
	
	
}
