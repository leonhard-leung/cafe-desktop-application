package com.leonhard_leung.network;

import com.leonhard_leung.controller.ServerController;
import com.leonhard_leung.model.ServerModel;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final int PORT = 3000;
    private static final int BROADCAST_PORT = 5432;
    private static final int THREAD_POOL_SIZE = 20;
    private static final ServerModel model = new ServerModel();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        // start the server
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // broadcast server to connected devices in the network
            broadcastServer();

            while (true) {
                // connect clients to the server
                Socket client = serverSocket.accept();

                // submit the runnable task to the executor service
                executorService.submit(() -> handleClient(client));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    } // end of main

    /**
     * Handles all client communication to the server
     * @param client the socket of the client that is connected to the server
     */
    private static void handleClient(Socket client) {
        ServerController controller = new ServerController(model);

        try (
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())
        ) {
            while (true) {
                try {
                    // read the request from the client
                    Object request = in.readObject();

                    // process the request and send the response back to the client if request is not null
                    if (request != null) {
                        Object response = controller.processRequest(request);
                        out.writeObject(response);
                        out.flush();
                        out.reset();
                    }
                } catch (EOFException eofException) {
                    // stop the loop if exception is caught
                    System.out.println("Client has closed the connection");
                    break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    } // end of handleClient

    /**
     * Broadcasts the server's presence across all devices connected in the local area network every 30 seconds
     */
    private static void broadcastServer() {
        scheduler.scheduleAtFixedRate(() -> {
            try (DatagramSocket broadcastSocket = new DatagramSocket()){
                System.out.println("Server broadcasting its presence on port " + BROADCAST_PORT);

                broadcastSocket.setBroadcast(true);
                String serverIP = "SERVER_ADDRESS:" + InetAddress.getLocalHost().getHostAddress();

                byte[] buffer = serverIP.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), BROADCAST_PORT);

                broadcastSocket.send(packet);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }, 0, 30, TimeUnit.SECONDS);
    } // end of broadcastServer
} // end of Server class
