package com.chillycheesy.socket.listener;

import com.chillycheesy.modulo.ModuloAPI;
import com.chillycheesy.modulo.events.EventContainer;
import com.chillycheesy.modulo.events.EventManager;
import com.chillycheesy.modulo.modules.Module;
import com.chillycheesy.modulo.modules.ModuloEntity;

public class SocketModuleListenerManager implements ModuloEntity {

    private Module module;

    @Override
    public void load(Module module) {
        this.module = module;
    }

    @Override
    public void start() {
        final EventContainer eventContainer = ModuloAPI.getEvent();
        final EventManager eventManager = eventContainer.getEventManager();
        eventManager.registerItem(module);
    }

    @Override
    public void stop() {
        final EventContainer eventContainer = ModuloAPI.getEvent();
        final EventManager eventManager = eventContainer.getEventManager();
        eventManager.removeAllItems(module);
    }
}
