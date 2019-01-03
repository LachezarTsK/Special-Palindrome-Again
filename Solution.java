import java.util.Scanner;

public class Solution {
	// stores the char of each consecutive substring.
	private static char[] charOfSubstrings;
	// stores the length of each consecutive substring consisting of the same char.
	private static long[] lengthOfSubtrings;

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		int lengthOfString = reader.nextInt();
		String inputString = reader.next();
		reader.close();

		int totalConsecutiveSubstrings = extract_allSubstringsOfSameChars(lengthOfString, inputString);
		long result = totalSpecialPalindromicSubstrings(totalConsecutiveSubstrings);
		System.out.print(result);
	}

	/**
	 * Calculates the total number of Special Palindromic Substrings.
	 */
	private static long totalSpecialPalindromicSubstrings(int totalConsecutiveSubstrings) {

		long total = calculate_allSubstringsInSubstringOfSameChar(lengthOfSubtrings[0]);
		if (totalConsecutiveSubstrings == 1) {
			return total;
		}
		total += calculate_allSubstringsInSubstringOfSameChar(lengthOfSubtrings[1]);
		if (totalConsecutiveSubstrings == 2) {
			return total;
		}

		for (int i = 2; i < totalConsecutiveSubstrings; i++) {
			total += calculate_allSubstringsInSubstringOfSameChar(lengthOfSubtrings[i]);
			if (charOfSubstrings[i - 2] == charOfSubstrings[i]) {
				if (lengthOfSubtrings[i - 1] == 1) {
					total += lengthOfSubtrings[i - 2] < lengthOfSubtrings[i] ? lengthOfSubtrings[i - 2]
							: lengthOfSubtrings[i];
				}
			}
		}
		return total;
	}

	/**
	 * Calculates all possible substrings within each consecutive substring that
	 * consists of same chars.
	 */
	private static long calculate_allSubstringsInSubstringOfSameChar(Long totalChars) {
		return (long) ((1 + totalChars) * totalChars) / 2;
	}

	/**
	 * Extracts the char and the length of each consecutive substring consisting of
	 * this char. Records the values in the corresponding instance variables.
	 */
	private static int extract_allSubstringsOfSameChars(int lengthOfString, String inputString) {

		long counterSameCharInSubstring = 1;
		int totalConsecutiveSubstringsOfSameChar = 1;
		char previousChar = '\u0000';
		char currentChar = '\u0000';

		charOfSubstrings = new char[lengthOfString];
		lengthOfSubtrings = new long[lengthOfString];

		for (int i = 1; i < lengthOfString; i++) {
			previousChar = inputString.charAt(i - 1);
			currentChar = inputString.charAt(i);
			if (previousChar == currentChar) {
				counterSameCharInSubstring++;
			} else {
				charOfSubstrings[totalConsecutiveSubstringsOfSameChar - 1] = previousChar;
				lengthOfSubtrings[totalConsecutiveSubstringsOfSameChar - 1] = counterSameCharInSubstring;
				totalConsecutiveSubstringsOfSameChar++;
				counterSameCharInSubstring = 1;
			}
		}

		charOfSubstrings[totalConsecutiveSubstringsOfSameChar - 1] = currentChar;
		lengthOfSubtrings[totalConsecutiveSubstringsOfSameChar - 1] = counterSameCharInSubstring;

		return totalConsecutiveSubstringsOfSameChar;
	}
}
