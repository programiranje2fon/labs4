package p1;

class TestTeamMemberWeights {

	public static void main(String[] args) {
		TeamMemberWeights team1 = new TeamMemberWeights(3);
		
		team1.insert(100.0);
		team1.insert(55.5);
		team1.insert(44.5);
		
		TeamMemberWeights team2 = new TeamMemberWeights();
		
		team2.insert(66.6);
		team2.insert(44.3);
		team2.insert(150.0);
		
		System.out.println(team2.findLightest());
		
	}

}
