# Lab exercise 4

## Problem 1
(to be done by the tutor in cooperation with students)

Create the class **TeamMemberWeights** in the **p1** package. The class should include:

1. The attribute **weights** - an array of weights of members of a boxing team in kilograms (e.g. 89.5 kg) 

2. The attribute **counter** - the current number of team members. Initialize it to 0.

3. A constructor that initializes the array of weights to 20 elements (capacity 20) and sets all of them to -1. 

4. A constructor that takes the maximum number of team members as a parameter (array capacity), initializes the weights array to that capacity and sets all elements in the array to -1. 

5. The method **insert** that takes the weight of a new team member as its parameter and inserts it in the weights array at the first empty place (element with the value -1). Inserting can be done only if there is an empty place in the array and if the weight passed as the parameter is greater than 40.5 kg. If the array is full or the weight is not within the specified bounds, the method should write "ERROR" on the screen.

6. The method **print** that prints the array on the screen.

7. The method **findWeight** that takes a boxer's weight as its parameter and checks if in the array of weights one can identify another boxer with the same weight. If such a boxer can be identified, the method returns true; otherwise, it returns false. 

8. The method **findLightest** that finds the weight of the lightest boxer in the array and returns it. If the array is empty, the method returns -1.

9. The method  **printCategories** that prints the numbers of boxers in the team from the lightweight, middleweight and heavyweight categories. If a boxer's weight is less than 65kg, he belongs to the lighteight category. If his weight is greater than or equal to 65kg, but still less than 85kg, he belongs to the middleweight category. If his weight is greater than or equal to 85kg, he belongs to the heavyweight category. The printout should be in 3 lines, in the following format: "Lightweight:4", "Middleweight:1", "Heavyweight:9".

Create the class **TestTeamMemberWeights** in the **p1** package. The class should include the **main** method that creates two TeamMemberWeights objects. Initialize the first team so that it can have max 3 boxers and set their weights to 100.0, 55.5 and 44.5kg. Initialize the second team so that the actual number of boxers is unknown (but is max 20) and insert three weights - 66.6, 44.3 and 150.0 kg. Print the weight of the lightest boxer from the second team. 


## Problem 2
(students work on it themselves)

Create the class **TeamMemberHeights** in the **p2** package. The class should include:

1. The attribute **heights** - an array of heights of members of a basketball team in centimeters (integers, e.g. 189 cm). 

2. The attribute **counter** - the current number of team members. Initialize it to 0.

3. A constructor that takes the maximum number of team members (the array capacity)as its parameter and initializes the array of heights to the value of the parameter only of the value is greater than 0. Otherwise, the array is initialized to the capacity of 20. In either case, initialize all elements of the array to 0.

4. The method **insert** that takes the height of a new team member as its parameter and inserts it in the heights array at the first empty place (element with the value 0). Inserting can be done only if there is an empty place in the array and if the height passed as the parameter is between 160 and 250 cm (inclusive). If the array is full or the height is not within the specified bounds, the method should write "ERROR" on the screen.

5. The method **getMeanHeight** that returns the mean height of the players in the team as an integer.

6. The method **getMaxDifference** that returns the height difference between the tallest and the shortest player in the team.

Create the class **TestTeamMemberHeights** in the **p2** package. The class should include the **main** method that creates three TeamMemberHeights objects, with the capacities of 20, 30 and 40 members. Insert the heights of 186, 169 and 224cm in the second team and print the mean height of the second team.