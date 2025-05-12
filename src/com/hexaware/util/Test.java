package com.hexaware.util;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        Connection conn = DBConnUtil.getConnection(); // ✅ No argument here
        if (conn != null) {
            System.out.println("✔ Connection successful!");
        } else {
            System.out.println("✘ Connection failed.");
        }
    }
}





