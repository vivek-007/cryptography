package railfence_cipher;
import java.util.Scanner;
public class RailFence {

	public static void main(String[] args) {
		String plain = "", cipher = ""; int key;
        Scanner in = new Scanner(System.in);
        System.out.print("Key: ");
        key = in.nextInt(); if(key < 2) System.exit(1);
        in.nextLine();
        System.out.print("Plain Text: ");
        plain = in.nextLine();
        StringBuilder rail[] = new StringBuilder[key];
        for(int i = 0; i < key; ++i)
            rail[i] = new StringBuilder();
        int count = 0; 
        for(int i = 0; i < plain.length();) {
        	if(count == -1) count = 1;
                while ((count < key) && (i < plain.length())) {
                    rail[count++].append(plain.charAt(i++));
                }
            	count -= 2;
            	while((count >= 0) && (i < plain.length())) {
            		rail[count--].append(plain.charAt(i++));
            	}
        }
        for(int i = 0; i < key; ++i)
        	cipher += rail[i].toString();
        System.out.println("Encryption ... \nCipher Text: " + cipher + "\nDecyption ..."); 
        plain=""; StringBuilder plain_text = new StringBuilder(cipher);
        char rail_dec[][] = new char[key][cipher.length()];
        for(int i = 0; i < key; ++i)
        	for(int j = 0; j < cipher.length(); ++j)
        		rail_dec[i][j] = '-';
        int row = 0, pos = 0;
        while(row <= (key - 1)) {
        	int i = 0, j = 0;
        	while(j < cipher.length()) {
        		while((i >= 0) && (i < key) && (j < cipher.length())) {
        			if(i == row) { rail_dec[i][j] = cipher.charAt(pos++); 
        							plain_text.replace(j,j+1, String.valueOf(rail_dec[i][j])); }
        			++i; ++j;
        		}
        		i -= 2;
        		while((i >= 0) && (j < cipher.length())) {
        			if(i == row) { rail_dec[i][j] = cipher.charAt(pos++); 
        							plain_text.replace(j,j+1, String.valueOf(rail_dec[i][j]));}
        			--i; ++j;
        		}
        		i += 2;
        	}
        	++row;
        }
        for(int i = 0; i < key; ++i) {
        	for (int j = 0; j < cipher.length(); ++j)
        		System.out.print("| " + rail_dec[i][j] + " ");
        	System.out.println("");
        }
        System.out.print("Plain Text: " + plain_text.toString());
        in.close();
	}
}