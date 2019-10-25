package p1;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p1.TeamMemberWeights;

public class TeamMemberWeightsTest {
	
	private TeamMemberWeights instance;

	@Before
	public void setUp() throws Exception {
		instance = new TeamMemberWeights(5);
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test(timeout = 2000)
	public void constructor_TeamMemberWeights() {
		instance = new TeamMemberWeights();
		
		assertEquals("Array length is not correct", 20, instance.weights.length);
		
		for (double tezina : instance.weights)
			assertEquals("Array element is not initialized to -1", -1, tezina, 0.001);
	}

	@Test(timeout = 2000)
	public void constructor_TeamMemberWeights_int() {
		instance = new TeamMemberWeights(5);
		
		assertEquals("If argument value is 5, the array length is not 5", 5, instance.weights.length);
		
		for (double tezina : instance.weights)
			assertEquals("Array element is not initialized to -1", -1, tezina, 0.001);
	}

	@Test(timeout = 2000)
	public void method_insert() {
		instance.insert(100.5);
		instance.insert(52.3);
		
		assertEquals("If the method is called two times, with the arguments 100.5 and 52.3, the counter is not 2", 2, instance.counter);
		assertEquals("If the method is called two times, with the arguments 100.5 and 52.3, the number 100.5 is not stored as a first element", 100.5, instance.weights[0], 0.001);
		assertEquals("If the method is called two times, with the arguments 100.5 and 52.3, the number 52.3 is not stored as a second element", 52.3, instance.weights[1], 0.001);
		assertEquals("If the method is called two times, with the arguments 100.5 and 52.3, the third element should be -1", -1, instance.weights[2], 0.001);
		assertEquals("If the method is called two times, with the arguments 100.5 and 52.3, the fourth element should be -1", -1, instance.weights[3], 0.001);
		assertEquals("If the method is called two times, with the arguments 100.5 and 52.3, the fifth element should be -1", -1, instance.weights[4], 0.001);
	}
	
	@Test(timeout = 2000)
	public void method_insert_lower() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.insert(40.0);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String output = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("For the argument 40.0, which is bellow the threshold, the method does not output the text 'ERROR'", output.toLowerCase().contains("ERROR".toLowerCase()));
			assertEquals("For the argument 40.0, the counter is increased, although the value is bellow the threshold", 0, instance.counter);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void method_insert_arrayFull() {
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(110.5);

		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.insert(99.9);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String output = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("When the array is full and a new element is added, the method should print ERROR to the console", output.toLowerCase().contains("ERROR".toLowerCase()));
			assertEquals("When the array is full and a new element is added, the counter is increased, but should not have been", 5, instance.counter);
			assertNotEquals("When the array is full and a new element is added, it is added as the last element, but should not have been", 99.9, instance.weights[4], 0.001);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void method_print() {
		instance.insert(110.5);
		instance.insert(82.3);

		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.print();

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			String s = "110.5" + System.lineSeparator() + "82.3" + System.lineSeparator();

			assertEquals("When two elements are added to the array, 110.5 and 82.3 respectivelly, those elements are not printed to the console", s.trim(), ispis.trim());
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void method_findLightest() {
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(50.0);
		instance.insert(182.3);
		
		assertEquals("After four elements are added to the array, 110.5, 82.3, 50.0, 182.3, the method does not return the value 50.0 as minimum",50.0, instance.findLightest(), 0.001);
	}
	
	@Test(timeout = 2000)
	public void method_findWeight_true() {
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(50.0);
		instance.insert(182.3);
		
		assertEquals("After four elements are added to the array, 110.5, 82.3, 50.0, 182.3, the method cannot find the element 50.0 in the array",true, instance.findWeight(50.0));
	}
	
	@Test(timeout = 2000)
	public void method_findWeight_false() {
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(50.0);
		instance.insert(182.3);
		
		assertEquals("After four elements are added to the array, the method finds an element 112.3 which is not present in the array",false, instance.findWeight(112.3));
	}

	@Test(timeout = 2000)
	public void method_printCategories() {
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(50.0);
		instance.insert(182.3);
		
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.printCategories();

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			String s = "Lightweight:1" + System.lineSeparator() + "Middleweight:1" + System.lineSeparator() + "Heavyweight:2" + System.lineSeparator();

			assertEquals("After two elements are added to the array, 110.5 and 82.3, those elements are not printed to the output", s.trim(), ispis.trim());
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

}
