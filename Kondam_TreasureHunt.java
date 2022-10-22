/*
 * Siddharth Kondam 
 * This project goes through a series of vaults using a stack and its methods and prints out the vault with the most amount of gems
 */
import java.util.*;
import java.io.*;

public class Kondam_TreasureHunt {

	public Kondam_TreasureHunt(String fname) {

		Scanner in = null;	
		try {
			in = new Scanner(new File(fname));
		}catch(FileNotFoundException e) {
			System.out.println(fname + " not found");
			System.exit(-1);
		}

		Stack<Vault> theStack = new Stack<Vault>();
		Vault greatestPop = new Vault();
		Vault currentVault = new Vault(parseLine(in.nextLine()));
		theStack.push(currentVault);

		while(in.hasNext()) {
			int vaultValues [] = parseLine(in.nextLine());	
			Vault newVault = new Vault(vaultValues);

			//adds the next Vault to the top of the stack 
			if(currentVault.vaultNum<vaultValues[0]) {
				theStack.push(newVault);
				currentVault = newVault; 
			}

			else if(currentVault.vaultNum>vaultValues[0]) {

				//removes all vaults until the vault being added in is equal to the current
				while(currentVault.vaultNum!=vaultValues[0]) {
					currentVault = theStack.pop();

					//stores the greatest "popped" vault with the most gems
					if(currentVault.vaultTotal>greatestPop.vaultTotal) 
						greatestPop = currentVault;
				}
				currentVault.vaultTotal+=vaultValues[1];
			}	
			//adds the gems to the same vault
			else 
				currentVault.vaultTotal+=vaultValues[1];
		}

		Vault greatestStack = new Vault();

		//finds the greatest amount of gems in a vault in the stack
		while(!theStack.isEmpty()) {
			Vault iterate = theStack.pop();		
			if(iterate.vaultTotal>greatestStack.vaultTotal) {
				greatestStack = iterate; 
			}
		}
		//compares the greatest gems in the stack against the popped vault
		System.out.println(largest(greatestPop,greatestStack));
	}

	//returns an array of size 2
	//spot 0 is the vault number,
	//spot 1 is the amount found at that spot in the vault
	private int[] parseLine(String line) {

		String[] sep = line.split(",");
		int[] toRet = {Integer.parseInt(sep[0]),Integer.parseInt(sep[1])};

		return toRet;
	}

	private Vault largest(Vault one, Vault two) {

		if(one.vaultTotal > two.vaultTotal)
			return one;
		else
			return two;
	}

	public class Vault{
		private int vaultNum;
		private int vaultTotal;

		public Vault() {
			vaultNum = -1;
			vaultTotal = Integer.MIN_VALUE;
		}

		public Vault(Vault other) {
			vaultNum = other.vaultNum;
			vaultTotal = other.vaultTotal;
		}

		public Vault(int[] info) {
			vaultNum = info[0];
			vaultTotal = info[1];
		}

		public String toString() {
			return "Vault "+vaultNum +" has "+vaultTotal;
		}
	}

	public static void main(String[] args) {
		new Kondam_TreasureHunt("treasureList.txt");
	}
}
