import java.util.Scanner;

public class hamming_code {
	static String str = null;
	static int[] dataword = new int[256];
	static int dataword_size;
	static int[] codeword = new int[256];
	static int codeword_size;
	static int NumRbit;
	public static void printArray(int[] array) {
		for(int i = 0; i < codeword_size;i++) {
			System.out.print(array[i]);
		}
		System.out.println();
	}
	public static int Hamming_cal(int pos, int size) {
		int count = 0;
		int i = pos - 1;
		while(i < size) {
			for(int j = i; j < i+pos;j++) {
				if(codeword[j] == 1) {
					count++;
				}
			}
			i = i+(2*pos);
		}
		if(count %2 == 0) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public static void Hamming_gen(int[] dataword) {
		System.out.println("Insert your dataword into codeword array base on corresponding redundancy bit position");
		System.out.println("----------------------------------------");
		int i = 0;
		while(dataword_size > (int)Math.pow(2, i)-(i+1)) {
			NumRbit++;
			i++;
		}
		codeword_size = NumRbit + dataword_size;
		int x = 0;
		int y = 0;
		for(i = 0; i < codeword_size;i++) {
			if(i == (int)Math.pow(2,x)-1) {
				codeword[i] = 0;
				x++;
			}else {
				codeword[i] = dataword[y];
				y++;
			}
			System.out.print("Index "+i+": ");
			printArray(codeword);
		}
		System.out.println("----------------------------------------");
		System.out.print("result after insert 0: ");
		printArray(codeword);
		System.out.println("----------------------------------------");
		System.out.println("Calculate the redundancy bit using even-parity bit checking base on corresponding redundancy bit position");
		System.out.println("----------------------------------------");
		for(i = 0; i < NumRbit; i++) {
			int pos = (int)Math.pow(2, i);
			System.out.print("Find R"+pos+": ");
			int val = Hamming_cal(pos,codeword_size);
			codeword[pos-1] = val;
			printArray(codeword);
		}
	}
	public static int Hamming_check(int[] codeword) {
		System.out.println("Checking for correctness");
		System.out.println("Every redundancy bit position must be divide evenly");
		int pos_error = 0;
		System.out.println("----------------------------------------");
		for(int i = 0; i <NumRbit; i++) {
			int pos = (int)Math.pow(2,i);
			int val = Hamming_cal(pos,codeword_size);
			System.out.println("R"+pos+" quotient result: "+val);
			if(val != 0) {
				pos_error += pos;
			}
		}
		System.out.println("----------------------------------------");
		if(pos_error == 0) {
			System.out.println("Every redundancy bit position divide evenly");
			System.out.println("Your codeword correct (PASS)");
			System.out.println("Return validate code (PASS = -1,FAIL = position of Error)");
			return -1;
		}else {
			System.out.println("Your codeword incorrect! (FAIL)");
			System.out.println("Return validate code (PASS = -1,FAIL = position of Error)");
			return pos_error;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		do {
			System.out.print("Please input dataword:");
			str = in.nextLine();
			for(int i = 0; i< str.length();i++) {
				if(str.charAt(i) == '0' || str.charAt(i) == '1') {
					flag = true;
				}else {
					flag = false;
					System.out.println("Plase input the correct dataword! (only 0 and 1)");
					break;
				}
			}
		}while(flag == false);
		System.out.println("Your input of dataword: "+str);
		System.out.println("----------------------------------------");
		dataword_size = str.length();
		for(int i = 0; i < dataword_size;i++) {
			dataword[i] = Character.getNumericValue(str.charAt(i));
		}
		Hamming_gen(dataword);
		System.out.println("----------------------------------------");
		System.out.print("Your codeword result: ");
		for(int i=0; i< codeword_size;i++) {
			System.out.print(codeword[i]);
		}
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("Validate Code: "+Hamming_check(codeword));
		System.out.println("----------------------------------------");
	}
}
