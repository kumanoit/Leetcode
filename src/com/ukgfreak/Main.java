package com.ukgfreak;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/5/20 IST 1:02 PM
 */
public class Main {
    private static final String USER_ENDPOINT = "https://jsonmock.hackerrank.com/api/article_users?page=";

    public static double champagneTower(int poured, int query_row, int query_glass) {
        int totalFullCups = getPenultimateLimit(query_row);
        poured -= totalFullCups;
        if (poured <= 0) {
            return 0.0;
        }
        System.out.print("\n\ncups = " + totalFullCups);
        return getTermConstant(query_row, query_glass) * poured;
    }

    private static int getPenultimateLimit(int rows) {
        return (int) Math.pow(2, rows) - 1;
    }

    private static double getTermConstant(int query_row, int query_glass) {
        int n = query_row;
        int k = Math.min(query_glass, n - query_glass);
        int i = n;
        double solution = 1;
        double nr = n;
        double dr = k;
        for (nr = n, dr = k; dr >= 1; dr--, nr--) {
            solution = solution * nr / dr;
        }

        while (nr >= 1) {
            solution *= nr--;
        }

        double factor = Math.pow(2, query_row);
        System.out.print(" " + solution + " " + factor);
        return solution / factor;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(champagneTower(1, 1, 1));
        System.out.println(champagneTower(2, 1, 1));
        System.out.println(champagneTower(100000009, 33, 17));
    }

    public static List<String> getUsernames(int threshold) throws Exception {
        List<String> userNames = new ArrayList<>();
        String response = getResponse(1);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);

        long totalPages = (long) jsonObject.get("total_pages");
        int i = 1;
        while (i <= totalPages) {
            jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray data = (JSONArray) jsonObject.get("data");
            for (int j = 0; j < data.size(); j++) {
                JSONObject userInfo = (JSONObject) data.get(j);
                long submission_count = (long) userInfo.get("submission_count");
                if (submission_count > threshold) {
                    String username = (String) userInfo.get("username");
                    userNames.add(username);
                }
            }
            response = getResponse(++i);
        }

        return userNames;
    }

//    public static void main(String[] args) throws Exception {
//        getUserTransaction(4, "debit", "02-2019").forEach(item -> System.out.println(item));
//    }

    private static String getResponse(final int pageNumber) throws Exception {
        URL url = new URL("https://jsonmock.hackerrank.com/api/article_users?page=" + pageNumber);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}
