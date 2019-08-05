package com.dev2qa.footgo.entity;

import com.dev2qa.footgo.repository.CaptainRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GaptainUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CaptainRepository captainRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		// given
		Captain captain = new Captain();
		captain.setCaptainName("Toni");
		entityManager.persist(captain);
		entityManager.flush();

		// when
		Captain captainFromDataStore = captainRepository.findByCaptainName(captain.getCaptainName());

		// then
		assertThat(captainFromDataStore.getCaptainName())
				.isEqualTo(captain.getCaptainName());

		
	}

	@After
	public void cleanUp() {
		captainRepository.deleteAll();
	}
	
}