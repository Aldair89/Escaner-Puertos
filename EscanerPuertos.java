
package com.mycompany.escanerpuertos;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EscanerPuertos {
    public static void main(String[] args) {
        String ip = obtenerDireccionIP();

        if (ip == null) {
            System.out.println("No se pudo obtener la dirección IP de tu PC.");
            return;
        }

        System.out.println("Escaneando puertos abiertos en la dirección IP: " + ip);
        System.out.println("Puertos abiertos:");

        // Define el rango de puertos a escanear (del puerto 1 al 65535)
        int puertoInicial = 1;
        int puertoFinal = 65535;

        for (int puerto = puertoInicial; puerto <= puertoFinal; puerto++) {
            if (isPuertoAbierto(ip, puerto)) {
                System.out.println("Puerto " + puerto + " abierto");
            }
        }
    }

    private static String obtenerDireccionIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isPuertoAbierto(String ip, int puerto) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, puerto), 1000);
            socket.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
