package caesar;
import java.lang.String;
import java.util.Scanner;
public class caesar_cipher {

	public static void main(String[] args) throws Exception {
		String plain = "", cipher = "";
        Scanner in = new Scanner(System.in);
        System.out.print("Key: ");
        int key = in.nextInt();
        if((key > 25) || (key < -25)) key %= 26;
        in.nextLine();
        System.out.print("Plain Text: ");
        plain = in.nextLine();
        plain = plain.toUpperCase();
        //Encryption ... 
        for(int i = 0; i < plain.length(); ++i) {
            if(plain.charAt(i) == ' ') {
                cipher += ' '; continue;
            }
            char c = (char)(((int)plain.charAt(i)) + key);
            if((int)c < 65) c = (char)(90 - (65 - ((int)c)) + 1);
            if((int)c > 90) c = (char)(65 + (((int)c) - 90) -1);
            cipher += c;
        }
        System.out.println("\nEncryption \nCipher Text = " + cipher);
        
        //Decryption ...
        plain = "";
        for(int i = 0; i < cipher.length(); ++i) {
        	if(cipher.charAt(i) == ' ') {
        		plain += ' '; continue;
        	}
        	char c = (char)((int)(cipher.charAt(i)) - (key));
        	if((int)c < 65) c = (char)(90 - (65 - ((int)c)) + 1);
            if((int)c > 90) c = (char)(65 + (((int)c) - 90) -1);
        	plain += c;
        }
        System.out.println("\nDecryption \nPlain Text = " + plain);
        in.close();
	}

}
