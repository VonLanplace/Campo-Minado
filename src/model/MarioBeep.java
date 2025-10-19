package model;

import java.awt.Toolkit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class MarioBeep {

	public MarioBeep() {
		super();
	}

	// Som de dano do Mario (quando leva hit e perde power-up)
	public void playDamage() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				// Som de dano: rápido e "pesado"
				int[] damageNotes = { 330, 262, 196 }; // E4, C4, G3 (descendente)
				int[] damageDurations = { 100, 100, 150 };

				for (int i = 0; i < damageNotes.length; i++) {
					playSquareWave(line, damageNotes[i], damageDurations[i], 0.7);
					if (i < damageNotes.length - 1) {
						Thread.sleep(20);
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Som de dano alternativo (mais curto e impactante)
	public void playDamageShort() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				// Som mais curto e grave
				int[] damageNotes = { 392, 294, 220 }; // G4, D4, A3
				int[] damageDurations = { 80, 80, 120 };

				for (int i = 0; i < damageNotes.length; i++) {
					playSquareWave(line, damageNotes[i], damageDurations[i], 0.8);
					if (i < damageNotes.length - 1) {
						Thread.sleep(15);
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Som quando fica pequeno (após levar dano)
	public void playShrink() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				// Efeito de "encolhimento" - pitch descendente rápido
				for (int freq = 523; freq > 196; freq -= 30) {
					playSquareWave(line, freq, 15, 0.6);
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Método principal para gerar onda quadrada (8-bit)
	private void playSquareWave(SourceDataLine line, int frequency, int durationMs, double volume) {
		try {
			int samples = (int) (44100.0 * durationMs / 1000.0);
			byte[] buffer = new byte[samples];
			int period = 44100 / frequency;

			for (int i = 0; i < samples; i++) {
				// Gera onda quadrada típica de 8-bit
				double value = (i % period) < (period / 2) ? 1.0 : -1.0;

				// Envelope para suavizar o som
				double envelope;
				if (i < samples * 0.1) {
					envelope = i / (samples * 0.1); // Attack
				} else if (i > samples * 0.8) {
					envelope = (samples - i) / (samples * 0.2); // Release
				} else {
					envelope = 1.0; // Sustain
				}

				buffer[i] = (byte) (value * 127.0 * volume * envelope);
			}

			line.write(buffer, 0, buffer.length);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// [Mantenha os outros métodos anteriores: playJump(), playMarioDeath(),
	// playOneUp(), playCoin(), playPowerUp()]
	// Som de pulo do Mario
	public void playJump() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				int[] jumpNotes = { 523, 659 };
				int[] jumpDurations = { 60, 80 };

				for (int i = 0; i < jumpNotes.length; i++) {
					playSquareWave(line, jumpNotes[i], jumpDurations[i], 0.5);
					if (i < jumpNotes.length - 1) {
						Thread.sleep(10);
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Música da morte do Mario (8-bit)
	public void playMarioDeath() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				int[] deathNotes = { 523, 494, 466, 440, 415, 392, 370, 349 };
				int[] deathDurations = { 70, 70, 70, 70, 70, 70, 70, 120 };

				for (int i = 0; i < deathNotes.length; i++) {
					playSquareWave(line, deathNotes[i], deathDurations[i], 0.6);
					if (i < deathNotes.length - 1) {
						Thread.sleep(15);
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Música do 1-Up (8-bit)
	public void playOneUp() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				int[] oneUpNotes = { 659, 784, 1047, 1318 };
				int[] oneUpDurations = { 120, 120, 120, 250 };

				for (int i = 0; i < oneUpNotes.length; i++) {
					playSquareWave(line, oneUpNotes[i], oneUpDurations[i], 0.5);
					if (i < oneUpNotes.length - 1) {
						Thread.sleep(10);
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Som de moeda coletada
	public void playCoin() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				int[] coinNotes = { 1047, 1318 };
				int[] coinDurations = { 40, 100 };

				for (int i = 0; i < coinNotes.length; i++) {
					playSquareWave(line, coinNotes[i], coinDurations[i], 0.4);
					if (i < coinNotes.length - 1) {
						Thread.sleep(5);
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Som de power-up
	public void playPowerUp() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				int[] powerUpNotes = { 262, 330, 392, 523, 659, 784 };
				int[] powerUpDurations = { 50, 50, 50, 50, 50, 150 };

				for (int i = 0; i < powerUpNotes.length; i++) {
					playSquareWave(line, powerUpNotes[i], powerUpDurations[i], 0.5);
					if (i < powerUpNotes.length - 1) {
						Thread.sleep(8);
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}

	// Som de dano super curto e impactante
	public void playDamageExtraShort() {
		new Thread(() -> {
			try {
				int sampleRate = 44100;
				AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();

				// Apenas 2 notas muito rápidas e graves
				int[] damageNotes = { 392, 294 }; // G4, D4
				int[] damageDurations = { 60, 80 }; // Muito curtas

				for (int i = 0; i < damageNotes.length; i++) {
					playSquareWave(line, damageNotes[i], damageDurations[i], 0.8);
					if (i < damageNotes.length - 1) {
						Thread.sleep(10); // Quase sem pausa
					}
				}

				line.drain();
				line.stop();
				line.close();

			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}).start();
	}
}