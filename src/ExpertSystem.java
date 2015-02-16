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
		int numberOfObstacles = 0;
		try {
			// Expected format:
			// first value M size of grid
			String[] gridMN = args[0].split(",");
			gridM = Integer.parseInt(gridMN[0]);
			gridN = Integer.parseInt(gridMN[1]);
			
			// x,y start position of robot
			String[] initialXY = args[1].split(",");
			initial[0] = Integer.parseInt(initialXY[0]);
			initial[1] = Integer.parseInt(initialXY[1]);
			
			// x,y;x,y;x,y values of obstacles
			String[] obstaclesInput = args[2].split("\\*");
			numberOfObstacles= obstaclesInput.length;
			obstacles = new int [numberOfObstacles][2];
			
			for (int i = 0; i < obstaclesInput.length; i++) {
				String[] xy = obstaclesInput[i].split(",");
				obstacles[i][0] = Integer.parseInt(xy[0]);
				obstacles[i][1] = Integer.parseInt(xy[1]);
			
			}
			
			
			// x,y goal
			String[] goalXY = args[3].split(",");
			goal[0] = Integer.parseInt(goalXY[0]);
			goal[1] = Integer.parseInt(goalXY[1]);
		
			// last argument file
			outputFile = args[4];

		} catch (Exception e) {
			System.out.println("Please enter a correct inputs");
			System.out.println(e.getMessage());
			return;
		}

		World world = new World(gridM, gridN, goal[0], goal[1]);
		for (int i = 0; i < numberOfObstacles; i++) {
			world.Grid[obstacles[i][0]][obstacles[i][1]] = Enums.GridValues.O;
		}
		
		Robot robot = new Robot(world, initial[0], initial[1], goal[0], goal[1]);

		robot.MoveTowardsGoal();

	//	System.out.println(world.toString());
		System.out.println(robot._personalGrid.toString());

		File.WriteFile(outputFile, robot._personalGrid.toString());

	}
}
