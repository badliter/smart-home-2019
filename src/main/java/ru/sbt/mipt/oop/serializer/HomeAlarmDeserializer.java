package ru.sbt.mipt.oop.serializer;

import com.google.gson.*;
import ru.sbt.mipt.oop.AlarmState;
import ru.sbt.mipt.oop.home.alarm.DeactivatedAlarmState;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class HomeAlarmDeserializer implements JsonDeserializer<HomeAlarm> {

    @Override
    public HomeAlarm deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        HomeAlarm homeAlarm = getHomeAlarm(jsonElement);
        setHomeAlarmInState(homeAlarm);
        return homeAlarm;
    }

    private void setHomeAlarmInState(HomeAlarm homeAlarm) {
        try {
            Field homeAlarmField = DeactivatedAlarmState.class.getDeclaredField("homeAlarm");
            homeAlarmField.setAccessible(true);
            homeAlarmField.set(homeAlarm.getAlarmState(), homeAlarm);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Default state should be deactivated");
        }
    }

    private HomeAlarm getHomeAlarm(JsonElement jsonElement) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AlarmState.class, new JsonInterfaceAdapter<AlarmState>());
        Gson gson = builder.create();
        return gson.fromJson(jsonElement, HomeAlarm.class);
    }
}
