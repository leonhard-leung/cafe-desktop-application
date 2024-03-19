package com.leonhard_leung.menu;

import java.math.BigDecimal;

public record Product(int productID, String name, String description, BigDecimal review, byte[] image, char type) {
} // end of Product record
