package p1;

class TeamMemberWeights {
	
	double[] weights;
	int counter = 0;
	
	TeamMemberWeights(){
		weights = new double[20];
		
		for(int i=0; i<weights.length; i++)
			weights[i] = -1;
	}
	
	TeamMemberWeights(int capacity){
		weights = new double[capacity];
		
		for(int i=0; i<weights.length; i++)
			weights[i] = -1;
	}
	
	void insert(double newWeight) {
		if (counter < weights.length && newWeight >= 40.5) {
			weights[counter] = newWeight;
			counter++;
		}
		else
			System.out.println("ERROR");
	}
	
	void print() {
		for(int i=0; i<counter; i++)
			System.out.println(weights[i]);
	}
	
	double findLightest() {
		if (counter == 0)
			return -1;
		
		double min = weights[0];
		
		// forEach cannot be used, 
		// since the loop should run up to the counter value, 
		// not up to the length value
		for(int i=0; i<counter; i++)
			if (weights[i] < min) min = weights[i];
		
		return min;
	}
	
	boolean findWeight(double weight) {
		for(int i=0; i<counter; i++)
			if (weights[i] == weight)
				return true;
		
		return false;
	}
	
	void printCategories() {
		int noOfLightweight = 0;
		int noOfMiddleweight = 0;
		int noOfHeavyWeight = 0;
		
        // forEach cannot be used, 
        // since the loop should run up to the counter value, 
        // not up to the length value
		for(int i=0; i<counter; i++)
			if (weights[i] < 65) noOfLightweight++;
			else if (weights[i] < 85) noOfMiddleweight++;
			else noOfHeavyWeight++;
			
		System.out.println("Lightweight:" + noOfLightweight);
		System.out.println("Middleweight:" + noOfMiddleweight);
		System.out.println("Heavyweight:" + noOfHeavyWeight);
	}

}
