package ua.lviv.footgo.entity;

import ua.lviv.footgo.repository.GoalRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoalUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GoalRepository goalRepository;

	@Test
	public void createGoal() {
		// create goal
		Goal goal = new Goal();
		goal.setTime(180);
		goalRepository.save(goal);

		// when
		Goal foundGoal = goalRepository.findByTime(goal.getTime());

		// then
		assertThat(foundGoal.getTime())
				.isEqualTo(goal.getTime());

		
	}

	@Test
	public void removeGoal() {
		// create goal
		Goal goal = new Goal();
		goal.setTime(180);
		goalRepository.save(goal);

		// get goal from DB and check it
		Optional<Goal> goalFromDB = goalRepository.findById(goal.getId());
		assertThat(goalFromDB.isPresent()).isTrue();

		goalRepository.deleteById(goalFromDB.get().getId());

		goalFromDB = goalRepository.findById(goal.getId());
		assertThat(goalFromDB.isPresent()).isFalse();


	}

	@After
	public void cleanUp() {
		goalRepository.deleteAll();
	}
	
}
