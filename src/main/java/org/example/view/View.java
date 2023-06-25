package org.example.view;

import org.example.controller.GameController;

import java.util.Observer;
import java.util.Scanner;

public abstract class View implements Observer {
    protected final Scanner scanner;
    protected final GameController controller;

    protected View(GameController controller) {
        scanner = new Scanner(System.in);
        this.controller = controller;
        controller.setView(this);
    }
}
