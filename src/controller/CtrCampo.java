package controller;

public class CtrCampo {

	private int[][] field;
	private int difficulty;
	private int minesQtd;

	public int[][] getField() {
		return field;
	}

	public int getSquare(int i, int j) {
		return field[i][j];
	}

	public int getMinesQtd() {
		return minesQtd;
	}

	public int getDifficulty() {
		return difficulty;
	}

	// public static void main(String[] args) {
	// int size = 25;
	// CtrCampo campo = new CtrCampo(size, size * (size / 5));
	// System.out.println(campo.toString());
	// }

	public CtrCampo(int difficulty, int minesQtd) {
		super();
		this.field = new int[difficulty][difficulty];
		this.difficulty = difficulty;
		this.minesQtd = minesQtd;
		populate();
	}

	private void populate() {
		for (int i = 0; i < this.minesQtd; i++) {
			int x = randInt(difficulty - 1);
			int y = randInt(difficulty - 1);

			if (this.field[x][y] >= 0) {
				this.field[x][y] = -1;
				setMine(x, y);
			} else {
				i--;
			}
		}
	}

	private void setMine(int x, int y) {
		for (int i = -1; i <= 1; i++) {
			int k = x + i;

			for (int l = -1; l <= 1; l++) {
				int j = y + l;

				if (k >= 0 && j >= 0) {
					if (k < this.difficulty && j < this.difficulty) {
						if (this.field[k][j] >= 0)
							this.field[k][j]++;
					}
				}
			}
		}
	}

	private int randInt(int max) {
		return (int) (Math.random() * (max + 1));
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		for (int[] i : this.field) {
			text.append("|");
			for (int j : i) {
				if (j >= 0) {
					text.append(j);
				} else {
					text.append("*");
				}
				text.append("|");
			}
			text.append("\n");
		}
		return text.toString();
	}
}
