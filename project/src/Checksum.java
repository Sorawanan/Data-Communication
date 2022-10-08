import java.util.Scanner;


public class Checksum{
    //One's complement of sum
    static String  One_complement(String sum){
        StringBuilder temp = new StringBuilder(sum);
        for(int i=0;i<temp.length();i++){
            if(temp.charAt(i) == '0'){
                temp.setCharAt(i, '1');
            }
            else {
                temp.setCharAt(i, '0');
            }
        }
        sum = temp.toString();
        System.out.println("complement:"+sum);
        return sum;
    }


    static String Checksum_gen(int[] dataword, int[] num_blocks){
        int data[]=new int[dataword.length];
        // Check data size is divisible by num_blocks
        if(data.length%num_blocks.length!=0){
            int size =num_blocks.length-(data.length%num_blocks.length);
            for (int i=0;i<size;i++){
                data[i]= Integer.parseInt("0"+data.length);
                System.out.println("");
            }
        }
        // Binary addition of all blocks with carry
        String result="";
        for (int i=0;i<num_blocks.length;i++){
            result+=dataword[i];
        }
        // calculate in block
        for (int i=num_blocks.length;i< data.length ;i+= num_blocks.length){
            String next_blcks ="";
            for (int j=i;j<i+ num_blocks.length;j++){
                next_blcks+=dataword[j];
            }
            String additions="";
            int sum=0;
            int carry=0;
            //Loop to calculate the binary addition
            for(int a=num_blocks.length-1;a>=0;a--){
                sum = sum + ((Character.getNumericValue(next_blcks.charAt(a))) + (Character.getNumericValue(result.charAt(a))));
                carry=sum/2;
                if(sum==0){
                    additions='0'+additions;
                    sum=carry;
                }
                else if(sum==1){
                    additions='1'+additions;
                    sum=carry;
                }
                else if(sum==2){
                    additions='0'+additions;
                    sum=carry;
                }
                else {
                    additions='1'+additions;
                    sum=carry;
                }

            }
            // if carry is 1 then  binary addition
            String last="";
            if(carry==1){
                for(int c=additions.length()-1;c>=0;c--){
                    if(carry==0){
                        last=(Character.getNumericValue(additions.charAt(c)))+last;
                    }
                    else if((Character.getNumericValue(additions.charAt(c)))%2==0){
                        last="0"+last;
                        carry=1;
                    }
                    else {
                        last="1"+last;
                        carry=0;
                    }
                }
                result=last;
            }
            else {
                result=additions;

            }
        }
        System.out.println("result sum :"+result);
        return One_complement(result);
    }

    static void check(int[] sent_message, int[] block_size) {
        String check_sent =Checksum_gen(sent_message,block_size);
        for (int i = 0; i < check_sent.length(); i++) {
            if(check_sent!="0"){
                System.out.println("FAIL=0 : Corrupted data received");
                return;
            }
        }
        System.out.println("\nPASS=1 : Data received without any error");
    }
//
//    static void check(int[] sent_message,int[] receiver_message, int[] block_sizee) {
//        String check_sent =Checksum_gen(sent_message,block_sizee);
//        StringBuilder temp = new StringBuilder();
//        for(int i = 0; i < receiver_message.length;i++) {
//            if(receiver_message[i] == 0) {
//                temp.append('0');
//            }else {
//                temp.append('1');
//            }
//        }
//        for(int i = 0; i < sent_message.length;i++) {
//            if(sent_message[i] == 0) {
//                temp.append('0');
//            }else {
//                temp.append('1');
//            }
//        }
//        int[] check_receive = new int[temp.length()];
//        for(int i = 0; i < temp.length();i++) {
//            check_receive[i] = Character.getNumericValue(temp.charAt(i));
//        }
//        String test = Checksum_gen(check_receive, block_sizee);
//        for (int i = 0; i < test.length(); i++) {
//            if(check_sent!="0"){
//                System.out.println("FAIL=0 : Corrupted data received");
//                return;
//            }
//        }
//        System.out.println("\nPASS=1 : Data received without any error");
//    }


    public static void main(String args[]){
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

        Scanner scanSize = new Scanner(System.in);
        System.out.print("please enter size blocks : ");
        int  size_blocks=scanSize.nextInt();
        int num_blocks[]=new int[size_blocks];
        check(dataword,num_blocks);

//        Scanner scan_reciever = new Scanner(System.in);
//        System.out.print("please enter reciever bits in the array : ");
//        String word_size_r;
//        word_size_r=scan_reciever.nextLine();
//        int reciever[]=new int[word_size_r.length()];
//        for (int i=0;i<word_size_r.length();i++){
//            if(word_size_r.length()>=5){
//                reciever[i]=Integer.parseInt(word_size_r.charAt(i)+"");
//            }
//        }
//        check(dataword,reciever,num_blocks);

    }
}