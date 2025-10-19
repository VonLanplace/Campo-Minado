package controller;

import model.MarioBeep;

public class BuzzerBiip {

	private MarioBeep marioBeep;

	public BuzzerBiip() {
		super();
		this.marioBeep = new MarioBeep();
	}

	public void playBad() {
		this.marioBeep.playDamageExtraShort();
	}

	public void playWin() {
		this.marioBeep.playOneUp();
	}

	public void playLose() {
		this.marioBeep.playMarioDeath();
	}

	public void playStart() {
		this.marioBeep.playJump();
	}

}
