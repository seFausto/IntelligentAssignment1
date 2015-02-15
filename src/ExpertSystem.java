public class ExpertSystem {

	public static void main(String[] args) {

		World world = new World(5, 5);
		Robot robot = new Robot(world, 4, 4, 0, 0);

		world.Grid[3][4] = Enums.GridValues.O;
		world.Grid[1][0] = Enums.GridValues.O;
		world.Grid[0][1] = Enums.GridValues.O;
		
		robot.MoveTowardsGoal();

		System.out.println(robot._personalGrid.toString());

		File.WriteFile("test.txt", robot._personalGrid.toString());

	}
}
