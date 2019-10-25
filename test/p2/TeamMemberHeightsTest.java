package p2;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p2.TeamMemberHeights;

public class TeamMemberHeightsTest {
	
	private TeamMemberHeights instance;

	@Before
	public void setUp() throws Exception {
		instance = new TeamMemberHeights(5);
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test (timeout = 2000)
	public void constructor_TeamMemberHeights() {
		assertEquals("Array length is not correct", 5, instance.heights.length);
		
		for(int visina: instance.heights)
			assertEquals("Array element is not initialized to 0", 0, visina);

	}

	@Test (timeout = 2000)
	public void constructor_TeamMemberHeights_NegativeParameter() {
		instance = new TeamMemberHeights(-10);
		
		assertEquals("If argument value is 20, the array length is not 20", 20, instance.heights.length);
		
		for(int visina: instance.heights)
			assertEquals("Array element is not initialized to 0", 0, visina);
	}
	
	@Test (timeout = 2000)
	public void method_insert() {
		instance.insert(180);
		instance.insert(169);
		
		assertEquals("If the method is called two times, with the arguments 180 and 169, counter nije 2", 2, instance.counter);
		assertEquals("If the method is called two times, with the arguments 180 and 169, the number 180 is not stored as the first element", 180, instance.heights[0]);
		assertEquals("If the method is called two times, with the arguments 180 and 169, the number 169 is not stored as the second element", 169, instance.heights[1]);
		assertEquals("If the method is called two times, with the arguments 180 and 169, the third element should be -1", 0, instance.heights[2]);
		assertEquals("If the method is called two times, with the arguments 180 and 169, the fourth element should be -1", 0, instance.heights[3]);
		assertEquals("If the method is called two times, with the arguments 180 and 169, the fifth element should be -1", 0, instance.heights[4]);
	}
	
	@Test(timeout = 2000)
	public void method_insert_tooShort() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.insert(159);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String output = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("For the argument 159, which is bellow the threshold, the method does not output the text 'ERROR'", output.toLowerCase().contains("ERROR".toLowerCase()));
			assertEquals("For the argument 159, the counter is increased, although the value is bellow the threshold", 0, instance.counter);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void method_insert_tooTall() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.insert(251);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String output = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("For the argument 251, which is above the threshold, the method does not output the text 'ERROR'", output.toLowerCase().contains("ERROR".toLowerCase()));
			assertEquals("For the argument 251, the counter is increased, although the value is above the threshold", 0, instance.counter);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void method_insert_arrayFull() {
		instance.insert(180);
		instance.insert(190);
		instance.insert(210);
		instance.insert(182);
		instance.insert(195);

		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.insert(199);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String output = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("When the array is full and a new element is added, the method should print ERROR to the console", output.toLowerCase().contains("ERROR".toLowerCase()));
			assertEquals("When the array is full and a new element is added, the counter is increased, but should not have been", 5, instance.counter);
			assertNotEquals("When the array is full and a new element is added, it is added as the last element, but should not have been", 199, instance.heights[4]);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test (timeout = 2000)
	public void method_getMeanHeight() {
		instance.insert(180);
		instance.insert(169);
		instance.insert(190);
		
		assertEquals("When three elements are added to the array, 180, 169 and 190, the average should be 179", 179, instance.getMeanHeight());
	}

	@Test (timeout = 2000)
	public void method_getMaxDifference() {
		instance.insert(180);
		instance.insert(169);
		instance.insert(190);
		instance.insert(160);

		assertEquals("When three elements are added to the array, 180, 169, 190 and 160, the difference between the highest (190) and the lowest (160) height should be 30 cm", 30, instance.getMaxDifference());
	}

}
