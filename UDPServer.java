
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_udp;

import java.io.*;
import java.net.*;

/**
 *
 * @author Cartaginesi Thomas
 */
public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket serverSocket = null;
        boolean attivo = true;
        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT;

        try {
            serverSocket = new DatagramSocket(6789);
            System.out.println("Server avviato");

            while (attivo) {
                DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
                serverSocket.receive(receivePacket);

                String ricevuto = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Ricevuto: " + ricevuto);

                InetAddress IPClient = receivePacket.getAddress();
                int portaClient = receivePacket.getPort();

                String daSpedire = ricevuto.toUpperCase();
                bufferOUT = daSpedire.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPClient, portaClient);
                serverSocket.send(sendPacket);

                if (ricevuto.equals("fine")) {
                    System.out.println("Server in chiusura");
                    attivo = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
