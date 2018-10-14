package p2;

public class TestTeamMemberHeights {

	public static void main(String[] args) {
		TeamMemberHeights team1 = new TeamMemberHeights(20);
		TeamMemberHeights team2 = new TeamMemberHeights(30);
		TeamMemberHeights team3 = new TeamMemberHeights(40);

		team2.insert(186);
		team2.insert(169);
		team2.insert(224);
		
		System.out.println("Mean height: " + team2.getMeanHeight());
	}

}
