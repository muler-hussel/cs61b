package lab14;

import lab14lib.Generator;
import lab14lib.GeneratorAudioVisualizer;
import lab14lib.GeneratorPlayer;
import lab14lib.MultiGenerator;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		/** Your code here.
		Generator generator = new SineWaveGenerator(200);
		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
		gav.drawAndPlay(4096, 1000000);*/

		Generator g1 = new SineWaveGenerator(200);
		Generator g2 = new SineWaveGenerator(201);

		ArrayList<Generator> generators = new ArrayList<Generator>();
		generators.add(g1);
		generators.add(g2);
		MultiGenerator mg = new MultiGenerator(generators);

		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(mg);
		gav.drawAndPlay(500000, 1000000);
	}
} 