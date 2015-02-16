public class World {
	public final int SizeX;
	public final int SizeY;
	public Enums.GridValues[][] Grid;

	public World(int sizeX, int sizeY) {
		SizeX = sizeX;
		SizeY = sizeY;
		Grid = new Enums.GridValues[SizeX][SizeY];

		//create the default world. Everything is empty
		for (int columns = 0; columns < SizeX; columns++) {
			for (int rows = 0; rows < SizeY; rows++)
				Grid[columns][rows] = Enums.GridValues.E;
		}

	}


	public World(int sizeX, int sizeY, int goalX, int goalY) {
		SizeX = sizeX;
		SizeY = sizeY;
		Grid = new Enums.GridValues[SizeX][SizeY];

		//create the default world. Everything is empty
		for (int columns = 0; columns < SizeX; columns++) {
			for (int rows = 0; rows < SizeY; rows++)
				Grid[columns][rows] = Enums.GridValues.E;
		}
		
		Grid[goalX][goalY] = Enums.GridValues.G;

	}
	
	public String toString() {
		String result = "";
		//Easily display the world
		for (int rows = 0; rows < SizeX; rows++) {
			for (int columns = 0; columns < SizeY; columns++) {
				result += Grid[columns][rows].toString() + " ";
			}

			result += "\n";
		}

		return result;
	}
}
