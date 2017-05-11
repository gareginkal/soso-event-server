package com.soso.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.soso.dto.EventDto;
import com.soso.dto.ServiceInfoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Garik Kalashyan on 3/8/2017.
 */

public class JsonConverter {
    private final static Gson gson = new GsonBuilder().create();

    public static String toJson(Map<String,Object> object){
        return gson.toJson(object);
    }

    public static ServiceInfoDto getServiceInfoFromJSONString(String jsonString){
        JsonObject jsonObject = gson.fromJson( jsonString , JsonObject.class);
        return gson.fromJson(jsonObject.get("serviceDetail").toString(),ServiceInfoDto.class);
    }

    public static List<EventDto> getEventsFromJSONString(String jsonString){
        JsonArray jsonArray = gson.fromJson( jsonString , JsonArray.class);
        List<EventDto> list = new ArrayList<>();
        jsonArray.forEach(node ->{
            list.add(gson.fromJson(node.toString(),EventDto.class));
        });
        return list;
    }

    public static boolean isValidTokenStatusFromJSONString(String jsonString){
        return gson.fromJson(gson.fromJson(jsonString,JsonObject.class).get("isValidToken").toString(),Boolean.class);
    }


    public static String getCreatedTokenKeyFromJSONString(String jsonString){
        return gson.fromJson(gson.fromJson(jsonString,JsonObject.class).getAsJsonObject("createdToken").get("key").toString(),String.class);
    }



}
