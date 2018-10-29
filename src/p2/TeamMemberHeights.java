package p2;

class TeamMemberHeights {
	
	int[] heights;
	int counter = 0;
	
	public TeamMemberHeights(int capacity) {
		if (capacity > 0)
			heights = new int[capacity];
		else
			heights = new int[20];
		
		for(int i=0; i<heights.length; i++)
			heights[i] = 0;
	}
	
	void insert(int newHeight) {
		if (counter < heights.length && newHeight >= 160 && newHeight <= 250) {
			heights[counter] = newHeight;
			counter++;
		}
		else
			System.out.println("ERROR");
	}
	
	int getMeanHeight() {
		int sum = 0;
		
		for(int i=0; i < counter; i++)
			sum = sum + heights[i];
		
		return sum / counter;
	}
	
	int getMaxDifference() {
		int min = heights[0];
		int max = heights[0];
		
		for (int i = 0; i < counter; i++) {
			if (heights[i] < min) min = heights[i];
			if (heights[i] > max) max = heights[i];
		}
		
		return (max - min);
	}

}
