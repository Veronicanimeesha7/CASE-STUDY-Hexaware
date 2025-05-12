package com.hexaware.test;

import com.hexaware.dao.AssetManagementService;
import com.hexaware.dao.AssetManagementServiceImpl;
import com.hexaware.entity.Asset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssetManagementTest {

    AssetManagementService service = new AssetManagementServiceImpl();

    // 1️⃣ Asset is created successfully
    @Test
    public void testAssetCreatedSuccessfully() {
        int id = (int)(Math.random() * 10000);
        String serial = "MON" + id;

        Asset asset = new Asset(id, "Test Monitor", "Electronics", serial, "2024-05-01", "Bangalore", "available", 1);
        boolean result = service.addAsset(asset);
        Assertions.assertTrue(result, "Asset should be added successfully.");
    }

    // 2️⃣ Asset is added to maintenance successfully
    @Test
    public void testMaintenanceAddedSuccessfully() {
        int id = (int)(Math.random() * 10000);
        String serial = "TAB" + id;

        Asset asset = new Asset(id, "Tablet", "Electronics", serial, "2023-01-10", "Hyderabad", "under maintenance", 1);
        service.addAsset(asset);

        boolean result = service.performMaintenance(id, "2024-05-01", "Battery replacement", 1800.0);
        Assertions.assertTrue(result, "Maintenance should be recorded.");
    }

    // 3️⃣ Asset is reserved successfully
    @Test
    public void testAssetReservedSuccessfully() {
        int id = (int)(Math.random() * 10000);
        String serial = "KEY" + id;

        Asset asset = new Asset(id, "Keyboard", "Peripheral", serial, "2022-11-15", "Delhi", "available", 1);
        service.addAsset(asset);

        boolean result = service.reserveAsset(id, 1, "2024-05-01", "2024-05-05", "2024-05-10");
        Assertions.assertTrue(result, "Reservation should be successful.");
    }

    // 4️⃣ Exception is thrown or fails on invalid asset ID
    @Test
    public void testAssetNotFound() {
        boolean result = service.deleteAsset(999999);  // Intentionally non-existent ID
        Assertions.assertFalse(result, "Should fail to delete non-existent asset.");
    }
}
