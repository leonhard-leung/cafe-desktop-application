package com.leonhard_leung.accounts;

public record Account(int accountID, String username, String name, String password, String email, byte[] profilePicture) {
} // end of Account record
