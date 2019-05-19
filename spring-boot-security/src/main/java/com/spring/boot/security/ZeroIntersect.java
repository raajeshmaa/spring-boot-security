package com.spring.boot.security;

public class ZeroIntersect {
	int[] notFound = new int[] { -1, -1 };

	/*
	 * 
	 * Find the point of intersection of an all 0 column and row in a given 2D
	 * matrix:
	 * 
	 * Example 1: {1, 0, 3, 0}, {4, 0, 6, 9}, {0, 0, 0, 0}, {1, 0, 3, 0},
	 * 
	 * Answer: 2, 1
	 * 
	 * Example 2: {0, 1}, {0, 2}, {0, 0}, {0, 0},
	 * 
	 * Answer: 2, 0 OR 3, 0
	 * 
	 * Example 3: {1, 3, 0}, {4, 0, 9}, {0, 0, 0},
	 * 
	 * Answer: -1, -1
	 * 
	 */

	ZeroIntersect() {
	}

	public int[] getZeroIntersectLocation(int[][] input) {

		int matchingRow = 0;
		int matchingColumn = 0;
		for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
			int[] row = input[inputIndex];
			boolean isRowColumnAllZeros = true;
			for (int rowIndex = 0; rowIndex < row.length; rowIndex++) {
				if (row[rowIndex] != 0) {
					isRowColumnAllZeros = false;
					break;
				}
				isRowColumnAllZeros = true;
			}
			if (!isRowColumnAllZeros) {
				continue;
			}

			if (isRowColumnAllZeros) {
				matchingRow = inputIndex;
			}
		}
		System.out.println("matchingColumn" + matchingColumn);
		System.out.println("matchingRow" + matchingRow);
		return this.notFound;
	}

	public static void main(String[] args) {
		ZeroIntersect zeroIntersect = new ZeroIntersect();

		int[][] input = { { 1, 0, 3, 0 }, { 4, 0, 6, 9 }, { 0, 0, 0, 0 }, { 1, 0, 3, 0 }, };

		int[] output = zeroIntersect.getZeroIntersectLocation(input);
// System.out.println("Answer:");
// System.out.println(output[0] + ", " + output[1]);
	}

}