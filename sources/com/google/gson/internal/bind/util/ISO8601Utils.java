package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    private static final String UTC_ID = "UTC";

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder("yyyy-MM-ddThh:mm:ss".length() + (z ? ".sss".length() : 0) + (timeZone.getRawOffset() == 0 ? "Z" : "+hh:mm").length());
        padInt(sb, gregorianCalendar.get(1), "yyyy".length());
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, "MM".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), "dd".length());
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), "hh".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), "mm".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), "ss".length());
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), "sss".length());
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, "hh".length());
            sb.append(':');
            padInt(sb, abs2, "mm".length());
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c4 A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01a9 A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date parse(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        /*
            r1 = r17
            r2 = r18
            int r0 = r18.getIndex()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r3 = r0 + 4
            int r0 = parseInt(r1, r0, r3)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r4 = 45
            boolean r5 = checkOffset(r1, r3, r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r5 == 0) goto L_0x0018
            int r3 = r3 + 1
        L_0x0018:
            int r5 = r3 + 2
            int r3 = parseInt(r1, r3, r5)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            boolean r6 = checkOffset(r1, r5, r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r6 == 0) goto L_0x0026
            int r5 = r5 + 1
        L_0x0026:
            int r6 = r5 + 2
            int r5 = parseInt(r1, r5, r6)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r7 = 84
            boolean r7 = checkOffset(r1, r6, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r8 = 1
            if (r7 != 0) goto L_0x0049
            int r9 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r9 > r6) goto L_0x0049
            java.util.GregorianCalendar r4 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r3 = r3 - r8
            r4.<init>(r0, r3, r5)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r2.setIndex(r6)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.util.Date r0 = r4.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            return r0
        L_0x0049:
            r9 = 43
            r10 = 90
            if (r7 == 0) goto L_0x00b9
            int r6 = r6 + 1
            int r7 = r6 + 2
            int r6 = parseInt(r1, r6, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r12 = 58
            boolean r13 = checkOffset(r1, r7, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r13 == 0) goto L_0x0061
            int r7 = r7 + 1
        L_0x0061:
            int r13 = r7 + 2
            int r7 = parseInt(r1, r7, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            boolean r12 = checkOffset(r1, r13, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r12 == 0) goto L_0x006f
            int r13 = r13 + 1
        L_0x006f:
            int r12 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r12 <= r13) goto L_0x00bc
            char r12 = r1.charAt(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r12 == r10) goto L_0x00bc
            if (r12 == r9) goto L_0x00bc
            if (r12 == r4) goto L_0x00bc
            int r12 = r13 + 2
            int r13 = parseInt(r1, r13, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r14 = 59
            if (r13 <= r14) goto L_0x008e
            r15 = 63
            if (r13 >= r15) goto L_0x008e
            goto L_0x008f
        L_0x008e:
            r14 = r13
        L_0x008f:
            r13 = 46
            boolean r13 = checkOffset(r1, r12, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r13 == 0) goto L_0x00b6
            int r12 = r12 + 1
            int r13 = r12 + 1
            int r13 = indexOfNonDigit(r1, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r15 = r12 + 3
            int r15 = java.lang.Math.min(r13, r15)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r16 = parseInt(r1, r12, r15)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r15 = r15 - r12
            switch(r15) {
                case 1: goto L_0x00b1;
                case 2: goto L_0x00ae;
                default: goto L_0x00ad;
            }     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
        L_0x00ad:
            goto L_0x00b3
        L_0x00ae:
            int r16 = r16 * 10
            goto L_0x00b3
        L_0x00b1:
            int r16 = r16 * 100
        L_0x00b3:
            r12 = r16
            goto L_0x00be
        L_0x00b6:
            r13 = r12
            r12 = 0
            goto L_0x00be
        L_0x00b9:
            r13 = r6
            r6 = 0
            r7 = 0
        L_0x00bc:
            r12 = 0
            r14 = 0
        L_0x00be:
            int r15 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r15 <= r13) goto L_0x01a9
            char r15 = r1.charAt(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r11 = 5
            if (r15 != r10) goto L_0x00d0
            java.util.TimeZone r4 = TIMEZONE_UTC     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r13 = r13 + r8
            goto L_0x0178
        L_0x00d0:
            if (r15 == r9) goto L_0x00f1
            if (r15 != r4) goto L_0x00d5
            goto L_0x00f1
        L_0x00d5:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r3.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r4 = "Invalid time zone indicator '"
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r3.append(r15)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r4 = "'"
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r3 = r3.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
        L_0x00f1:
            java.lang.String r4 = r1.substring(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r9 = r4.length()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r9 < r11) goto L_0x00fc
            goto L_0x010d
        L_0x00fc:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r9.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r9.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r4 = "00"
            r9.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r4 = r9.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
        L_0x010d:
            int r9 = r4.length()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r13 = r13 + r9
            java.lang.String r9 = "+0000"
            boolean r9 = r9.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r9 != 0) goto L_0x0176
            java.lang.String r9 = "+00:00"
            boolean r9 = r9.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r9 == 0) goto L_0x0123
            goto L_0x0176
        L_0x0123:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r9.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r10 = "GMT"
            r9.append(r10)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r9.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r4 = r9.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.util.TimeZone r9 = java.util.TimeZone.getTimeZone(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r10 = r9.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            boolean r15 = r10.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r15 != 0) goto L_0x0174
            java.lang.String r15 = ":"
            java.lang.String r11 = ""
            java.lang.String r10 = r10.replace(r15, r11)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            boolean r10 = r10.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            if (r10 == 0) goto L_0x0151
            goto L_0x0174
        L_0x0151:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r3.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r5 = "Mismatching time zone indicator: "
            r3.append(r5)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r4 = " given, resolves to "
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r4 = r9.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r3 = r3.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
        L_0x0174:
            r4 = r9
            goto L_0x0178
        L_0x0176:
            java.util.TimeZone r4 = TIMEZONE_UTC     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
        L_0x0178:
            java.util.GregorianCalendar r9 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r9.<init>(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r4 = 0
            r9.setLenient(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r9.set(r8, r0)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            int r3 = r3 - r8
            r0 = 2
            r9.set(r0, r3)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r0 = 5
            r9.set(r0, r5)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r0 = 11
            r9.set(r0, r6)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r0 = 12
            r9.set(r0, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r0 = 13
            r9.set(r0, r14)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r0 = 14
            r9.set(r0, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            r2.setIndex(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.util.Date r0 = r9.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            return r0
        L_0x01a9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            java.lang.String r3 = "No time zone indicator"
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01b5, NumberFormatException -> 0x01b3, IllegalArgumentException -> 0x01b1 }
        L_0x01b1:
            r0 = move-exception
            goto L_0x01b6
        L_0x01b3:
            r0 = move-exception
            goto L_0x01b6
        L_0x01b5:
            r0 = move-exception
        L_0x01b6:
            if (r1 != 0) goto L_0x01ba
            r1 = 0
            goto L_0x01d0
        L_0x01ba:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = 34
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = "'"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
        L_0x01d0:
            java.lang.String r3 = r0.getMessage()
            if (r3 == 0) goto L_0x01dc
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x01fa
        L_0x01dc:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "("
            r3.append(r4)
            java.lang.Class r4 = r0.getClass()
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r4 = ")"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
        L_0x01fa:
            java.text.ParseException r4 = new java.text.ParseException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Failed to parse date ["
            r5.append(r6)
            r5.append(r1)
            java.lang.String r1 = "]: "
            r5.append(r1)
            r5.append(r3)
            java.lang.String r1 = r5.toString()
            int r2 = r18.getIndex()
            r4.<init>(r1, r2)
            r4.initCause(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        } else {
            i4 = i;
            i3 = 0;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i4 = i5;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
