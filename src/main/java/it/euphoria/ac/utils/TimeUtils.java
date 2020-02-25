/*
    Class created by NotStxnks_
    Project: EuphoriaAC
    Date: 25/02/2020
    Time: 12:37
*/

package it.euphoria.ac.utils;

public class TimeUtils {

    public static boolean elapsed(long from, long required) {
        return System.currentTimeMillis() - from > required;
    }

    public static long elapsed(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public static long left(long start, long required) {
        return required + start - System.currentTimeMillis();
    }
}
