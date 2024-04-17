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
public class UDPClient {

    public static void main(String args[]) {
        DatagramSocket clientSocket = null;
        byte[] bufferOUT;
        byte[] bufferIN = new byte[1024];

        try {
            int portaServer = 6789;
            InetAddress IPServer = InetAddress.getByName("localhost");
            clientSocket = new DatagramSocket();

            System.out.println("Client pronto, inserisci dati da inviare");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String daSpedire = input.readLine();

            if (daSpedire == null || daSpedire.isEmpty()) {
                System.out.println("Nessun dato inserito, chiusura del client.");
                return;
            }

            bufferOUT = daSpedire.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer, portaServer);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
            clientSocket.receive(receivePacket);

            String ricevuto = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Dal server: " + ricevuto);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}
