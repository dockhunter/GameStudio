package sk.tsystems.gamestudio.games.puzzlegame;

import sk.tsystems.gamestudio.games.puzzlegame.Tile;

import java.util.Random;

import sk.tsystems.gamestudio.games.puzzlegame.GameState;

public class Field {
		
	public final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	public final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	public final int columnCount;

	/**
	 * Mine count.
	 * 
	 * /** Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * /*
	 */
	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;

		tiles = new Tile[rowCount][columnCount];

		generateFiled();

		// generate the field content
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	private void generateFiled() {
		int total = (columnCount * rowCount);

		int count = 1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (count < total)
					tiles[i][j] = new Tile(count);
				count++;
				if (count > total)
					tiles[i][j] = new Tile(0);
			}
		}

		shuffel();
	}

	private void shuffel() {
		Random rnd = new Random();
		int total = (columnCount * rowCount) - 1;
		int tiletomove;

		for (int x = 0; x < 2000; x++) {
			tiletomove = rnd.nextInt(total) + 1;
			moveTile(tiletomove);
		}
	}

//	private void generateByRandom() {
//		Random rnd = new Random();
//		int total = (columnCount * rowCount) - 1;
//
//		while (total >= 0) {
//			int rndColumn = rnd.nextInt(columnCount);
//			int rndRow = rnd.nextInt(rowCount);
//			if (tiles[rndRow][rndColumn] == null) {
//				tiles[rndRow][rndColumn] = new Tile(total);
//				--total;
//			}
//
//		}
//
//	}

	public void moveTile(int tiletomove) {

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				Tile tile = tiles[i][j];
				if (tiletomove == tile.getValue()) {
					moveToAdjacentTile(i, j);
					return;
				}
			}
		}
	}

	public void moveToAdjacentTile(int row, int column) {
		Tile tile = tiles[row][column];

		int er = row - 1;
		int ec = column - 1;
		int ed = row + 1;
		int ef = column + 1;

		if (er >= 0 && er < rowCount && tiles[er][column].getValue() == 0) {
			tiles[er][column].setValue(tile.getValue());
			tile.setValue(0);
		}
		if (ec >= 0 && ec < columnCount && tiles[row][ec].getValue() == 0) {
			tiles[row][ec].setValue(tile.getValue());
			tile.setValue(0);
		}
		if (ed >= 0 && ed < rowCount && tiles[ed][column].getValue() == 0) {
			tiles[ed][column].setValue(tile.getValue());
			tile.setValue(0);
		}
		if (ef >= 0 && ef < columnCount && tiles[row][ef].getValue() == 0) {
			tiles[row][ef].setValue(tile.getValue());
			tile.setValue(0);
		}

	}

	public boolean isSolved() {
		int total = (columnCount * rowCount);
		int count = 1;

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				Tile tile = tiles[i][j];
				if (count < total && tile.getValue() != count)
						return false;
					count++;

			}
		}
	    return true;
	}

}
