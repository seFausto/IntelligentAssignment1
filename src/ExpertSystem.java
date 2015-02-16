public class ExpertSystem {

	public static void main(String[] args) {

		if (args.length <= 1) {
			System.out.println("Please enter and input and output file");
			return;
		}

		String outputFile;
		int gridM = 0;
		int gridN = 0;

		int[][] obstacles;
		int[] goal = new int[2];
		int[] initial = new int[2];
		
		try {
			// Expected format:
			// first value M size of grid
			gridM = Integer.parseInt(args[0]);
			// 2nd value N of grid
			gridN = Integer.parseInt(args[1]);
			// x,y start position of robot
			String[] initialXY = args[2].split(",");
			initial[0] = Integer.parseInt(initialXY[0]);
			initial[1] = Integer.parseInt(initialXY[1]);
			// x,y;x,y;x,y values of obstacles
			String[] obsctacles = args[3].split(";");
			
			// x,y goal
			String[] goalXY = args[4].split(",");
			goal[0] = Integer.parseInt(goalXY[0]);
			goal[1] = Integer.parseInt(goalXY[1]);
			
			// last argument file
			outputFile = args[5];

		} catch (Exception e) {
			System.out.println("Please enter a correct inputs");
			return;
		}

		World world = new World(gridM, gridN);
		Robot robot = new Robot(world, initial[0], initial[1], goal[0], goal[1]);

//		robot.MoveTowardsGoal();

		System.out.println(world.Grid.toString());
		System.out.println(robot._personalGrid.toString());

		File.WriteFile(outputFile, robot._personalGrid.toString());

	}
}
