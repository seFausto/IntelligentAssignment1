public class ExpertSystem {

	public static void main(String[] args) {

		World world = new World(25, 25);

		Robot robot = new Robot(world, 0,0, 5,14);

		robot.MoveTowardsGoal();
		
		System.out.println(robot._personalGrid.toString());
		
	}

}
