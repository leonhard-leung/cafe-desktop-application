package com.leonhard_leung.orders;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record Order(int orderID, int accountID, int productID, char size, int quantity, BigDecimal deliveryFee, BigDecimal price, Timestamp timestamp) {
} // end of Order record
