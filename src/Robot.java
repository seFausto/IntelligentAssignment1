public class Robot {

	World _grid;
	World _personalGrid;

	Enums.Orientation Orientation;
	Enums.Orientation _nextMove;

	int _currentX;
	int _currentY;

	int _goalX;
	int _goalY;

	final int _maxSpaceX;
	final int _maxSpaceY;
	final int _maxNumberObstacles = 3;

	public Robot(World grid, int startingX, int startingY, int goalX, int goalY) {
		_grid = grid;
		_personalGrid = new World(grid.SizeX, grid.SizeY);
		_maxSpaceX = grid.SizeX - 1;
		_maxSpaceY = grid.SizeY - 1;

		_currentX = startingX;
		_currentY = startingY;

		_goalX = goalX;
		_goalY = goalY;

		SetCurrent();
		Orientation = Enums.Orientation.Down;
	}

	public int MoveTowardsGoal() {
		int numberOfMoves = 0;
		Boolean reachedGoal = false;

		do {
			reachedGoal = Move(GetOrientationBasedOnMovement());
			numberOfMoves++;
		} while (!reachedGoal);

		return numberOfMoves;
	}

	public Enums.Orientation GetOrientationBasedOnMovement() {
		Enums.Orientation result = Enums.Orientation.Down;

		int resultX = _goalX - _currentX;
		int resultY = _goalY - _currentY;

		if (resultX == 0 && resultY == 0)
			result = Enums.Orientation.None;

		else if (resultX > 0 && resultY == 0)
			result = Enums.Orientation.Right;

		else if (resultX < 0 && resultY == 0)
			result = Enums.Orientation.Left;

		else if (resultX == 0 && resultY > 0)
			result = Enums.Orientation.Down;

		else if (resultX == 0 && resultY < 0)
			result = Enums.Orientation.Up;

		else if (resultX > 0 && resultY > 0)
			result = Enums.Orientation.DownRight;

		else if (resultX < 0 && resultY < 0)
			result = Enums.Orientation.UpLeft;

		else if (resultX > 0 && resultY < 0)
			result = Enums.Orientation.UpRight;

		else if (resultX < 0 && resultY > 0)
			result = Enums.Orientation.DownLeft;

		return result;

	}

	public int[] GetNextPositionBasedOnOrientation(Enums.Orientation orientation) {
		int[] result = new int[2];
		switch (orientation) {
		case Down:
			result[0] = 0;
			result[1] = 1;
			break;
		case DownLeft:
			result[0] = -1;
			result[1] = 1;
			break;
		case DownRight:
			result[0] = 1;
			result[1] = 1;
			break;
		case Left:
			result[0] = -1;
			result[1] = 0;
			break;
		case None:
			result[0] = 0;
			result[1] = 0;
			break;
		case Right:
			result[0] = 1;
			result[1] = 0;
			break;
		case Up:
			result[0] = 0;
			result[1] = -1;
			break;
		case UpLeft:
			result[0] = -1;
			result[1] = -1;
			break;
		case UpRight:
			result[0] = 1;
			result[1] = -1;
			break;
		}

		return result;
	}

	public Boolean Move(Enums.Orientation orientation) {
		Boolean result = false;

		switch (orientation) {
		case Down:
			MoveDown();
			break;
		case DownLeft:
			MoveDownLeft();
			break;
		case DownRight:
			MoveDownRight();
			break;
		case Left:
			MoveLeft();
			break;
		case None:
			result = true;
			System.out.println("Your done!!!");
			break;
		case Right:
			MoveRight();
			break;
		case Up:
			MoveUp();
			break;
		case UpLeft:
			MoveUpLeft();
			break;
		case UpRight:
			MoveUpRight();
			break;
		default:
			System.out.println("Error!!1!!ONE!");
			break;
		}

		return result;
	}

	public Boolean MoveDown() {
		Boolean result = false;

		if (_currentY < _maxSpaceY) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;

			_currentY++;
			Orientation = Enums.Orientation.Down;
			SetCurrent();
			result = true;

			System.err.println("Moved Down : X,Y++");
		} else {
			System.err.println("Reached end: Down");

		}

		return result;
	}

	public Boolean MoveUp() {
		Boolean result = false;

		if (_currentY > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentY--;
			result = true;
			Orientation = Enums.Orientation.Up;
			SetCurrent();

			System.err.println("Moved Up");
		} else {
			System.err.println("Reached end: Up");

		}

		return result;
	}

	public Boolean MoveUpLeft() {
		Boolean result = false;

		if (_currentY > 0 || _currentX > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentY--;
			_currentX--;
			result = true;
			Orientation = Enums.Orientation.UpLeft;
			SetCurrent();

			System.err.println("Moved UpLeft");
		} else {
			System.err.println("Reached end: UpLeft");

		}

		return result;
	}

	public Boolean MoveUpRight() {
		Boolean result = false;

		if (_currentY > 0 || _currentX < _maxSpaceX) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentY--;
			_currentX++;
			result = true;
			Orientation = Enums.Orientation.UpRight;
			SetCurrent();

			System.err.println("Moved UpRight");
		} else {
			System.err.println("Reached end: UpRight");

		}

		return result;
	}

	public Boolean MoveDownLeft() {
		Boolean result = false;

		if (_currentY < _maxSpaceY && _currentX > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentY++;
			_currentX--;
			result = true;
			Orientation = Enums.Orientation.DownLeft;
			SetCurrent();

			System.err.println("Moved DownLeft. X--, Y++");
		} else {
			System.err.println("Reached end: DownLeft.  X--, Y++");
		}

		return result;
	}

	public Boolean MoveDownRight() {
		Boolean result = false;

		if (_currentY < _maxSpaceY || _currentX < _maxSpaceX) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentY++;
			_currentX++;
			result = true;
			Orientation = Enums.Orientation.DownRight;
			SetCurrent();

			System.err.println("Moved DownRight: X++, Y++");
		} else {
			System.err.println("Reached end: DownRight. X++, Y++");

		}

		return result;
	}

	public Boolean MoveRight() {
		Boolean result = false;

		if (_currentX < _maxSpaceX) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentX++;
			result = true;
			Orientation = Enums.Orientation.Right;
			SetCurrent();

			System.err.println("Moved Right");
		} else {
			System.err.println("Reached end: Right");

		}

		return result;
	}

	public Boolean MoveLeft() {
		Boolean result = false;

		if (_currentX > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentX--;
			result = true;
			Orientation = Enums.Orientation.Left;
			SetCurrent();

			System.err.println("Moved Left");
		} else {
			System.err.println("Reached end: Left");

		}

		return result;
	}

	private void SetCurrent() {
		_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Current;
	}

	public int[][] ScanForObstacles() {
		int[][] result = new int[_maxNumberObstacles][_maxNumberObstacles];

		Enums.Orientation[] scanLineOrientations = GetScanLineOrientations(this.Orientation);
		
		for (int i = 0; i < scanLineOrientations.length; i++) {
			Scan(GetNextPositionBasedOnOrientation(scanLineOrientations[i]));
		}

		return result;
	}

	private void Scan(int[] lineToScan) {
		if (lineToScan.length != 2) {
			Log("Something went wrong, lineToScan is diff than 2");
			return;
		}

		Boolean foundObstacle = false;

		int x = _currentX + (1 * lineToScan[0]);
		int y = _currentY + (1 * lineToScan[1]);

		while (!foundObstacle && ValidGridSpace(x, y)) {
			
			if (_grid.Grid[x][y] == Enums.GridValues.Obstacle) {
				Log("Found Obstacle");
				_personalGrid.Grid[x][y] = Enums.GridValues.Obstacle;
				break;
			}

			x = x + (1 * lineToScan[0]);
			y = y + (1 * lineToScan[1]);
		}

	}

	private Enums.Orientation[] GetScanLineOrientations(Enums.Orientation orientation) {
		Enums.Orientation[] result = new Enums.Orientation[3];

		result[0] = orientation;

		switch (orientation) {
		case Down:
			result[1] = Enums.Orientation.DownLeft; 
			result[2] = Enums.Orientation.DownRight;
			break;
		case DownLeft:
			result[1] = Enums.Orientation.Left; 
			result[2] = Enums.Orientation.Down;
			break;
		case DownRight:
			result[1] = Enums.Orientation.Down; 
			result[2] = Enums.Orientation.Right;
			break;
		case Left:
			result[1] = Enums.Orientation.DownLeft; 
			result[2] = Enums.Orientation.UpLeft;
			break;
		case None:
			result[1] = Enums.Orientation.None; 
			result[2] = Enums.Orientation.None;
			break;
		case Right:
			result[1] = Enums.Orientation.UpRight; 
			result[2] = Enums.Orientation.DownRight;
			break;
		case Up:
			result[1] = Enums.Orientation.UpLeft; 
			result[2] = Enums.Orientation.UpRight;
			break;
		case UpLeft:
			result[1] = Enums.Orientation.Up; 
			result[2] = Enums.Orientation.Left;
			break;
		case UpRight:
			result[1] = Enums.Orientation.Up; 
			result[2] = Enums.Orientation.Right;
			break;
		default:
			break;
		}

		return result;

	}

	private boolean ValidGridSpace(int x, int y) {
		return x <= _maxSpaceX && y <= _maxSpaceY && x >= 0 && y >= 0;
	}

	private void Log(String string) {
		System.err.println(string);
	}

}
