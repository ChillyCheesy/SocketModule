package com.chillycheesy.socket;

import com.chillycheesy.modulo.ModuloAPI;
import com.chillycheesy.modulo.modules.Module;
import com.chillycheesy.modulo.modules.ModuleManager;
import com.chillycheesy.modulo.modules.ModuloEntity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Open a socket server who listens to a port and for each connection it creates a new thread.
 *
 * @author chillycheesy
 */
public class ModuloSocketServer implements ModuloEntity {

    private Module module;
    private final int port;
    private final SocketScanner<?> socketScanner;
    private final List<Thread> clients;

    public ModuloSocketServer(int port, SocketScanner<?> socketScanner) {
        this.port = port;
        this.socketScanner = socketScanner;
        this.clients = new ArrayList<>();
    }

    @Override
    public void load(Module module) {
        this.module = module;
    }

    @Override
    public void start() {
        try {
            final ModuleManager moduleManager = ModuloAPI.getModule().getModuleManager();
            final ServerSocket serverSocket = new ServerSocket(port);
            while (moduleManager.containsModule(module)) {
                final Socket socket = serverSocket.accept();
                final ModuloSocket moduloSocket = new ModuloSocket(socket, socketScanner);
                final Thread thread = new Thread(moduloSocket);
                thread.start();
                clients.add(thread);
            }
            serverSocket.close();
        } catch (IOException e) {
            module.error("Error while starting the server");
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        for (Thread client : clients) {
            client.interrupt();
        }
    }
}
