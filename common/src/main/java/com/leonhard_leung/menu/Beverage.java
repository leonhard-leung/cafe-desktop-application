package com.leonhard_leung.menu;

import java.math.BigDecimal;

public record Beverage(int beverageID, String category, char size, int quantity, BigDecimal price) {
} // end of Beverage Record
