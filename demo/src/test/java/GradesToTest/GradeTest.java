package GradesToTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GradeTest {
	
	@Test
	void fiftyNineShouldBeF() {
		Grade grader = new Grade();
		assertEquals('F', grader.determineLetterGrade(59));
	}
	@Test
	void sixyNineShouldReturnD() {
		Grade grader = new Grade();
		assertEquals('D', grader.determineLetterGrade(69));
	}
	@Test
	void seventyNineShouldReturnC() {
		Grade grader = new Grade();
		assertEquals('C', grader.determineLetterGrade(79));
	}
	@Test
	void eightyNineShouldReturnB() {
		Grade grader = new Grade();
		assertEquals('B', grader.determineLetterGrade(89));
	}
	@Test
	void ninetyOneShouldReturnA() {
		Grade grader = new Grade();
		assertEquals('A', grader.determineLetterGrade(91));
	}
	@Test
	void ZeroShouldreturnF() {
		Grade grader = new Grade();
		assertEquals('F', grader.determineLetterGrade(50));
	}
	@Test
	void sixtyShouldReturnD() {
		Grade grader = new Grade();
		assertEquals('D', grader.determineLetterGrade(60));
	}
	@Test
	void seventyShouldReturnC() {
		Grade grader = new Grade();
		assertEquals('C', grader.determineLetterGrade(70));
	}
	@Test
	void eightyShouldReturnB() {
		Grade grader = new Grade();
		assertEquals('B', grader.determineLetterGrade(80));
	}
	@Test
	void ninetyShouldReturnA() {
		Grade grader = new Grade();
		assertEquals('A', grader.determineLetterGrade(90));
	}
	 @Test 
	 void negativeOneShouldReturnIlligalArgumentException() { 
		 Grade grader = new Grade();
		 assertThrows(IllegalArgumentException.class, 
				 () -> {
					 grader.determineLetterGrade(-1);
				 });
	 }


}
