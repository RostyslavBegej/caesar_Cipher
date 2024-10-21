package org.example.crypto;

public interface Cypher<KeyType> {

    String encrypt(String input, KeyType key);

    String decrypt(String input, KeyType key);

    String bruteForce(String input);

}
