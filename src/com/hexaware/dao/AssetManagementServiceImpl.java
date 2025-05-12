package com.hexaware.dao;

import com.hexaware.entity.Asset;
import com.hexaware.util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AssetManagementServiceImpl implements AssetManagementService {

	    private Connection con;

	    public AssetManagementServiceImpl() {
	         this.con = DBConnUtil.getConnection();
	         System.out.println("Connection from constructor: " + con);
	        
	    }
	


    // a. Add Asset
    @Override
    public boolean addAsset(Asset asset) {
        try {
            String query = "INSERT INTO assets (asset_id, name, type, serial_number, purchase_date, location, status, owner_id) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, asset.getAssetId());
            ps.setString(2, asset.getName());
            ps.setString(3, asset.getType());
            ps.setString(4, asset.getSerialNumber());
            ps.setString(5, asset.getPurchaseDate());
            ps.setString(6, asset.getLocation());
            ps.setString(7, asset.getStatus());
            ps.setInt(8, asset.getOwnerId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace(); // Log error
            return false;
        }
    }

    // b. Update Asset
    @Override
    public boolean updateAsset(Asset asset) {
        try {
            String query = "UPDATE assets SET name=?, type=?, serial_number=?, purchase_date=?, location=?, status=?, owner_id=? WHERE asset_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, asset.getName());
            ps.setString(2, asset.getType());
            ps.setString(3, asset.getSerialNumber());
            ps.setString(4, asset.getPurchaseDate());
            ps.setString(5, asset.getLocation());
            ps.setString(6, asset.getStatus());
            ps.setInt(7, asset.getOwnerId());
            ps.setInt(8, asset.getAssetId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // c. Delete Asset
    @Override
    public boolean deleteAsset(int assetId) {
        try {
            String query = "DELETE FROM assets WHERE asset_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, assetId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // d. Allocate Asset
    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
        try {
            String query = "INSERT INTO asset_allocations (asset_id, employee_id, allocation_date) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, allocationDate);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // e. Deallocate Asset
    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
        try {
            String query = "UPDATE asset_allocations SET return_date = ? WHERE asset_id = ? AND employee_id = ? AND return_date IS NULL";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, returnDate);
            ps.setInt(2, assetId);
            ps.setInt(3, employeeId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // f. Perform Maintenance
    @Override
    public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
        try {
            String query = "INSERT INTO maintenance_records (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, assetId);
            ps.setString(2, maintenanceDate);
            ps.setString(3, description);
            ps.setDouble(4, cost);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // g. Reserve Asset
    @Override
    public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
        try {
            String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date, start_date, end_date, status) " +
                           "VALUES (?, ?, ?, ?, ?, 'pending')";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, reservationDate);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // h. Withdraw Reservation
    @Override
    public boolean withdrawReservation(int reservationId) {
        try {
            String query = "UPDATE reservations SET status = 'canceled' WHERE reservation_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, reservationId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
