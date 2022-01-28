package Restaurant;
import java.io.*;
import java.util.*;
import java.text.*;
public class bill extends menu{
	public int billNum=0;
	String date;	
	String food;
	String billPath;
	int quantity,price,opt,totPrice;

	bill(){
		try {
			File fileHold = new File(System.getProperty("user.dir")+"/billNum.txt"); 	//Creating a file to store bill numbers	
			if(!fileHold.exists()) {
				fileHold.createNewFile();
			}
			Scanner reader = new  Scanner(fileHold);
			while(reader.hasNextLine()) {
				billNum=Integer.parseInt(reader.nextLine());
			}
			
			billNum++;																//Updating the bill number
			billPath=System.getProperty("user.dir")+"/"+billNum+".txt";
			
			reader.close();
			Date d = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			date = formatter.format(d);
		}
		catch(IOException e) {
			System.out.print("\nError occured at Bill number");
		}
	}
	
	public void createBill() {
		
		System.out.print("******************** New Bill ********************\n\n");
		
		boolean foodExists=false;
		Scanner s=new Scanner(System.in);
		System.out.print("\nEnter the Food: ");
		food =s.nextLine().toLowerCase();
		try {											//To check if the entered dish is in the menu
			File fObj = new File(System.getProperty("user.dir")+"/Food.txt");
			Scanner reader = new Scanner(fObj);
			while(reader.hasNextLine()) {
				String temp[]=reader.nextLine().split("- Rs.");
				//System.out.print("\n"+temp[0].toLowerCase()+" "+food+"\n");
				if(food.equals(temp[0].toLowerCase())) {			//Checks if the dish is present
					food=temp[0];
					price=Integer.parseInt(temp[1]);
					System.out.print("\n"+food+ " - Rs."+price+"\n");	//Prints the dish name with price
					reader.close();
					foodExists=true;
					break;
				}
			}
			if(!foodExists) {
				System.out.print("\nThe dish does not exist on the menu");
				System.out.print("\n1. Enter again\t\t2.Home");
				opt=s.nextInt();
				if(opt==1) {
					createBill();
				}
				else {
					return;
				}
			}
			System.out.print("\nEnter the quantity : ");		//Gets the quantity
			quantity=s.nextInt();
			totPrice=quantity*price;
			
			

			
			File obj = new File(billPath);
			
			if(obj.createNewFile()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(billPath,true));
				System.out.print("Inside if\n\n");
				System.out.print("\nBill Num: "+billNum);
				writer.write(Integer.toString(billNum));								//Writing the details to a new file under the bill number as name
				writer.newLine();									//Have to enter Emp-deets after login feature
				writer.write(date);
				writer.newLine();
				writer.close();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(billPath,true));
			writer.write(food+"-"+quantity+"-"+totPrice);
			writer.newLine();
			writer.close();
			
			
			
			
			
			System.out.print("\n1. Enter again\t\t2.Home");
			opt=s.nextInt();
			if(opt==1) {
				createBill();
			}
			else {
				BufferedWriter billWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/billNum.txt",true));
				System.out.print("\nBill Num: "+billNum);
				billWriter.write(Integer.toString(billNum));
				billWriter.newLine();								//Updating the bill Number into the file
				billWriter.close();
				return;
			}
			
		}
		catch(IOException e){
			System.out.print("\nError while creating Bill\n");
		}
		
	}
	public void viewBill() {
		int finalPrice=0;
		Scanner s = new Scanner(System.in);
		System.out.print("Last bill was: "+(billNum-1)+"\nEnter bill number: ");		//To display last bill num and get bill num
		billNum=s.nextInt();
		billPath=System.getProperty("user.dir")+"/"+billNum+".txt";
		File obj= new File(billPath);
		ArrayList<String>billList = new ArrayList<String>();						//To store the lines in bill file
		String billContents[]= new String[3];										//TO Store the contents in bill file
		System.out.print("\t\t\t     ******* Dubakoor Hotel *******\n");
		
		try {
			Scanner reader = new Scanner(obj);
			while(reader.hasNextLine()) {
				billList.add(reader.nextLine());
			}
			System.out.print("\n\t\t  Bill Number: "+billList.get(0)+"\t\t\t Date: "+billList.get(1)+"\n\n");
			System.out.print("\t\t____________________________________________________________\n");
			System.out.print("\t\tContents \t-\t Quantity \t-\t Price\n");
			System.out.print("\t\t____________________________________________________________\n\n");
			for(int i =2; i<billList.size();i++) {
				billContents=billList.get(i).split("-");
				System.out.print("\t\t"+billContents[0]);
				if(billContents[0].length()<27) {
					for(int j=billContents[0].length();j<=27;j++) {
						System.out.print(" ");
					}
				}
				System.out.print(billContents[1]+" \t\t\tRs. "+billContents[2]+"\n");
				finalPrice+=Integer.parseInt(billContents[2]);
			}
			reader.close();
			System.out.print("\t\t____________________________________________________________\n\n");
			System.out.print("\t\t\t\t\t\t\tTotal : Rs. "+finalPrice+"\n\n\n");
			System.out.print("\t\t******************** Thank You <3 ********************\n\n");
			System.out.print("\nTo Exit press 1 : ");		//To go back to home page
			int opt = s.nextInt();
			if(opt == 1) {
				return;
			}
			
		}
		catch(IOException e) {
			System.out.print("\nError while viewing bill\n");
		}
	}
}
