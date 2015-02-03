public class ExpertSystem {

	public static void main(String[] args) {

		World world = new World(20);

		Robot robot = new Robot(world, 9, 9, 7, 4);

		robot.MoveTowardsGoal();
		
		System.out.println(robot._personalGrid.toString());
		
	}

}
