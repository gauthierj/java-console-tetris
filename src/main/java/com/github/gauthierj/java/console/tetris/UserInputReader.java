package com.github.gauthierj.java.console.tetris;

import com.github.gauthierj.java.console.tetris.util.InitializableAndDestroyable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class UserInputReader extends InitializableAndDestroyable {

    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicReference<List<Character>> input = new AtomicReference<>(new ArrayList<>());

    public void doInit() {
        this.enableRawMode();
        this.running.set(true);
        new Thread(this::readUserInput).start();
    }

    public void destroy() {
        this.running.set(false);
        this.disableRawMode();
    }

    private void enableRawMode() {
        executeCommand("stty raw -echo </dev/tty");
    }

    private void disableRawMode() {
        executeCommand("stty sane </dev/tty");
    }

    public List<Character> getNextUserInput() {
        List<Character> currentInput = input.getAndSet(new ArrayList<>());
        return List.copyOf(currentInput);
    }

    private void readUserInput() {
        while (running.get()) {
            try {
                char read = (char) System.in.read();
                this.input.updateAndGet(l -> {
                    l.add(read);
                    return l;
                });
            } catch (IOException e) {
                throw new IllegalStateException("Could not read user input", e);
            }
        }
    }

    private void executeCommand(String command) {
        try {
            Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command}).waitFor();
        } catch (Exception e) {
            throw new IllegalStateException("Could not execute command");
        }
    }
}