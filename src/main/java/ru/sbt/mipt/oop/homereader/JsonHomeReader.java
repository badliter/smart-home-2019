package ru.sbt.mipt.oop.homereader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.AlarmState;
import ru.sbt.mipt.oop.HomeReader;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;
import ru.sbt.mipt.oop.serializer.HomeAlarmDeserializer;
import ru.sbt.mipt.oop.serializer.JsonInterfaceAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeReader implements HomeReader {
    @Override
    public SmartHome readHome() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AlarmState.class, new JsonInterfaceAdapter<AlarmState>())
                .registerTypeAdapter(HomeAlarm.class, new HomeAlarmDeserializer())
                .create();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}