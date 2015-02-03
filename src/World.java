public class World {
	public final int Size;
	public Enums.GridValues[][] Grid;

	public World(int size) {
		Size = size;
		
		Grid = new Enums.GridValues[Size][Size];

		for (int columns = 0; columns < Size; columns++) {
			for (int rows = 0; rows < Size; rows++)
				Grid[columns][rows] = Enums.GridValues.Empty;
		}

	}

	public String toString() {
		String result = "";
		for (int rows = 0; rows < Size; rows++) {
			for (int columns = 0; columns < Size; columns++) {
				result += Grid[columns][rows].toString() + " ";
			}

			result += "\n";
		}

		return result;
	}
}
