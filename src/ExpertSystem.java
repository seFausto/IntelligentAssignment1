//import Enums.Orientation;

public class ExpertSystem {

	public static void main(String[] args) {

		World world = new World(5, 5);
		
		world.Grid[0][3]= Enums.GridValues.Obstacle;

		Robot robot = new Robot(world, 0, 0, 4, 4);

		robot.ScanForObstacles();
		
		
		System.out.println(robot._personalGrid.toString());

	}
}
