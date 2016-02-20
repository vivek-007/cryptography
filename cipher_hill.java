package hill_cipher;
import java.math.BigInteger;
import java.util.Scanner;

public class cipher_hill {            

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String plain="", cipher="";
        Scanner in = new Scanner(System.in);
        System.out.print("Plain Text: ");
        plain = in.nextLine(); plain = plain.toUpperCase();
        int[][] key = new int [][] {{17,17,5},{21,18,21},{2,2,19}};
        cipher = hill_cipher(plain,key);
        System.out.println("Encryption ...\nCipher Text: " + cipher);
        int key_inv[][] = invert(key);
        plain=""; plain = hill_cipher(cipher,key_inv);
        System.out.println("Decryption ...\nPlain Text: " + plain);
        in.close();
	}
	public static String hill_cipher(String plain, int key[][]) {
		int num = 65; char c[] = new char[26]; String cipher="";
		for(int i = 0; i < 26; ++i) {
            c[i] = ((char)num++);
        }
		int part[] = new int[3]; int ans[] = {0,0,0};
        int beginIndex = 0, endIndex = 3;
        for(int z = 0; z < plain.length() / 3; ++z) {
        	String temp = plain.substring(beginIndex, endIndex);
        	for(int i  = 0; i < 3; ++i) { 
                char t = temp.charAt(i);
                part[i] = ans[i] = 0;
                for(int k = 0; k < 26; ++k) {
                    if(t == c[k]) part[i] = k;
                }
            }
        	for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j) {
                    ans[i] += key[i][j] * part[j];
                }
                ans[i] %= 26;
                cipher += c[ans[i]];
            }
        	beginIndex += 3; endIndex += 3;
        }
		return cipher;
	}
	public static int[][] invert(int a[][]) {
		int det = 0;
		// finding determinant ... 
		for(int i = 0, j = 0; j < 3; ++j) {
			if(j != 1) 
				det += (a[i][j]* minor_det(i,j,a));
			else det -= (a[i][j]*minor_det(i,j,a));
		}
		// finding modular multiplicative inverse of determinant ...
		BigInteger val1 = BigInteger.valueOf(det);
		BigInteger val2 = BigInteger.valueOf(26);
		det = Integer.parseInt((val1.modInverse(val2)).toString());
		// finding minor determinant matrix ...
		int minor[][] = new int[3][3];
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j)
				minor[i][j] = minor_det(i, j, a);
		}
		minor[0][1] *= -1; minor[1][0] *= -1; minor[1][2] *= -1; minor[2][1] *= -1;
		int final_rev_key[][] = new int[3][3];
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				if(minor[i][j] != 0) {
					minor[i][j] *= det; minor[i][j] %= 26;
					if(minor[i][j] < 0)
						minor[i][j] = (minor[i][j] + 26) % 26;
				}
				final_rev_key[j][i] = minor[i][j];
			}
		}
		return final_rev_key;
	}
	public static int minor_det(int i, int j, int a[][]) {
		int result = 0; int temp[] = new int[4];
		for(int m = 0; m < 3; ++m) {
			for(int n = 0; n < 3; ++n) {
				if((m != i) && (n != j))
					temp[result++] = a[m][n];
			}
		}
		result = (temp[0] * temp[3])-(temp[1] * temp[2]);
		return result;
	}
}