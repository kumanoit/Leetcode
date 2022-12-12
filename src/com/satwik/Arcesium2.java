package com.satwik;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Arcesium2 {

    private static final String URL_PATH = "https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users";

    public static void main(String[] args) throws ParseException {
        List<String> inputList = new ArrayList<>();
        int size = 0;
//        inputList.add("username");
//        inputList.add("EQUALS");
//        inputList.add("vinayk");
//
//        inputList.add("address.city");
//        inputList.add("EQUALS");
//        inputList.add("Kolkata");

        inputList.add("address.city");
        inputList.add("IN");
        inputList.add("Kolkata,Mumbai");


        apiResponseParser(inputList, size).forEach(item -> System.out.println(item));
    }

    public static List<Integer> apiResponseParser(List<String> inputList, int size) throws ParseException {
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
        System.out.println(fileContent);
        return solution;
    }

    private static List<Integer> computeEqual(final String fileContent, final String fieldName, final String value) {
//        JsonArray users = new Gson().fromJson(fileContent, JsonArray.class);
        List<Integer> output = new ArrayList<>();
//        int count = 0;
//        for (int index = 0; index < users.size(); index++) {
//            JsonObject user = (JsonObject) users.get(index);
//            if (user.get(fieldName).getAsString().equals(value)) {
//                count++;
//            }
//        }
//        output.add(count);
        return output;
    }

    private static List<Integer> computeIn(final String fileContent, final String fieldName, final String value) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray users = (JSONArray) jsonParser.parse(fileContent);
        final String[] values = value.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (final String val : values) {
            map.put(val, 0);
        }

        for (int index = 0; index < users.size(); index++) {
            JSONObject user = (JSONObject) users.get(index);
            final String key = user.get(fieldName).toString();
            if (map.containsKey(key)) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        return (List<Integer>) map.values();
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
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
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
