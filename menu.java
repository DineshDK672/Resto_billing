package Restaurant;
import java.io.*;
import java.util.*;
public class menu {

	public menu() {
		try {
			File f = new File(System.getProperty("user.dir")+"/Food.txt");
			if(!f.exists()) {													//Creates a new menu file if does not exist
				f.createNewFile();
			}
		}
		catch(IOException e) {
			System.out.print("Cannot create file");
			
		}
		
		
	}
	void viewMenu() {
		try {
			System.out.print("******************** Menu ********************\n\n");
			File f = new File(System.getProperty("user.dir")+"/Food.txt");
			Scanner reader = new Scanner(f);
			ArrayList<String> list =new ArrayList<String>();
			while(reader.hasNextLine()) {
				list.add(reader.nextLine());				//Adds the menu items to the array list for sorting
			}
			Collections.sort(list);							//Sorts the menu for printing
			System.out.print("\t\tDish Name - Price\n\n");
			for(int i=0;i<list.size();i++) {
				System.out.print("\t\t"+list.get(i)+"\n");
			}
			reader.close();
			System.out.print("\nTo Exit press 1 : ");		//To go back to home page
			Scanner s = new Scanner(System.in);
			int opt = s.nextInt();
			if(opt == 1) {
				return;
			}
		}
		catch(IOException e) {
			System.out.print("\nError occured while viewing menu");
			return;
		}
	}
	void editMenu() {
		try {
			String food,price,filename;
			int opt;
			boolean buff = true;
			filename = System.getProperty("user.dir")+"/Food.txt";
			Scanner s = new Scanner(System.in);
			System.out.print("Enter dish Name: ");
			food=s.nextLine();
			System.out.print("Enter dish Price: ");
			price=s.nextLine();
			BufferedWriter mywrite = new BufferedWriter(new FileWriter(filename,buff));
			mywrite.write(food+"- Rs."+price);	// Updates the entered food and price into the file
			mywrite.newLine();
			mywrite.close();
			System.out.print("Do you want to enter another value ?\n1. Yes\n2. Exit\n");
			opt=s.nextInt();
			if(opt==1) {						// Checks if user want to enter another value
				editMenu();
			}
			else if(opt==2) {					//To go back to home page
				return;
			}
			else {
				System.out.print("Invalid option");
				for(int i =0;i<2000;i++) {
				
				}
				return;
			}
		}
		catch(IOException e ) {
			System.out.print("\nError occured while editing menu");
			return;
		}
	}

}
