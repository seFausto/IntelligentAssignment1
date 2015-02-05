//import Enums.Orientation;

public class ExpertSystem {

	public static void main(String[] args) {

		World world = new World(5, 5);

		//world.Grid[0][3] = Enums.GridValues.O;
		//world.Grid[4][0] = Enums.GridValues.O;
		//world.Grid[0][4] = Enums.GridValues.O;
		//world.Grid[1][2] = Enums.GridValues.O;
		Robot robot = new Robot(world, 0, 0, 4, 4);
		
		robot.MoveTowardsGoal();
		
		System.out.println(robot._personalGrid.toString());

	}
}
