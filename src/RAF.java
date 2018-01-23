import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * 
 * @author Tahlor Clark
 * 1-17-18
 *COSC311
 */

public class RAF {

	static Scanner key = new Scanner(System.in);
	static Scanner textFileIn = null;
	static Student rec = new Student();
	static File file = null;
	static RandomAccessFile raFile = null;
	static String ans;
	static String first = "";
	static String last = "";
	static int ID = -1;
	static double GPA = -1;

	public static void Create(String txtfilename, String rafname) throws IOException {
		textFileIn = new Scanner(new FileInputStream(txtfilename));
		file = new File (rafname);
		if(!textFileIn.hasNext()) {
			System.out.println("No data in file.");
			return;
		}
		if (file.exists())
			file.delete();
		raFile = new RandomAccessFile(file, "rw");
		while (textFileIn.hasNext()){
			rec.readFromTextFile(textFileIn);
			rec.writeToFile(raFile);
		}
	}

	public static void Display(String fname) throws IOException {
		if (isNull(raFile) == false) {
			print(raFile, rec);
		}
	}

	public static void Retrieve(int index) throws IOException {
		try {
			if (isNull(raFile) == false) {
				raFile.seek((index)*rec.size());
				rec.readFromFile(raFile);
						if (rec.isDeleted(raFile)) {
							raFile.seek((index+1)*rec.size());
							rec.readFromFile(raFile);
						}
				System.out.println(rec.toString());
			}
		} catch (IOException e) {
			System.out.println("There was an error: " + e);
			MainMenu.Menu();
		}
	}
	
	public static void RetrieveMod(int index) throws IOException {
		try {
			if (isNull(raFile) == false) {
				raFile.seek((index)*rec.size());
				rec.readFromFile(raFile);
						if (rec.isDeleted(raFile)) {
							raFile.seek((index+1)*rec.size());
							rec.readFromFile(raFile);
						}
				System.out.println(rec.toString());
			}
		} catch (IOException e) {
			System.out.println("There was an error: " + e);
			MainMenu.Menu();
		}
		try {
			if (isNull(raFile) == false) {
				raFile.seek((index)*rec.size());
				rec.readFromFile(raFile);
						if (rec.isDeleted(raFile)) {
							raFile.seek((index+1)*rec.size());
							rec.readFromFile(raFile);
						}
			}
		} catch (IOException e) {
			System.out.println("There was an error: " + e);
			MainMenu.Menu();
		}
	}

	public static void Modify(int index) throws IOException {
		if (isNull(raFile) == false) {
			RetrieveMod(index);
			
			System.out.print("\nEdit first name? Y/N ");
			ans = key.next().toLowerCase();
			if (ans.equals("y")) {
				System.out.print("\nEnter a new first name: ");
				first = key.next();
			}
			System.out.print("\nEdit last name? Y/N ");
			ans = key.next().toLowerCase();
			if (ans.equals("y")) {
				System.out.print("\nEnter a new last name: ");
				last = key.next();
			}
			System.out.print("\nEdit ID? Y/N ");
			ans = key.next().toLowerCase();
			if (ans.equals("y")) {
				System.out.print("\nEnter a new ID: ");
				ID = key.nextInt();
			}
			System.out.print("\nEdit GPA? Y/N ");
			ans = key.next().toLowerCase();
			if (ans.equals("y")) {
				System.out.print("\nEnter a new GPA: ");
				GPA = key.nextDouble();
			}
			raFile.seek(index*rec.size());
			rec.modifyRec(raFile, first, last, ID, GPA);
			System.out.println("Record has been modified.");
		}
	}

	public static void Add() throws IOException {
		if (isNull(raFile) == false) {
			System.out.print("Enter the first name, last name, ID, and GPA: ");
			rec.readFromTextFile(key);
			raFile.seek(raFile.length());
			rec.writeToFile(raFile);
		}
	}

	public static void Delete(int index) throws IOException {
		if (isNull(raFile) == false) {
			raFile.seek((index-1)*rec.size());
			rec.delete(raFile);
		}
	}

	public static void print (RandomAccessFile raFile, Student rec) throws IOException {
		if (isNull(raFile) == false) {
			String ans;
			int index = 0;
			int counter = 0;
			try {
				while (true){
					raFile.seek(index*rec.size());
					rec.readFromFile(raFile);
						if (rec.isDeleted(raFile)) {
							index++;
							continue;
						}
					System.out.println(rec);
					counter++;
					index++;
					if (counter % 5 == 0) {
						System.out.print("Main Menu (M), Next Screen (N), Display All (A)");
						ans = key.next().toLowerCase();
						if (ans.equals("m"))
							MainMenu.Menu();
						else if (ans.equals("n"))
							continue;
						else if (ans.equals("a"))
							printAll(raFile, rec, index);
					}
				}
			}
			catch (EOFException e){

			}
		}
	}

	public static void printAll (RandomAccessFile raf, Student rec, int index) throws IOException
	{
		raf.seek(index*rec.size());
		while (true){
			rec.readFromFile(raf);
			System.out.println(rec);
		}
	}


	public static boolean isNull(RandomAccessFile raFile) throws FileNotFoundException {
		if (raFile == null) {
			System.out.println("Please go to option one to create your output file before continuing.");
			return true;
		}
		else
			return false;	
	}
}
