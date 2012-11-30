/**
 * This class generates cylinder references ranging from 0 .. 99
 *
 * Usage:
 *	Generator gen = new Generator()
 *	int[] ref = gen.getCylinders();
 */

public class Generator
{
	private static final int DEFAULT_SIZE = 100;
	private static final int RANGE = 99;

	int[] referenceString;

	public Generator() {
		this(DEFAULT_SIZE);
	}

	public Generator(int count) {
		if (count < 0)
			throw new IllegalArgumentException();

		java.util.Random generator = new java.util.Random();
		referenceString = new int[count];

		for (int i = 0; i < count; i++)
			referenceString[i] = generator.nextInt(RANGE + 1);
	}

	public int[] getCylinders() {
		return referenceString;
	}
}
