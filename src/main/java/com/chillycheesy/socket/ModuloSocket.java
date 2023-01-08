package com.chillycheesy.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ModuloSocket<T> implements Runnable {

    private Socket socket;
    private SocketScanner<T> socketScanner;

    public ModuloSocket(Socket socket, SocketScanner<T> socketScanner) {
        this.socket = socket;
        this.socketScanner = socketScanner;
    }

    @Override
    public void run() {
        try (
            final InputStream inputStream = socket.getInputStream();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            final Scanner scanner = new Scanner(bufferedInputStream);
        ) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final T content = socketScanner.scan(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
