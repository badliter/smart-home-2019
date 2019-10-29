package ru.sbt.mipt.oop.homeReader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.HomeReader;
import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeReader implements HomeReader {
    @Override
    public SmartHome readHome() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}