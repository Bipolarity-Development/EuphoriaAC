package it.euphoria.ac.utils;

import org.bukkit.Bukkit;

public class Logger {

    public String name = "EuphoriaAC";

    public void log(String s) {
        Bukkit.getServer().getConsoleSender().sendMessage("[" + name + "] | [INFO] " + s);
    }

    public void log(String s, LogType type) {
        Bukkit.getServer().getConsoleSender().sendMessage("[" + name + "] | [" + type.name() + "] " + s);
    }

    public enum LogType {
        INFO, WARNING, ERROR
    }
}