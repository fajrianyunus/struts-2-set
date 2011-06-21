package lib;

import java.util.Random;

public class RandomAlphaNumericStringGenerator {

	public static final int DEFAULT_LENGTH = 8;
	
	public static String generate(int length) {
		Random randomNumberGenerator = new Random();
		String output = new String();
		
		for (int i = 0 ; i < length ; i++) {
			double randomDouble = randomNumberGenerator.nextDouble();
			int randomInt = (int) Math.floor(randomDouble * 62);
			
			if (randomInt == 62) {
				randomInt = 61;
			}
			
			if (0 <= randomInt && randomInt < 10) {
				int asciiCode = randomInt + 48;
				output = output + (char) asciiCode;
			} else if (10 <= randomInt && randomInt < 36) {
				int asciiCode = randomInt + 55;
				output = output + (char) asciiCode;
			} else if (36 <= randomInt && randomInt < 62) {
				int asciiCode = randomInt + 61;
				output = output + (char) asciiCode;
			}
		}
		
		return output;		
	}
}
