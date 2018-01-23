import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Tahlor Clark
 * 1-17-18
 *COSC311
 */

public class MainMenu {
	static Scanner key = new Scanner(System.in);
	
	public static void main (String[]args) throws IOException {
			Menu();
	}
	
	/**
	 * main menu
	 * @throws FileNotFoundException 
	 */
	public static void Menu() throws IOException {
		System.out.println("\n1- Create a random-access file\n" + 
				"2- Display a random-access file\n" + 
				"3- Retrieve a record\n" + 
				"4- Modify a record\n" + 
				"5- Add a new record\n" + 
				"6- Delete a record\n" + 
				"7- Exit");
		System.out.print("\nWhich action would you like to perform? ");
		Option(key.nextInt());	
	}
	
	/**
	 * decide which option was selected
	 * @param keyIn
	 * @throws FileNotFoundException 
	 */
	public static void Option(int keyIn) throws IOException {
		String inStr = "";
		int inInt = 0;
		switch (keyIn) {
		case 1: 
			System.out.print("\nEnter a name for the input file: ");
			inStr = key.next();
			System.out.print("\nEnter a name for the output file: ");
			String out = key.next();
			RAF.Create(inStr, out);
			System.out.print("\nFile has been created.\n\n");
			break;
		case 2: 
			System.out.print("\nEnter the file name: ");
			inStr = key.next();
			RAF.Display(inStr);
			break;
		case 3:
			System.out.print("\nEnter the index of the record: ");
			inInt = key.nextInt()-1;
			RAF.Retrieve(inInt);
			break;
		case 4:
			System.out.print("\nEnter the index of the record you'd like to modify: ");
			inInt = key.nextInt()-1;
			RAF.Modify(inInt);
			break;
		case 5:
			RAF.Add();
			break;
		case 6:
			System.out.print("\nEnter the index of the record you'd like to delete: ");
			inInt = key.nextInt()-1;
			RAF.Delete(inInt);
			break;
		case 7:
			System.out.println("GoodBye!");
			System.exit(0);
			break;
		default: 
			System.out.print("\nPlease enter a valid option: ");
			Option(key.nextInt());
			break;
		}
//			
//		if (keyIn == 1) {
//			System.out.print("\nEnter a name for the input file: ");
//			String in = key.next();
//			System.out.print("\nEnter a name for the output file: ");
//			String out = key.next();
//			RAF.Create(in, out);
//			System.out.print("\nFile has been created.\n\n");
//		}
//		else if (keyIn == 2) {
//			System.out.print("\nEnter the file name: ");
//			String in = key.next();
//			RAF.Display(in);
//		}
//		else if (keyIn == 3) {
//			System.out.print("\nEnter the index of the record: ");
//			int in = key.nextInt()-1;
//			RAF.Retrieve(in);
//		}
//		else if (keyIn == 4) {
//			System.out.print("\nEnter the index of the record you'd like to modify: ");
//			int in = key.nextInt()-1;
//			RAF.Modify(in);
//		}
//		else if (keyIn == 5)
//			RAF.Add();
//		else if (keyIn == 6) {
//			System.out.print("\nEnter the index of the record you'd like to delete: ");
//			int in = key.nextInt()-1;
//			RAF.Delete(in);
//		}
//		else if (keyIn == 7) {
//			System.out.println("GoodBye!");
//			System.exit(0);
//		}
//		else {
//			System.out.print("\nPlease enter a valid option: ");
//			Option(key.nextInt());
//			}
		Menu();
	}
}
