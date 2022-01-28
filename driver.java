package Restaurant;
import java.util.*;
public class driver {

	public static void main(String[] args) {
		home();
	}
	static void home()						// Displays Home Page
	{
        bill b = new bill();
        
		int option;
		System.out.print("******************** Dubakoor Hotel ********************\n\n");
		System.out.print("\t\t\t Main menu\n\n");
		System.out.print("\t\t\t 1. Create Bill\n");
		System.out.print("\t\t\t 2. View Bill\n");
		System.out.print("\t\t\t 3. View Menu\n");
		System.out.print("\t\t\t 4. Edit Bill\n");
		System.out.print("\t\t\t 5. Exit\n\n");
		System.out.print("Choose an option : ");
		Scanner s = new Scanner(System.in);
		
		option=s.nextInt();
		
		switch(option) {
		
		case 1:{
			System.out.print("\nInside Create Bill\n");
			b.createBill();
			System.out.println();									//TO create a new bill
			System.out.println();
			home();
			break;
			}
		
		case 2:{
			System.out.print("\nInside View Bill\n");
			b.viewBill();
			System.out.println();									//To view an existing bill
			System.out.println();
			home();
			break;
			}
		
		case 3:{
			System.out.print("\nInside View Menu\n");
			b.viewMenu();
			System.out.println();									//To view the menu
			System.out.println();
			home();
			break;
			}
		
		case 4:{
			System.out.print("\nInside Edit Menu\n");
			b.editMenu();
			System.out.println();									//To edit the menu
			System.out.println();
			home();
			break;
			}
		case 5:{
			System.out.print("\n\n ******************** Application Closed ********************");
			break;
			}
		}	
		
		
	}

}
