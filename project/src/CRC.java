import java.util.Scanner;

public class CRC {

    //method to get CRC
    static int[] CRC_genModulo_2division(int inputdataword[], int divisor[]) {
        int countXOR[] = new int[divisor.length];
        int dataword[] = new int[inputdataword.length + divisor.length];
        //copying data into countXOR and dataword
        System.arraycopy(inputdataword, 0, dataword, 0, inputdataword.length);
        System.arraycopy(dataword, 0, countXOR, 0, divisor.length);
        int i;
        for (i = 0; i<inputdataword.length; i++) {
            System.out.print("\n["+(i+1) + "] First data bit is : " + countXOR[0]);
            System.out.print("\nRemainder bit :");
            if(countXOR[0]==1) {
                int j;
                // have to xor the remainder bits with divisor bits
                for (j=1;j<divisor.length;j++) {
                    countXOR[j-1]=xor(countXOR[j],divisor[j]);
                    System.out.print(countXOR[j-1]);
                }
            }
            else{
                int j;
                //have to xor the remainder bits with 0
                for (j=1; j < divisor.length; j++) {
                    countXOR[j - 1] = xor(countXOR[j], 0);
                    System.out.print(countXOR[j-1]);
                }
            }
            //The data will be used to get the last bit of the remainder.
            countXOR[divisor.length - 1] = dataword[i + divisor.length];
            System.out.print(countXOR[divisor.length - 1]);
        }
        return countXOR;
    }

    //method to perform xor data
    static int xor(int a, int b) {
        //returns the xor of two bits
        if (a == b) {
            return 0;
        }
        return 1;
    }

    //result of received
    static void CRC_checkReceived(int dataword[], int divisor[]) {
        int remainder[] = CRC_genModulo_2division(dataword, divisor);
        int i = 0;
        for (i = 0; i < remainder.length; i++) {
            // if the remainder is not equal to zero
            if (remainder[i] != 0) {
                System.out.println("FAIL=0 : Corrupted data received");
                return;
            }
        }
        System.out.println("\nPASS=1 : Data received without any error");

    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.print("please enter dataword bits in the array : ");
        String word_size;
        word_size=scan.nextLine();
        int dataword[]=new int[word_size.length()];
        for (int i=0;i<word_size.length();i++){
            if(word_size.length()>=5){
                dataword[i]=Integer.parseInt(word_size.charAt(i)+"");
            }
        }
        System.out.println("__________________________________________________________");
        System.out.println("CRC Divisor Standards");
        System.out.println("CRC-8 >>> 100000111");
        System.out.println("CRC-16 >>> 10001000000100001");
        System.out.println("CRC-32 >>> 100000100110000010001110110110111");
        Scanner scanCRC_type = new Scanner(System.in);
        System.out.print("please enter bits of divisor : ");
        String number_CRC;
        number_CRC = scanCRC_type.nextLine();
        System.out.println("__________________________________________________________");
        int divisor[] = new int[number_CRC.length()];
        for (int i = 0; i < number_CRC.length(); i++) {
            divisor[i] = Integer.parseInt(number_CRC.charAt(i) + "");
        }

        int remainder[] = CRC_genModulo_2division(dataword, divisor);
        System.out.print("\n__________________________________________________________");
        System.out.print("\nupdate Remainder bit :");
        for (int i = 0; i < remainder.length - 1; i++) {
            System.out.print(remainder[i]);
        }
        System.out.print("\nGenerated  is: ");
        for (int i = 0; i < dataword.length; i++) {
            System.out.print(dataword[i]);
        }
        for (int i = 0; i < remainder.length - 1; i++) {
            System.out.print(remainder[i]);
        }

        Scanner generatedCRC = new Scanner(System.in);
        System.out.print("\nplease enter bits in generated CRC code to send:");
        String generated = generatedCRC.nextLine();
        int divide[]=new int [dataword.length + remainder.length - 1];
        for(int i = 0; i < dataword.length+remainder.length-1; i++) {
            divide[i] = Integer.parseInt(generated.charAt(i)+"");
        }
        CRC_checkReceived(divide, divisor);
    }
}



























