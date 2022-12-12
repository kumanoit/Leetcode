package com.satwik;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 4/27/22 IST 9:34 PM
 */
public class Arcesium {
    private static final String URL_PATH = "https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users";

    public static void main(String[] args) {
        List<String> inputList = new ArrayList<>();
        int size = 0;
        inputList.add("username");
        inputList.add("EQUALS");
        inputList.add("Garimag");

//        inputList.add("address.city");
//        inputList.add("EQUALS");
//        inputList.add("Kolkata");

//        inputList.add("address.city");
//        inputList.add("IN");
//        inputList.add("Kolkata,Mumbai");

        apiResponseParser(inputList, size).forEach(item -> System.out.println(item));
    }

    public static List<Integer> apiResponseParser(List<String> inputList, int size) {
        List<Integer> solution = new ArrayList<>();
        final String fileContent = getContent();
        final String fieldName = inputList.get(0);
        final String operationType = inputList.get(1);
        final String value = inputList.get(2);
        if ("EQUALS".equals(operationType)) {
            solution = computeEqual(fileContent, fieldName, value);
        } else {
            solution = computeIn(fileContent, fieldName, value);
        }
        if (solution.isEmpty()) {
            solution.add(-1);
        }
        return solution;
    }

    private static List<Integer> computeEqual(final String fileContent, final String fieldName, final String value) {
        JsonArray users = new Gson().fromJson(fileContent, JsonArray.class);
        List<Integer> output = new ArrayList<>();
        final String[] fields = fieldName.split("\\.");
        for (int index = 0; index < users.size(); index++) {
            JsonObject data = (JsonObject) users.get(index);
            int userId = data.get("id").getAsInt();
            String key = null;
            for (int fieldIndex = 0; fieldIndex < fields.length; fieldIndex++) {
                if (data.get(fields[fieldIndex]) == null) {
                    break;
                }
                if (fieldIndex == fields.length - 1) {
                    key = data.get(fields[fieldIndex]).getAsString();
                } else {
                    data = (JsonObject) data.get(fields[fieldIndex]);
                }
            }
            if (value.equals(key)) {
                output.add(userId);
            }
        }

        return output;
    }

    private static List<Integer> computeIn(final String fileContent, final String fieldName, final String value) {
        JsonArray users = new Gson().fromJson(fileContent, JsonArray.class);
        List<Integer> output = new ArrayList<>();
        final String[] values = value.split(",");
        Set<String> set = new HashSet<>();
        for (final String val : values) {
            set.add(val);
        }

        final String[] fields = fieldName.split("\\.");
        for (int index = 0; index < users.size(); index++) {
            JsonObject data = (JsonObject) users.get(index);
            int userId = data.get("id").getAsInt();
            String key = null;
            for (int fieldIndex = 0; fieldIndex < fields.length; fieldIndex++) {
                if (data.get(fields[fieldIndex]) == null) {
                    break;
                }
                if (fieldIndex == fields.length - 1) {
                    key = data.get(fields[fieldIndex]).getAsString();
                } else {
                    data = (JsonObject) data.get(fields[fieldIndex]);
                }
            }
            if (set.contains(key)) {
                output.add(userId);
            }
        }
        return output;
    }

    private static String getContent() {
        final StringBuilder fileContent = new StringBuilder();
        try {
            URL url = new URL(URL_PATH);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                fileContent.append(output);
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
