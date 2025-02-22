package com.example.mud.controller;

import java.util.Scanner;
import com.example.mud.player.Player;
import com.example.mud.room.Room;
import com.example.mud.item.Item;


public class MUDController {

    private final Player player;
    private boolean running;
    private final Scanner scanner;


    public MUDController(Player player) {
        this.player = player;
        this.running = true;
        this.scanner = new Scanner(System.in);
    }


    public void runGameLoop() {
        while (running) {
            System.out.print("> "); // Command prompt
            String input = scanner.nextLine().trim();
            handleInput(input);
        }
        scanner.close();
    }


    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                if (argument.startsWith("up ")) {
                    pickUp(argument.substring(3));
                } else {
                    System.out.println("Invalid pick up command. Try 'pick up <itemName>'.");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "talk":
                talk();
                break;
            case "open":
                open(argument);
                break;
            case "quit":
            case "exit":
                running = false;
                System.out.println("Exiting game. Goodbye!");
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
                break;
        }
    }


    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        if (currentRoom != null) {
            System.out.println(currentRoom.describe());
        } else {
            System.out.println("You are in an unknown space.");
        }
    }


    private void move(String direction) {
        if (direction.isEmpty()) {
            System.out.println("Move where? Specify a direction: forward, back, left, or right.");
            return;
        }

        Room nextRoom = player.getCurrentRoom().getConnectedRoom(direction);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("You moved " + direction + ".");
            lookAround();
        } else {
            System.out.println("You can't go that way!");
        }
    }


    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            player.addItemToInventory(item);
            currentRoom.removeItem(item);
            System.out.println("You picked up the " + itemName + ".");
        } else {
            System.out.println("No item named " + itemName + " here!");
        }
    }


    private void checkInventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("You are carrying:");
            player.getInventory().forEach(item -> System.out.println("- " + item.getName()));
        }
    }


    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Describe the current room");
        System.out.println("move <forward|back|left|right> - Move in the specified direction");
        System.out.println("pick up <itemName> - Pick up an item from the room");
        System.out.println("inventory - List items in your inventory");
        System.out.println("talk - Talk to NPCs in the room");
        System.out.println("open <object> - Open doors or containers in the room");
        System.out.println("help - Show this help menu");
        System.out.println("quit / exit - Exit the game");
    }


    private void talk() {
        Room currentRoom = player.getCurrentRoom();
        if (currentRoom.hasNPC()) {
            System.out.println("You talk to " + currentRoom.getNPC().getName() + ": " + currentRoom.getNPC().getDialogue());
        } else {
            System.out.println("There is no one to talk to here.");
        }
    }


    private void open(String object) {
        if (object.isEmpty()) {
            System.out.println("Open what? Specify an object.");
            return;
        }

        Room currentRoom = player.getCurrentRoom();
        if (currentRoom.hasObject(object)) {
            System.out.println("You open the " + object + " and find: " + currentRoom.getObjectContents(object));
        } else {
            System.out.println("There is no " + object + " here to open.");
        }
    }
}
