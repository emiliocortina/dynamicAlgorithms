package Dynamic;

import java.util.concurrent.ThreadLocalRandom;

public class Thieves {

	public static void main(String[] args) throws Exception {
		for (int size = 10; size < 1000000; size*=2) {
			int[] values = new int[size];
			int[] weights = new int[size];
			for (int i = 0; i < size; i++) {
				values[i] = ThreadLocalRandom.current().nextInt(10, 99);
				weights[i] = ThreadLocalRandom.current().nextInt(10, 99);
			}
			int maxWeight = 25*size;
			long t1 = System.currentTimeMillis();
			Thieves t = new Thieves(values, weights, maxWeight);
			long t2 = System.currentTimeMillis();
			long time = t2-t1;
			System.out.println(size+"\t"+time);
		}
	}

	private int[] v;
	private int[] w;
	private int k;
	private int n;
	private int[][] solution;

	public Thieves(int v[], int w[], int k) {
		this.v = v;
		this.w = w;
		this.k = k;

		this.n = w.length;
		this.solution = new int[n + 1][k + 1];

		// Initialization
		for (int j = 0; j <= k; j++) {
			solution[0][j] = 0;
		}

		for (int i = 0; i <= n; i++) {
			solution[i][0] = 0;
		}

		for (int item = 1; item <= n; item++) {
			for (int weight = 1; weight <= k; weight++) {
				if (w[item - 1] <= weight) {
					solution[item][weight] = Math.max(v[item - 1] + solution[item - 1][weight - w[item - 1]],
							solution[item - 1][weight]);
				} else {
					solution[item][weight] = solution[item - 1][weight];
				}
			}

		}

		// Pick solution[n][k] is the best solution to the problem.

	}
}
