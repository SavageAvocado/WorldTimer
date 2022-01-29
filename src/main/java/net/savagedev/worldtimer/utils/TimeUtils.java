package net.savagedev.worldtimer.utils;

public final class TimeUtils {
    private TimeUtils() {
        throw new UnsupportedOperationException();
    }

    public static String formatTime(long playTime) {
        final long[] times = new long[3];

        times[0] = playTime / 20 % 60; // Seconds
        times[1] = playTime / (20 * 60) % 60; // Minutes
        times[2] = playTime / (20 * 3600); // Hours

        return formatTimes(times);
    }

    private static String formatTimes(long[] times) {
        final StringBuilder builder = new StringBuilder();

        for (int i = times.length - 1; i >= 0; i--) {
            final long time = times[i];

            if (time < 10) {
                builder.append('0');
            }

            builder.append(time);

            if (i > 0) {
                builder.append(':');
            }
        }

        return builder.toString().trim();
    }
}
