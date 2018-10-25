package p2;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p2.TeamMemberHeights;

public class TeamMemberHeightsTest {
	
	TeamMemberHeights instance;

	@Before
	public void setUp() throws Exception {
		instance = new TeamMemberHeights(5);
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test (timeout = 2000)
	public void konstruktor_TeamMemberHeights() {
		assertEquals("Kad se pozove sa brojem 5, ne inicijalizuje niz na 5 elemenata", 5, instance.heights.length);
		
		for(int visina: instance.heights)
			assertEquals("Element niza nije inicijalizovan na 0", 0, visina);

	}

	@Test (timeout = 2000)
	public void konstruktor_TeamMemberHeights_NegativeParameter() {
		instance = new TeamMemberHeights(-10);
		
		assertEquals("Kad se pozove sa brojem -10, ne inicijalizuje niz na 20 elemenata", 20, instance.heights.length);
		
		for(int visina: instance.heights)
			assertEquals("Element niza nije inicijalizovan na 0", 0, visina);
	}
	
	@Test (timeout = 2000)
	public void metoda_insert() {
		instance.insert(180);
		instance.insert(169);
		
		assertEquals("Ako se unesu dva elementa, metoda u niz ne ubaci oba kako treba, counter nije 2", 2, instance.counter);
		assertEquals("Unet je prvi element 180, ali nije postavljen na prvo mesto u nizu", 180, instance.heights[0]);
		assertEquals("Unet je drugi element 169, ali nije postavljen na drugo mesto u nizu", 169, instance.heights[1]);
		assertEquals("Unet je i treci element, a trebalo je da ostane 0", 0, instance.heights[2]);
		assertEquals("Unet je i cetvrti element, a trebalo je da ostane 0", 0, instance.heights[3]);
		assertEquals("Unet je i peti element, a trebalo je da ostane 0", 0, instance.heights[4]);
	}
	
	@Test(timeout = 2000)
	public void metoda_insertTooShort() {
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
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unetu visinu 159 koja je premala NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
			assertEquals("Ipak je povecan counter iako nije trebalo jer je visina premala", 0, instance.counter);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void metoda_insertTooTall() {
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
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unetu visinu 251 koja je prevelika NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
			assertEquals("Ipak je povecan counter iako nije trebalo jer je visina prevelika", 0, instance.counter);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test(timeout = 2000)
	public void metoda_insertArrayFull() {
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
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za pokusaj unosa kad je niz vec pun NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
			assertEquals("Ipak je povecan counter iako nije trebalo jer je niz pun", 5, instance.counter);
			assertNotEquals("Ipak je uneta tezina iako je niz pun", 199, instance.heights[4]);
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test (timeout = 2000)
	public void metoda_getMeanHeight() {
		instance.insert(180);
		instance.insert(169);
		instance.insert(190);
		
		assertEquals("Za visine 180, 169 i 190, prosek bi trebalo da bude 179", 179, instance.getMeanHeight());
	}

	@Test (timeout = 2000)
	public void metoda_getMaxDifference() {
		instance.insert(180);
		instance.insert(169);
		instance.insert(190);
		instance.insert(160);

		assertEquals("Za visine 180, 169, 190 i 160, razlika izmedju najviseg (190) i najnizeg (160) bi trebalo da bude 30 cm", 30, instance.getMaxDifference());
	}

}
