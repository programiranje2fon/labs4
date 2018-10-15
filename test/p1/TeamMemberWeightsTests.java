package p1;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p1.TeamMemberWeights;

public class TeamMemberWeightsTests {
	
	TeamMemberWeights instance;

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
		
		assertEquals("Kapacitet niza nije 20", 20, instance.weights.length);
		
		for(double tezina: instance.weights)
			assertEquals("Element niza nije inicijalizovan na -1", -1, tezina, 0.001);
	}

	@Test(timeout = 2000)
	public void constructor_TeamMemberWeightsInt() {
		instance = new TeamMemberWeights(5);
		
		assertEquals("Ako se unese kapacitet 5, kapacitet niza nije 5", 5, instance.weights.length);
		
		for(double tezina: instance.weights)
			assertEquals("Element niza nije inicijalizovan na -1", -1, tezina, 0.001);
	}

	@Test(timeout = 2000)
	public void method_insert() {
		instance.insert(100.5);
		instance.insert(52.3);
		
		assertEquals("Ako se unesu dva elementa, metoda u niz ne ubaci oba kako treba, counter nije 2", 2, instance.counter);
		assertEquals("Unet je prvi element 100.5, ali nije postavljen na prvo mesto u nizu", 100.5, instance.weights[0], 0.001);
		assertEquals("Unet je drugi element 52.3, ali nije postavljen na drugo mesto u nizu", 52.3, instance.weights[1], 0.001);
		assertEquals("Unet je i treci element, a trebalo je da ostane -1", -1, instance.weights[2], 0.001);
		assertEquals("Unet je i cetvrti element, a trebalo je da ostane -1", -1, instance.weights[3], 0.001);
		assertEquals("Unet je i peti element, a trebalo je da ostane -1", -1, instance.weights[4], 0.001);

	}
	
	@Test(timeout = 2000)
	public void method_insertTooLight() {
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
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unetu tezinu 40.0 koja je premala NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
			assertEquals("Ipak je povecan counter iako nije trebalo jer je tezina premala", 0, instance.counter);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void method_insertArrayFull() {
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
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za pokusaj unosa kad je niz vec pun NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
			assertEquals("Ipak je povecan counter iako nije trebalo jer je niz pun", 5, instance.counter);
			assertNotEquals("Ipak je uneta tezina iako je niz pun", 99.9, instance.weights[4], 0.001);
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

			assertEquals("Za unete tezine 110.5 i 82.3 NE ispisuju se te tezine na ekranu", s.trim(), ispis.trim());
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
		
		assertEquals("Za uneti niz tezina {110.5, 82.3, 50.0, 182.3}, metoda kao minimum ne vraca 50.0",50.0, instance.findLightest(), 0.001);
	}
	
	@Test(timeout = 2000)
	public void method_findWeightTrue() {
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(50.0);
		instance.insert(182.3);
		
		assertEquals("Za uneti niz tezina {110.5, 82.3, 50.0, 182.3}, metoda ne pronalazi 50.0 u nizu",true, instance.findWeight(50.0));
	}
	
	@Test(timeout = 2000)
	public void method_findWeightFalse() {
		instance.insert(110.5);
		instance.insert(82.3);
		instance.insert(50.0);
		instance.insert(182.3);
		
		assertEquals("Za uneti niz tezina {110.5, 82.3, 50.0, 182.3}, metoda pronalazi 112.3 u nizu a ne bi trebalo",false, instance.findWeight(112.3));
	}

	@Test(timeout = 2000)
	public void method_printPercents() {
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

			assertEquals("Za unete tezine 110.5 i 82.3 NE ispisuju se te tezine na ekranu", s.trim(), ispis.trim());
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
		
	}

}
