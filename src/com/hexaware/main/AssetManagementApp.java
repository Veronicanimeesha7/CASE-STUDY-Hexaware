package com.hexaware.main;

import com.hexaware.dao.AssetManagementService;
import com.hexaware.dao.AssetManagementServiceImpl;
import com.hexaware.entity.Asset;

import java.util.Scanner;

public class AssetManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AssetManagementService service = new AssetManagementServiceImpl();
        int choice;

        do {
            System.out.println("\n==== Digital Asset Management System ====");
            System.out.println("1. Add Asset");
            System.out.println("2. Update Asset");
            System.out.println("3. Delete Asset");
            System.out.println("4. Allocate Asset");
            System.out.println("5. Deallocate Asset");
            System.out.println("6. Perform Maintenance");
            System.out.println("7. Reserve Asset");
            System.out.println("8. Withdraw Reservation");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Asset ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Type: ");
                    String type = sc.nextLine();
                    System.out.print("Serial Number: ");
                    String serial = sc.nextLine();
                    System.out.print("Purchase Date (yyyy-mm-dd): ");
                    String purchase = sc.nextLine();
                    System.out.print("Location: ");
                    String loc = sc.nextLine();
                    System.out.print("Status: ");
                    String status = sc.nextLine();
                    System.out.print("Owner ID: ");
                    int owner = sc.nextInt();
                    Asset asset = new Asset(id, name, type, serial, purchase, loc, status, owner);
                    System.out.println(service.addAsset(asset) ? "Asset added successfully." : "Failed to add asset.");
                    break;

                case 2:
                    System.out.print("Asset ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("New Type: ");
                    String utype = sc.nextLine();
                    System.out.print("New Serial Number: ");
                    String userial = sc.nextLine();
                    System.out.print("New Purchase Date: ");
                    String upurchase = sc.nextLine();
                    System.out.print("New Location: ");
                    String uloc = sc.nextLine();
                    System.out.print("New Status: ");
                    String ustatus = sc.nextLine();
                    System.out.print("New Owner ID: ");
                    int uowner = sc.nextInt();
                    Asset updatedAsset = new Asset(uid, uname, utype, userial, upurchase, uloc, ustatus, uowner);
                    System.out.println(service.updateAsset(updatedAsset) ? "Asset updated." : "Update failed.");
                    break;

                case 3:
                    System.out.print("Enter Asset ID to delete: ");
                    int did = sc.nextInt();
                    System.out.println(service.deleteAsset(did) ? "Asset deleted." : "Deletion failed.");
                    break;

                case 4:
                    System.out.print("Asset ID: ");
                    int aid = sc.nextInt();
                    System.out.print("Employee ID: ");
                    int empId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Allocation Date (yyyy-mm-dd): ");
                    String adate = sc.nextLine();
                    System.out.println(service.allocateAsset(aid, empId, adate) ? "Asset allocated." : "Allocation failed.");
                    break;

                case 5:
                    System.out.print("Asset ID: ");
                    int daid = sc.nextInt();
                    System.out.print("Employee ID: ");
                    int dempId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Return Date (yyyy-mm-dd): ");
                    String rdate = sc.nextLine();
                    System.out.println(service.deallocateAsset(daid, dempId, rdate) ? "Asset deallocated." : "Deallocation failed.");
                    break;

                case 6:
                    System.out.print("Asset ID: ");
                    int mid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Maintenance Date (yyyy-mm-dd): ");
                    String mdate = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Cost: ");
                    double cost = sc.nextDouble();
                    System.out.println(service.performMaintenance(mid, mdate, desc, cost) ? "Maintenance recorded." : "Maintenance failed.");
                    break;

                case 7:
                    System.out.print("Asset ID: ");
                    int rsid = sc.nextInt();
                    System.out.print("Employee ID: ");
                    int remp = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Reservation Date (yyyy-mm-dd): ");
                    String rsv = sc.nextLine();
                    System.out.print("Start Date (yyyy-mm-dd): ");
                    String sdate = sc.nextLine();
                    System.out.print("End Date (yyyy-mm-dd): ");
                    String edate = sc.nextLine();
                    System.out.println(service.reserveAsset(rsid, remp, rsv, sdate, edate) ? "Reserved successfully." : "Reservation failed.");
                    break;

                case 8:
                    System.out.print("Reservation ID to cancel: ");
                    int resId = sc.nextInt();
                    System.out.println(service.withdrawReservation(resId) ? "Reservation cancelled." : "Cancellation failed.");
                    break;

                case 9:
                    System.out.println("Exiting the system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 9);

        sc.close();
    }
}

