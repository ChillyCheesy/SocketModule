package com.chillycheesy.socket;

import com.chillycheesy.modulo.modules.Module;
import com.chillycheesy.socket.listener.SocketModuleListenerManager;
import com.chillycheesy.socket.manager.SocketManager;

/**
 * It is a Module for Modulo.
 * It facilitates the management of communications by a Sockets protocol.
 *
 * @author chillycheesy
 */
public class SocketModule extends Module {

    public static SocketModule instance;

    private SocketManager socketManager;
    private SocketModuleListenerManager socketModuleListenerManager;

    @Override
    protected void onLoad() {
        instance = this;
        socketManager = new SocketManager();
        socketModuleListenerManager = new SocketModuleListenerManager();
        socketModuleListenerManager.load(this);
        socketManager.load(this);
    }

    @Override
    protected void onStart() {
        socketModuleListenerManager.start();
        socketManager.start();
    }

    @Override
    protected void onStop() {
        socketModuleListenerManager.stop();
        socketManager.stop();
    }

    public SocketManager getSocketManager() {
        return socketManager;
    }

}
