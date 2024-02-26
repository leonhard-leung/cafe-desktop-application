package com.leonhard_leung;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class Client {
    private static String SERVER_ADDRESS;
    private static final int BROADCAST_PORT = 5432;
    private static final int PORT = 8080;

    public static void main( String[] args ) {
        // obtain server address
        listenForServerBroadcast();

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            System.out.println("connected on the server: " + socket.getInetAddress());

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject("Hello from the client!");
            out.flush();

            Object response = in.readObject();
            System.out.println("Server response: " + response);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    } // end of main

    private static void listenForServerBroadcast() {
        try (DatagramSocket socket = new DatagramSocket(BROADCAST_PORT)){
            while (true) {
                byte[] dataRecieved = new byte[1024];
                DatagramPacket packet = new DatagramPacket(dataRecieved, dataRecieved.length);
                socket.receive(packet);

                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                if (receivedMessage.startsWith("SERVER_ADDRESS:")) {
                    SERVER_ADDRESS = receivedMessage.split(":")[2];
                    System.out.println("Received Server IP: " + SERVER_ADDRESS);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    } // end of requestServerAddress
} // end of Client class
