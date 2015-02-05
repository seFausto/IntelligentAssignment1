public class World {
	public final int SizeX;
	public final int SizeY;
	public Enums.GridValues[][] Grid;

	public World(int sizeX, int sizeY) {
		SizeX = sizeX;
		SizeY = sizeY;
		Grid = new Enums.GridValues[SizeX][SizeY];

		for (int columns = 0; columns < SizeX; columns++) {
			for (int rows = 0; rows < SizeY; rows++)
				Grid[columns][rows] = Enums.GridValues.E;
		}

	}

	public String toString() {
		String result = "";
		for (int rows = 0; rows < SizeX; rows++) {
			for (int columns = 0; columns < SizeY; columns++) {
				result += Grid[columns][rows].toString() + " ";
			}

			result += "\n";
		}

		return result;
	}
}
