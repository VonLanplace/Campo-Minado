package controller;

import java.util.ArrayList;

import model.Campo;
import view.FieldSquare;

public class CtrBuilder {
	int difficulty;

	public CtrBuilder(int difficulty) {
		super();
		this.difficulty = difficulty;
	}

	public FieldSquare[][] generateButtons() {
		int size = difficulty;
		Campo field = new Campo(size);
		FieldSquare[][] buttons = new FieldSquare[size][size];
		CtrButton ctrButton = new CtrButton(size);
		FieldSquare.setCtrButton(ctrButton);

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				buttons[i][j] = new FieldSquare(field.getSquare(i, j), ctrButton);

		// Apresentar os vizinhos
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ArrayList<FieldSquare> vizinhos = new ArrayList<FieldSquare>();

				for (int k = -1; k < 2; k++) {
					for (int l = -1; l < 2; l++) {
						int x = i + k, y = j + l;

						if (x >= 0 && x < size) {
							if (y >= 0 && y < size) {
								if (x == i && y == j) {
									// System.out.println("no" + x + "|" + y);
								} else {
									// System.out.println("in" + x + "|" + y);
									vizinhos.add(buttons[x][y]);
								}
							}
						}
					}
				}

				/*
				 * for (int k = 0; k < vizinhos.size(); k++) {
				 * System.out.print(vizinhos.get(k).getValueSquare()); System.out.print(","); }
				 * 
				 * System.out.println();
				 */
				buttons[i][j].setVizinhos(vizinhos);
			}
		}

		System.out.println(field.toString());
		return buttons;
	}
}
