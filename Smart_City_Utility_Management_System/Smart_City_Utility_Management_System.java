import java.util.Scanner;

public class Smart_City_Utility_Management_System {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        String name = "";
        int houseNo = 0;
        int electricityUnits = 0;
        int waterLiters = 0;
        int gasKg = 0;

        double electricityBill = 0;
        double waterBill = 0;
        double gasBill = 0;
        double totalBill = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Enter Consumer Details");
            System.out.println("2. Calculate Electricity Bill");
            System.out.println("3. Calculate Water Bill");
            System.out.println("4. Calculate Gas Bill");
            System.out.println("5. Show Total Bill");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine(); // buffer clear

                    System.out.print("Enter Name: ");
                    name = sc.nextLine();

                    System.out.print("Enter House No: ");
                    houseNo = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Electricity Units: ");
                    electricityUnits = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Water Liters: ");
                    waterLiters = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Gas Kg: ");
                    gasKg = Integer.parseInt(sc.nextLine());

                    break;

                case 2:
                    if (electricityUnits <= 100)
                        electricityBill = electricityUnits * 5;
                    else if (electricityUnits <= 300)
                        electricityBill = electricityUnits * 8;
                    else
                        electricityBill = electricityUnits * 10;

                    System.out.println("Electricity Bill = " + electricityBill);
                    break;

                case 3:
                    if (waterLiters <= 500)
                        waterBill = waterLiters * 2;
                    else if (waterLiters <= 1000)
                        waterBill = waterLiters * 5;
                    else
                        waterBill = waterLiters * 8;

                    System.out.println("Water Bill = " + waterBill);
                    break;

                case 4:
                    gasBill = gasKg * 50;
                    System.out.println("Gas Bill = " + gasBill);
                    break;

                case 5:
                    totalBill = electricityBill + waterBill + gasBill;
                    System.out.println("\n--- BILL SUMMARY ---");
                    System.out.println("Name: " + name);
                    System.out.println("House No: " + houseNo);
                    System.out.println("Total Bill: " + totalBill);
                    break;

                case 6:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 6);

        sc.close();
    }
}
