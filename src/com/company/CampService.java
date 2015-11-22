package com.company;

/**
 * Service for camp calculation
 */
public class CampService {

    /**
     * Return camp, from which you can make a full circle
     *
     * @param campGrant       int[] How much water can give each camp
     * @param roadConsumption int[] How much water you need on the road to the next camp
     * @return int camp number and -1 in case of no result
     */
    public int getInitialCamp(int[] campGrant, int[] roadConsumption) {
        //check preconditions
        if (campGrant == null || roadConsumption == null) {
            throw new IllegalArgumentException("Input arrays shouldn't be null");
        }
        if (campGrant.length != roadConsumption.length) {
            throw new IllegalArgumentException("Input arrays should have the same size");
        }
        if (campGrant.length == 0) {
            throw new IllegalArgumentException("Input arrays shouldn't be empty");
        }

        int campCount = campGrant.length;
        int startCamp = 0;
        int bottle = 0;
        //go through both arrays twice to check case of the last camp
        for (int camp = startCamp; camp <= campCount * 2 - 1; camp++) {
            bottle = bottle + campGrant[camp % campCount] - roadConsumption[camp % campCount];
            if (bottle < 0) {
                //ran out of camps
                if (camp > campCount - 1) {
                    return -1;
                }
                bottle = 0;
                startCamp = camp + 1;
            }
        }
        return startCamp;
    }
}
