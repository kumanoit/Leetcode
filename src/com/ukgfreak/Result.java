package com.ukgfreak;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Result {

    public static void main(String[] args) throws Exception {
        getUserTransaction(4, "debit", "02-2019").forEach(item -> System.out.println(item));
    }

    public static List<Integer> getUserTransaction(int uid, String txnType, String monthYear) throws Exception {
        List<Integer> solution = new ArrayList<>();
        String response = getResponse(uid, 1);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);

        List<UserInfo> userInfoList = new ArrayList<>();
        long totalPages = (long) jsonObject.get("total_pages");
        int i = 1;
        while (i <= totalPages) {
            jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray data = (JSONArray) jsonObject.get("data");
            for (int j = 0; j < data.size(); j++) {
                JSONObject userInfo = (JSONObject) data.get(j);
                long userId = (long) userInfo.get("userId");
                if (userId == uid) {
                    long timestamp = (long) userInfo.get("timestamp");
                    if (isInSameMonthYear(monthYear, timestamp)) {
                        userInfoList.add(new UserInfo(
                                (long) userInfo.get("id"),
                                (String) userInfo.get("txnType"),
                                (String) userInfo.get("amount")
                        ));
                    }
                }
            }
            response = getResponse(uid, ++i);
        }
        double averageAmount = getAverageAmount(userInfoList);

        for (UserInfo userInfo : userInfoList) {
            if (userInfo.txnType.equals(txnType.toLowerCase()) && userInfo.amount > averageAmount) {
                solution.add((int) userInfo.id);
            }
        }
        return solution;
    }

    private static double getAverageAmount(List<UserInfo> userInfoList) {
        double sum = 0;
        int count = 0;
        for (UserInfo userInfo : userInfoList) {
            if (userInfo.txnType.equals("debit")) {
                sum += userInfo.amount;
                count++;
            }
        }
        if (!userInfoList.isEmpty()) {
            sum /= userInfoList.size();
        }
        return count == 0 ? 0 : sum / count;
    }

    private static boolean isInSameMonthYear(final String monthYear, final long timestamp) {
        Date date = new Date(timestamp);
        DateFormat format = new SimpleDateFormat("MM-YYYY");
        return monthYear.equals(format.format(date));
    }

    private static String getResponse(final int userId, int pageNumber) throws Exception {
        URL url = new URL("https://jsonmock.hackerrank.com/api/transactions/search?userId=" + userId + "&page=" + pageNumber);
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
        System.out.println(response.toString());
        return response.toString();
    }

}

class UserInfo {
    long id;
    String txnType;
    double amount;

    public UserInfo(long id, String txnType, String amount) {
        this.id = id;
        this.txnType = txnType;
        amount = amount.replace(",", "");
        this.amount = Double.parseDouble(amount.substring(1));
    }

}
