
import java.util.*;
import java.lang.*;

public class Parity_bit {

	public static String[] parity_gen(String[] datawords, int word_size, int parity_type, int array_size) {
		String[] codeword = null;
		if (parity_type == 1 || parity_type == 2) {
			codeword = new String[array_size];
		} else {
			codeword = new String[array_size + 1];
		}
		for (int i = 0; i < array_size; i++) {
			StringBuilder temp = new StringBuilder(datawords[i]);
			for (int j = 0; j < word_size; j++) {
				if (temp.length() < word_size) {
					temp.append("0");
				}
				codeword[i] = temp.toString();
			}
		}
		System.out.println("Generating codeword...");
		switch (parity_type) {
			// one-dimensional-even
			case 1: {
				System.out.println("Using one-dimensional-even method");
				for (int i = 0; i < codeword.length; i++) {
					int sum = 0;
					StringBuilder temp = new StringBuilder(codeword[i]);
					for (int j = 0; j < temp.length(); j++) {
						if (temp.charAt(j) == '1') {
							sum++;
						}
					}
					if (sum % 2 == 0) {
						temp.append("0");
					} else {
						temp.append("1");
					}
					codeword[i] = temp.toString();
				}
				break;
			}
			// one-dimensional-odd
			case 2: {
				System.out.println("Using one-dimensional-odd method");
				for (int i = 0; i < codeword.length; i++) {
					int sum = 0;
					StringBuilder temp = new StringBuilder(codeword[i]);
					for (int j = 0; j < temp.length(); j++) {
						if (temp.charAt(j) == '1') {
							sum++;
						}
					}
					if (sum % 2 == 0) {
						temp.append("1");
					} else {
						temp.append("0");
					}
					codeword[i] = temp.toString();
				}
				break;
			}
			// two-dimensional-even
			case 3: {
				System.out.println("Using two-dimensional-even method");
				for (int i = 0; i < array_size; i++) {
					int sum = 0;
					StringBuilder temp = new StringBuilder(codeword[i]);
					for (int j = 0; j < temp.length(); j++) {
						if (temp.charAt(j) == '1') {
							sum++;
						}
					}
					if (sum % 2 == 0) {
						temp.append("0");
					} else {
						temp.append("1");
					}
					codeword[i] = temp.toString();
				}
				StringBuilder bit = new StringBuilder();
				for (int j = 0; j < word_size + 1; j++) {
					int sumCol = 0;
					for (int i = 0; i < array_size; i++) {
						StringBuilder temp = new StringBuilder(codeword[i]);
						if (temp.charAt(j) == '1') {
							sumCol++;
						}
					}
					if (sumCol % 2 == 0) {
						bit.append("0");
					} else {
						bit.append("1");
					}
				}
				codeword[codeword.length - 1] = bit.toString();
				break;
				
			}
			// two-dimensional-odd
			case 4: {
				System.out.println("Using two-dimensional-odd method");
				for (int i = 0; i < array_size; i++) {
					int sum = 0;
					StringBuilder temp = new StringBuilder(codeword[i]);
					for (int j = 0; j < temp.length(); j++) {
						if (temp.charAt(j) == '1') {
							sum++;
						}
					}
					if (sum % 2 == 0) {
						temp.append("1");
					} else {
						temp.append("0");
					}
					codeword[i] = temp.toString();
				}
				StringBuilder bit = new StringBuilder();
				for (int j = 0; j < word_size + 1; j++) {
					int sumCol = 0;
					for (int i = 0; i < array_size; i++) {
						StringBuilder temp = new StringBuilder(codeword[i]);
						if (temp.charAt(j) == '1') {
							sumCol++;
						}
					}
					if (sumCol % 2 == 0) {
						bit.append("1");
					} else {
						bit.append("0");
					}
				}
				codeword[codeword.length - 1] = bit.toString();

				break;
			}
			default:
				System.out.println("Invaid input");
				break;
		}
		return codeword;
	}

	public static int parity_check(String[] codeword, int parity_type, int size) {
		int validate = 0;
		System.out.println("checking parity bit...");
		// one-dimensional-even
		if(parity_type == 1) {
			for(int i = 0;i < size;i++) {
				StringBuilder temp = new StringBuilder(codeword[i]);
				System.out.println("-----------------------------");
				System.out.println("Row " + (i+1) + ":" + temp.toString());
				int sum = 0;
				char sum_output;
				char r_bit = temp.charAt(temp.length() - 1);
				System.out.println("R_Bit = " + r_bit);
				for(int j = 0; j < temp.length() - 1;j++) {
					if(temp.charAt(j) =='1') {
						sum++;
					}
				}
				if(sum % 2 == 0) {
					sum_output = '0';
					System.out.println("expect R_Bit: "+sum_output);
				}else
				{
					sum_output = '1';
					System.out.println("expect R_Bit: "+sum_output);
				}
				if(sum_output == r_bit) {
					System.out.println("R_bit from row "+(i+1) +" match the expect result");
					validate = 1;
				}else {
					validate = 0;
					System.out.println("-----------------------------");
					System.out.println("R_bit does not match the expect result");
					System.out.println("Return validate code. (FAIL = 0,PASS = 1)");
					System.out.println("-----------------------------");
					return validate;
				}
			}
		}
		// one-dimensional-odd
		if(parity_type == 2) {
			for(int i = 0;i < size;i++) {
				StringBuilder temp = new StringBuilder(codeword[i]);
				System.out.println("-----------------------------");
				System.out.println("Row " + (i+1) + ":" + temp.toString());
				int sum = 0;
				char sum_output;
				char r_bit = temp.charAt(temp.length() - 1);
				System.out.println("R_Bit = " + r_bit);
				for(int j = 0; j < temp.length() - 1;j++) {
					if(temp.charAt(j) =='1') {
						sum++;
					}
				}
				if(sum % 2 == 0) {
					sum_output = '1';
					System.out.println("expect R_Bit: "+sum_output);
				}else
				{
					sum_output = '0';
					System.out.println("expect R_Bit: 0");
				}
				if(sum_output == r_bit) {
					System.out.println("R_bit from row "+(i+1) +" match the expect result");
					validate = 1;
				}else {
					validate = 0;
					System.out.println("-----------------------------");
					System.out.println("R_bit does not match the expect result");
					System.out.println("Return validate code. (FAIL = 0,PASS = 1)");
					System.out.println("-----------------------------");
					return validate;
				}
			}
		}
		// two-dimensional-even
		if(parity_type == 3) {
			System.out.println("============================Check for row============================");
			for(int i = 0;i < size;i++) {
				StringBuilder temp = new StringBuilder(codeword[i]);
				System.out.println("-----------------------------");
				System.out.println("Row " + (i+1) + ":" + temp.toString());
				int sum = 0;
				char sum_output;
				char r_bit = temp.charAt(temp.length() - 1);
				System.out.println("R_Bit = " + r_bit);
				for(int j = 0; j < temp.length() - 1;j++) {
					if(temp.charAt(j) =='1') {
						sum++;
					}
				}
				if(sum % 2 == 0) {
					sum_output = '0';
					System.out.println("expect R_Bit: "+sum_output);
				}else
				{
					sum_output = '1';
					System.out.println("expect R_Bit: "+sum_output);
				}
				if(sum_output == r_bit) {
					System.out.println("R_bit from row "+(i+1) +" match the expect result");
					validate = 1;
				}else {
					validate = 0;
					System.out.println("-----------------------------");
					System.out.println("R_bit does not match the expect result");
					System.out.println("Return validate code. (FAIL = 0,PASS = 1)");
					System.out.println("-----------------------------");
					return validate;
				}
			}
			System.out.println("-----------------------------");
			System.out.println("============================Check for column============================");
			for(int i = 0; i <codeword[0].length();i++) {
				System.out.println("-----------------------------");
				System.out.println("Column " + (i+1) +": "+ codeword[codeword.length-1]);
				System.out.println("Watch the codeword base on the index of the column");
				int sumCol = 0;
				char sumCol_output;
				char r_bitCol = codeword[codeword.length -1].charAt(i);
				System.out.println("R_Bit Column = "+r_bitCol);
				for(int j = 0; j < size - 1;j++) {
					StringBuilder temp = new StringBuilder(codeword[j]);
					if(temp.charAt(i) == '1') {
						sumCol++;
					}
				}
				if(sumCol %2 == 0) {
					sumCol_output = '0';
					System.out.println("expect R_Bit: "+sumCol_output);
				}else {
					sumCol_output = '1';
					System.out.println("expect R_Bit: "+sumCol_output);
				}
				if(sumCol_output == r_bitCol) {
					validate = 1;
				}
				else {
					validate = 0;
				}
			}
		}
		// two-dimensional-odd
		if(parity_type == 4) {
			System.out.println("============================Check for row============================");
			for(int i = 0;i < size;i++) {
				StringBuilder temp = new StringBuilder(codeword[i]);
				System.out.println("-----------------------------");
				System.out.println("Row " + (i+1) + ":" + temp.toString());
				int sum = 0;
				char sum_output;
				char r_bit = temp.charAt(temp.length() - 1);
				System.out.println("R_Bit = " + r_bit);
				for(int j = 0; j < temp.length() - 1;j++) {
					if(temp.charAt(j) =='1') {
						sum++;
					}
				}
				if(sum % 2 == 0) {
					sum_output = '1';
					System.out.println("expect R_Bit: "+sum_output);
				}else
				{
					sum_output = '0';
					System.out.println("expect R_Bit: "+sum_output);
				}
				if(sum_output == r_bit) {
					System.out.println("R_bit from row "+(i+1) +" match the expect result");
					validate = 1;
				}else {
					validate = 0;
					System.out.println("-----------------------------");
					System.out.println("R_bit does not match the expect result");
					System.out.println("Return validate code. (FAIL = 0,PASS = 1)");
					System.out.println("-----------------------------");
					return validate;
				}
			}
			System.out.println("-----------------------------");
			System.out.println("============================Check for column============================");
			for(int i = 0; i <codeword[0].length();i++) {
				System.out.println("-----------------------------");
				System.out.println("Column " + (i+1) +": "+ codeword[codeword.length-1]);
				System.out.println("Watch the codeword base on the index of the column");
				int sumCol = 0;
				char sumCol_output;
				char r_bitCol = codeword[codeword.length -1].charAt(i);
				System.out.println("R_Bit Column = "+r_bitCol);
				for(int j = 0; j < size - 1;j++) {
					StringBuilder temp = new StringBuilder(codeword[j]);
					if(temp.charAt(i) == '1') {
						sumCol++;
					}
				}
				if(sumCol %2 == 0) {
					sumCol_output = '1';
					System.out.println("expect R_Bit: "+sumCol_output);
				}else {
					sumCol_output = '0';
					System.out.println("expect R_Bit: "+sumCol_output);
				}
				if(sumCol_output == r_bitCol) {
					validate = 1;
				}
				else {
					validate = 0;
				}
			}
		}
		System.out.println("-----------------------------");
		System.out.println("All the row match the expect result.");
		System.out.println("Return validate code. (FAIL = 0,PASS = 1)");
		System.out.println("-----------------------------");
		return validate;
	}

	public static void main(String[] args) {
		//attempt 1
		String[] datawords_1 = new String[] { "10110011", "10101011", "01011010","11010101" };
		System.out.println("Attempt #1");
		System.out.println("input datawords");
		System.out.println("-----------------------------");
		for (int i = 0; i < datawords_1.length; i++) {
			System.out.println(datawords_1[i].toString());
		}
		System.out.println("-----------------------------");
		String[] codeword_1 = new String[datawords_1.length];
		codeword_1 = parity_gen(datawords_1, 8, 1, datawords_1.length);
		System.out.println("Result of codeword");
		System.out.println("-----------------------------");
		for (int i = 0; i < codeword_1.length; i++) {
			System.out.println(codeword_1[i].toString());
		}
		System.out.println("-----------------------------");
		System.out.println("Validate code: "+parity_check(codeword_1,1,4));
		System.out.println("-----------------------------");
		
		//attempt 2
		String[] datawords_2 = new String[] { "10110011", "10101011", "01011010","11010101" };
		System.out.println("Attempt #2");
		System.out.println("input datawords");
		System.out.println("-----------------------------");
		for (int i = 0; i < datawords_2.length; i++) {
			System.out.println(datawords_2[i].toString());
		}
		System.out.println("-----------------------------");
		String[] codeword_2 = new String[datawords_2.length];
		codeword_2 = parity_gen(datawords_2, 8, 2, datawords_2.length);
		System.out.println("Result of codeword");
		System.out.println("-----------------------------");
		for (int i = 0; i < codeword_2.length; i++) {
			System.out.println(codeword_2[i].toString());
		}
		System.out.println("-----------------------------");
		System.out.println("Validate code: "+parity_check(codeword_2,2,4));
		System.out.println("-----------------------------");
		
		//attempt 3
		String[] datawords_3 = new String[] { "10110011", "10101011", "01011010","11010101" };
		System.out.println("Attempt #3");
		System.out.println("input datawords");
		System.out.println("-----------------------------");
		for (int i = 0; i < datawords_3.length; i++) {
			System.out.println(datawords_3[i].toString());
		}
		System.out.println("-----------------------------");
		String[] codeword_3 = new String[datawords_3.length + 1];
		codeword_3 = parity_gen(datawords_3, 8, 3, datawords_3.length);
		System.out.println("Result of codeword");
		System.out.println("-----------------------------");
		for (int i = 0; i < codeword_3.length; i++) {
			System.out.println(codeword_3[i].toString());
		}
		System.out.println("-----------------------------");
		System.out.println("Validate code: "+parity_check(codeword_3,3,5));
		System.out.println("-----------------------------");
		
		//attempt 4
		String[] datawords_4 = new String[] { "10110011", "10101011", "01011010","11010101" };
		System.out.println("Attempt #4");
		System.out.println("input datawords");
		System.out.println("-----------------------------");
		for (int i = 0; i < datawords_4.length; i++) {
			System.out.println(datawords_4[i].toString());
		}
		System.out.println("-----------------------------");
		String[] codeword_4 = new String[datawords_4.length + 1];
		codeword_4 = parity_gen(datawords_4, 8, 4, datawords_4.length);
		System.out.println("Result of codeword");
		System.out.println("-----------------------------");
		for (int i = 0; i < codeword_4.length; i++) {
			System.out.println(codeword_4[i].toString());
		}
		System.out.println("-----------------------------");
		System.out.println("Validate code: "+parity_check(codeword_4,4,5));
		System.out.println("-----------------------------");
	}
}
