public class Robot {

	World _grid;
	World _personalGrid;

	Enums.Orientation Orientation;
	Enums.Orientation _nextMove;

	int _currentX;
	int _currentY;

	int _goalX;
	int _goalY;

	final int _maxSpace;

	public Robot(World grid, int startingX, int startingY, int goalX, int goalY) {
		_grid = grid;
		_personalGrid = new World(grid.Size);
		_maxSpace = grid.Size - 1;

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
			reachedGoal = Move(GetNextMove());
		} while (!reachedGoal);

		return numberOfMoves;
	}

	public Enums.Orientation GetNextMove() {
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

		if (_currentY < _maxSpace) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;

			_currentY++;
			Orientation = Enums.Orientation.Down;
			SetCurrent();
			result = true;

			System.err.println("Moved Down");
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

		if (_currentY > 0 || _currentX < _maxSpace) {
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

		if (_currentY < _maxSpace || _currentX > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentY++;
			_currentX--;
			result = true;
			Orientation = Enums.Orientation.DownLeft;
			SetCurrent();

			System.err.println("Moved DownLeft");
		} else {
			System.err.println("Reached end: DownLeft");

		}

		return result;
	}

	public Boolean MoveDownRight() {
		Boolean result = false;

		if (_currentY < _maxSpace || _currentX < _maxSpace) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.Visited;
			_currentY++;
			_currentX++;
			result = true;
			Orientation = Enums.Orientation.DownRight;
			SetCurrent();

			System.err.println("Moved DownRight");
		} else {
			System.err.println("Reached end: DownRight");

		}

		return result;
	}

	public Boolean MoveRight() {
		Boolean result = false;

		if (_currentX < _grid.Size) {
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
}
