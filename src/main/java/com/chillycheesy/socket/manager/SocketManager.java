package com.chillycheesy.socket.manager;

import com.chillycheesy.modulo.modules.Module;
import com.chillycheesy.modulo.modules.ModuloEntity;
import com.chillycheesy.modulo.utils.Manager;
import com.chillycheesy.socket.ModuloSocket;

import java.util.ArrayList;
import java.util.List;

public class SocketManager extends Manager<ModuloSocket> implements ModuloEntity {

    private Module module;
    private List<Thread> threads;

    @Override
    public void load(Module module) {
        threads = new ArrayList<>();
        this.module = module;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

}
