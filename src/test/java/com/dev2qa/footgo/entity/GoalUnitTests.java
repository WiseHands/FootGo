package com.dev2qa.footgo.entity;

import com.dev2qa.footgo.repository.GoalRepository;
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
public class GoalUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GoalRepository goalRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		// given
		Goal goal = new Goal();
		goal.setTime(180);
		entityManager.persist(goal);
		entityManager.flush();

		// when
		Goal foundGoal = goalRepository.findByTime(goal.getTime());

		// then
		assertThat(foundGoal.getTime())
				.isEqualTo(goal.getTime());

		
	}

	@After
	public void cleanUp() {
		goalRepository.deleteAll();
	}
	
}
