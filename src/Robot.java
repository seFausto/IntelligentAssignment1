
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
		Enums.Orientation nextOrientation = Orientation;
		do {

			ScanForObstacles();
			System.out.println(_personalGrid.toString());

			if (nextOrientation != null)
				Orientation = nextOrientation;
			else
				Orientation = GetOrientationBasedOnMovement();

			Enums.Orientation possibleOrientation = GetPossibleMove(
					Orientation, 0);
			int[] movement = GetNextMove(possibleOrientation);
			int[] nextPosition = GetNextPosition(_currentX, _currentY,
					movement[0], movement[1]);

			Boolean isNextPositionObstacle = IsNextPoistionObstacle(nextPosition);

			// check if there is an obstacle, if so, try to move the space on
			// the left,
			if (isNextPositionObstacle) {
				// get new position to the left
				possibleOrientation = GetPossibleMove(Orientation, 1);
				movement = GetNextMove(possibleOrientation);
				nextPosition = GetNextPosition(_currentX, _currentY,
						movement[0], movement[1]);

				isNextPositionObstacle = IsNextPoistionObstacle(nextPosition);

			}

			// check if there is an obstacle, if so, try to move to the right
			if (isNextPositionObstacle) {
				possibleOrientation = GetPossibleMove(Orientation, 2);
				movement = GetNextMove(possibleOrientation);
				nextPosition = GetNextPosition(_currentX, _currentY,
						movement[0], movement[1]);

				isNextPositionObstacle = IsNextPoistionObstacle(nextPosition);

			}

			if (isNextPositionObstacle) {
				// rotate
				nextOrientation = RotateCounterClockwise(Orientation, false);
				continue;
			}

			// Check if it can move in the intended direction
			if (IsValidGridSpace(nextPosition[0], nextPosition[1])) {
				if (IsSpaceEmpty(nextPosition[0], nextPosition[1])) {
					reachedGoal = Move(possibleOrientation);
					Orientation = GetOrientationBasedOnMovement();
				} else {
					Orientation = RotateCounterClockwise(Orientation, false);
				}
			} else {
				Orientation = RotateCounterClockwise(Orientation, false);
			}

			numberOfMoves++;
			nextOrientation = null;

		} while (!reachedGoal);

		return numberOfMoves;
	}

	private Boolean IsNextPoistionObstacle(int[] nextPosition) {
		int x = nextPosition[0];
		int y = nextPosition[1];

		if (x < 0 || x > _maxSpaceX)
			return true;

		if (y < 0 || y > _maxSpaceY)
			return true;

		if (!IsValidGridSpace(nextPosition[0], nextPosition[1]))
			return true;

		return _grid.Grid[x][y] == Enums.GridValues.O;

	}

	private Enums.Orientation RotateCounterClockwise(
			Enums.Orientation orientation, boolean rotateClockwise) {
		Enums.Orientation result = Enums.Orientation.None;

		switch (orientation) {
		case Down:
			result = Enums.Orientation.DownRight;
			break;
		case DownLeft:
			result = Enums.Orientation.Down;
			break;
		case DownRight:
			result = Enums.Orientation.Right;
			break;
		case Left:
			result = Enums.Orientation.DownLeft;
			break;
		case Right:
			result = Enums.Orientation.UpRight;
			break;
		case Up:
			result = Enums.Orientation.UpRight;
			break;
		case UpLeft:
			result = Enums.Orientation.Left;
			break;
		case UpRight:
			result = Enums.Orientation.Up;
			break;
		default:
			break;
		}

		return result;
	}

	public Boolean IsSpaceEmpty(int x, int y) {

		if (_personalGrid.Grid[x][y] == Enums.GridValues.O)
			return false;

		return true;
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

	public Enums.Orientation GetPossibleMove(Enums.Orientation orientation,
			int numberOfOrientation) {
		Enums.Orientation[] result = new Enums.Orientation[3];

		switch (orientation) {
		case Down:
			result[0] = Enums.Orientation.Down;
			result[2] = Enums.Orientation.DownLeft;
			result[1] = Enums.Orientation.DownRight;
			break;
		case DownLeft:
			result[1] = Enums.Orientation.Down;
			result[0] = Enums.Orientation.DownLeft;
			result[2] = Enums.Orientation.Left;
			break;
		case DownRight:
			result[2] = Enums.Orientation.Down;
			result[1] = Enums.Orientation.Right;
			result[0] = Enums.Orientation.DownRight;
			break;
		case Left:
			result[2] = Enums.Orientation.UpLeft;
			result[1] = Enums.Orientation.DownLeft;
			result[0] = Enums.Orientation.Left;
			break;
		case Right:
			result[0] = Enums.Orientation.Right;
			result[1] = Enums.Orientation.UpRight;
			result[2] = Enums.Orientation.DownRight;
			break;
		case Up:
			result[0] = Enums.Orientation.Up;
			result[2] = Enums.Orientation.UpLeft;
			result[1] = Enums.Orientation.UpRight;
			break;
		case UpLeft:
			result[2] = Enums.Orientation.Up;
			result[0] = Enums.Orientation.UpLeft;
			result[1] = Enums.Orientation.Left;
			break;
		case UpRight:
			result[1] = Enums.Orientation.Up;
			result[2] = Enums.Orientation.Right;
			result[0] = Enums.Orientation.UpRight;
			break;
		default:
			result[0] = Enums.Orientation.None;
			result[1] = Enums.Orientation.None;
			result[2] = Enums.Orientation.None;
			break;
		}

		return result[numberOfOrientation];
	}

	public int[] GetNextMove(Enums.Orientation orientation) {
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
		default:
			Log("Error getting next move");
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
			Log("Error!!1!!ONE!");
			break;
		}

		return result;
	}

	public Boolean MoveDown() {
		Boolean result = false;

		if (_currentY < _maxSpaceY) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;

			_currentY++;
			Orientation = Enums.Orientation.Down;
			SetCurrent();
			result = true;

			Log("Moved Down : X,Y++");
		} else {
			Log("Reached end: Down. X,Y++");

		}

		return result;
	}

	public Boolean MoveUp() {
		Boolean result = false;

		if (_currentY > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;
			_currentY--;
			result = true;
			Orientation = Enums.Orientation.Up;
			SetCurrent();

			Log("Moved Up. X, Y--");
		} else {
			Log("Reached end: Up. X, Y--");

		}

		return result;
	}

	public Boolean MoveUpLeft() {
		Boolean result = false;

		if (_currentY > 0 || _currentX > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;
			_currentY--;
			_currentX--;
			result = true;
			Orientation = Enums.Orientation.UpLeft;
			SetCurrent();

			Log("Moved UpLeft. X--, Y--");
		} else {
			Log("Reached end: UpLeft. X--, Y--");

		}

		return result;
	}

	public Boolean MoveUpRight() {
		Boolean result = false;

		if (_currentY > 0 || _currentX < _maxSpaceX) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;
			_currentY--;
			_currentX++;
			result = true;
			Orientation = Enums.Orientation.UpRight;
			SetCurrent();

			Log("Moved UpRight. X++,Y--");
		} else {
			Log("Reached end: UpRight. X++,Y--");

		}

		return result;
	}

	public Boolean MoveDownLeft() {
		Boolean result = false;

		if (_currentY < _maxSpaceY && _currentX > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;
			_currentY++;
			_currentX--;
			result = true;
			Orientation = Enums.Orientation.DownLeft;
			SetCurrent();

			Log("Moved DownLeft. X--, Y++");
		} else {
			Log("Reached end: DownLeft.  X--, Y++");
		}

		return result;
	}

	public Boolean MoveDownRight() {
		Boolean result = false;

		if (_currentY < _maxSpaceY || _currentX < _maxSpaceX) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;
			_currentY++;
			_currentX++;
			result = true;
			Orientation = Enums.Orientation.DownRight;
			SetCurrent();

			Log("Moved DownRight: X++, Y++");
		} else {
			Log("Reached end: DownRight. X++, Y++");
		}

		return result;
	}

	public Boolean MoveRight() {
		Boolean result = false;

		if (_currentX < _maxSpaceX) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;
			_currentX++;
			result = true;
			Orientation = Enums.Orientation.Right;
			SetCurrent();

			Log("Moved Right. X++, Y");
		} else {
			Log("Reached end: Right. X++, Y");

		}

		return result;
	}

	public Boolean MoveLeft() {
		Boolean result = false;

		if (_currentX > 0) {
			_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.V;
			_currentX--;
			result = true;
			Orientation = Enums.Orientation.Left;
			SetCurrent();

			Log("Moved Left. X--,Y");
		} else {
			Log("Reached end: Left. X--,Y");

		}

		return result;
	}

	private void SetCurrent() {
		_personalGrid.Grid[_currentX][_currentY] = Enums.GridValues.C;
	}

	public void ScanForObstacles() {
		Enums.Orientation[] scanLineOrientations = GetScanLineOrientations(this.Orientation);

		for (int i = 0; i < scanLineOrientations.length; i++) {
			Scan(GetNextMove(scanLineOrientations[i]));
		}

	}

	public int[] GetNextPosition(int x, int y, int moveX, int moveY) {
		int[] result = new int[2];

		result[0] = x + (1 * moveX);
		result[1] = y + (1 * moveY);

		return result;
	}

	private void Scan(int[] lineToScan) {
		if (lineToScan.length != 2) {
			Log("Something went wrong, lineToScan is diff than 2");
			return;
		}

		Boolean foundObstacle = false;

		int x = GetNextPosition(_currentX, _currentY, lineToScan[0],
				lineToScan[1])[0];
		int y = GetNextPosition(_currentX, _currentY, lineToScan[0],
				lineToScan[1])[1];

		while (!foundObstacle && IsValidGridSpace(x, y)) {

			if (_grid.Grid[x][y] == Enums.GridValues.O) {
				_personalGrid.Grid[x][y] = Enums.GridValues.O;
				break;
			}

			int[] next = GetNextPosition(x, y, lineToScan[0], lineToScan[1]);

			if (next[0] == x && next[1] == y) {
				break;
			} else {
				x = next[0];
				y = next[1];
			}
		}

	}

	private Enums.Orientation[] GetScanLineOrientations(
			Enums.Orientation orientation) {
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

	private boolean IsValidGridSpace(int x, int y) {
		return x <= _maxSpaceX && y <= _maxSpaceY && x >= 0 && y >= 0;
	}

	private void Log(String string) {

		// System.err.println(string);
	}

}
