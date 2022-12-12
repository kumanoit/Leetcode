package com.ukgfreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class IPOShareDistribution {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 5, 0);
        List<Integer> list2 = Arrays.asList(2, 1, 4, 2);
        List<Integer> list3 = Arrays.asList(3, 5, 4, 6);
        List<List<Integer>> bids = new ArrayList<>();
        bids.add(list1);
        bids.add(list2);
        bids.add(list3);
        getUnallottedUsers(bids, 3).forEach(item -> {
            System.out.println(item);
        });
    }

    public static List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        List<Integer> unallottedUsers = new ArrayList<>();
        int[][] users = new int[bids.size()][4];

        Map<Integer, List<List<Integer>>> highestBids = new TreeMap<>(Collections.reverseOrder());
        for (List<Integer> bid : bids) {
            if (!highestBids.containsKey(bid.get(2))) {
                highestBids.put(bid.get(2), new ArrayList<>());
            }
            highestBids.get(bid.get(2)).add(bid);
        }

        Set<Integer> allottedUsers = new HashSet<>();
        for (Integer key : highestBids.keySet()) {
            if (totalShares <= 0) {
                break;
            }
            List<List<Integer>> biddings = highestBids.get(key);
            Collections.sort(biddings, (bid1, bid2) -> {
                return bid1.get(3) == bid2.get(3) ?
                        bid1.get(1) - bid2.get(1) :
                        bid1.get(3) - bid2.get(3);
            });

            while(true){
                int totalShareCustom = totalShares;
                for (List<Integer> bidding : biddings) {
                    if (totalShares <= 0) {
                        break;
                    }
                    if (bidding.get(1) > 0) {
                        totalShares--;
                        allottedUsers.add(bidding.get(0));
                        bidding.set(1, bidding.get(1) - 1);
                    }
                }
                if (totalShareCustom == totalShares) {
                    break;
                }
            }
        }

        for (List<Integer> bid : bids) {
            if (!allottedUsers.contains(bid.get(0))) {
                unallottedUsers.add(bid.get(0));
            }
        }
        return unallottedUsers;
    }

}
//
//class User implements Comparable<User> {
//    int userId;
//    int numberOfShares;
//    int biddingPrice;
//    long timestamp;
//
//    public User(final int userId, final int numberOfShares, final int biddingPrice, final long timestamp) {
//        this.userId = userId;
//        this.numberOfShares = numberOfShares;
//        this.biddingPrice = biddingPrice;
//        this.timestamp = timestamp;
//    }
//
//    @Override
//    public int compareTo(User other) {
//        return
//    }
//}
