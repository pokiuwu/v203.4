package net.swordie.ms.util;

import io.netty.buffer.ByteBuf;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created on 2/28/2017.
 */
public class Util {

    private static Map<Class, Class> boxedToPrimClasses = new HashMap<>();
    private static Pattern regexPattern = Pattern.compile("^\\$2[a-z]\\$.{56}$");

    static {
        boxedToPrimClasses.put(Boolean.class, boolean.class);
        boxedToPrimClasses.put(Byte.class, byte.class);
        boxedToPrimClasses.put(Short.class, short.class);
        boxedToPrimClasses.put(Character.class, char.class);
        boxedToPrimClasses.put(Integer.class, int.class);
        boxedToPrimClasses.put(Long.class, long.class);
        boxedToPrimClasses.put(Float.class, float.class);
        boxedToPrimClasses.put(Double.class, double.class);
    }

    /**
     * Gets a random element from a given List. This is done by utilizing {@link #getRandom(int)}.
     * @param list The list to select the element from
     * @param <T> The type of elements of the list
     * @return A random element from the list, or null if the list is null or empty.
     */
    public static <T> T getRandomFromCollection(List<T> list) {
        if(list != null && list.size() > 0) {
            return list.get(getRandom(list.size() - 1));
        }
        return null;
    }

    /**
     * Gets a random element from a given Collection. This is done by making an array from the Collection and calling
     * {@link #getRandomFromCollection(List)}
     * @param coll The collection to select the element from
     * @param <T> The type of elements of the list
     * @return A random element from the list, or null if the list is null or empty.
     */
    public static <T> T getRandomFromCollection(Collection<T> coll) {
        return getRandomFromCollection(new ArrayList<>(coll));
    }

    /**
     * Gets a random element from a given List. This is done by utilizing {@link #getRandom(int)}.
     * @param list The list to select the element from
     * @param <T> The type of elements of the list
     * @return A random element from the list, or null if the list is null or empty.
     */
    public static <T> T getRandomFromCollection(T[] list) {
        if(list != null && list.length > 0) {
            return list[getRandom(list.length - 1)];
        }
        return null;
    }

    /**
     * Reads a file and returns the contents as a single String.
     * @param path The path to the file
     * @param encoding The encoding the file is in.
     * @return The contents of the File as a single String.
     * @throws IOException If the file cannot be found (usually)
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Returns a bitwise OR of two arrays. Takes the length of arr1 as the return array size. If arr2 is smaller,
     * will return an {@link ArrayIndexOutOfBoundsException}.
     * @param arr1 The first array
     * @param arr2 The second array
     * @return The result of using bitwise OR on all contents of arr1 to arr2 such that for all index i with i < arr1.length:
     *  res[i] == arr1[i] | arr2[i]
     */
    public static int[] bitwiseOr(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr1[i] | arr2[i];
        }
        return res;
    }

    /**
     * Returns the current time as an int. See System.currentTimeMillis().
     * @return the current time as an int.
     */
    public static int getCurrentTime() {
        return (int) System.currentTimeMillis();
    }

    /**
     * Returns the current time. Simply calls System.currentTimeMillis().
     * @return The current time as milliseconds since unix start time
     */
    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * Returns a random number from 0 up to (and <b>including</b>) inclBound. Creates a new Random class upon call.
     * If a bound smaller or equal to 0 is given, always returns 0.
     * @param inclBound the upper bound of the random number
     * @return A random number from 0 up to and including inclBound
     */
    public static int getRandom(int inclBound) {
        if (inclBound <= 0) {
            return 0;
        }
        return new Random().nextInt(inclBound + 1);
    }

    /**
     * Returns a random number from <code>start</code> up to <code>end</code>. Creates a new Random class upon call.
     * If <code>start</code> is greater than <code>end</code>, <code>start</code> will be swapped with <code>end</code>.
     * @param start the lower bound of the random number
     * @param end the upper bound of the random number
     * @return A random number from <code>start</code> up to <code>end</code>
     */
    public static int getRandom(int start, int end) {
        if (end - start == 0) {
            return start;
        }
        if (start > end) {
            int temp = end;
            end = start;
            start = temp;
        }
        return start + new Random().nextInt(end - start);
    }

    /**
     * Checks if some action succeeds, given a chance and maximum number.
     * @param chance The threshold at which something is classified as success
     * @param max The maximum number that is generated, exclusive
     * @return Whether or not the test succeeded
     */
    public static boolean succeedProp(int chance, int max) {
        Random random = new Random();
        return random.nextInt(max) < chance;
    }

    /**
     * Checks of some action succeeds, given a chance out of a 100.
     * @param chance The threshold at which something is classified as success
     * @return Whether or not the test succeeded
     */
    public static boolean succeedProp(int chance) {
        return succeedProp(chance, 100);
    }

    // https://www.programcreek.com/2014/03/leetcode-reverse-bits-java/

    /**
     * Reverses all the bits of an integer.
     * @param n The number to reverse the bits of
     * @return The reversed bits
     */
    public static int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }

        return n;
    }

    /**
     * Swaps two bits of a given number.
     * @param n The number that the bits should be swapped of
     * @param i The first swapping index
     * @param j The second swapping index
     * @return The number with the bits reversed
     */
    private static int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;

        if ((a ^ b) != 0) {
            n ^= (1 << i) | (1 << j);
        }
        return n;
    }

    /**
     * Checks if a String is a number ((negative) natural or decimal).
     * @param string The String to check
     * @return Whether or not the String is a number
     */
    public static boolean isNumber(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Creates a byte array given a string. Ignores spaces and the '|' character.
     * @param s The String to transform
     * @return The byte array that the String contained (if there is any, some RuntimeException otherwise)
     */
    public static byte[] getByteArrayByString(String s) {
        s = s.replace("|", " ");
        s = s.replace(" ", "");
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Turns a byte array into a readable String (e.g., 3A 00 89 BF).
     * @param arr The array to transform
     * @return The readable byte array
     */
    public static String readableByteArray(byte[] arr) {
        StringBuilder res = new StringBuilder();
        for(byte b : arr) {
            res.append(String.format("%02X ",b));
        }
        return res.toString();
    }

    /**
     * Turns a ByteBuf into a readable String (e.g., 3A 00 89 BF).
     * @param buf The ByteBuf to transform
     * @return The readable byte array
     */
    public static String readableByteArrayFromByteBuf(ByteBuf buf) {
        byte[] bytes = new byte[buf.capacity()];
        for(int i = buf.readableBytes(); i < buf.capacity(); i++) {
            bytes[i] = buf.getByte(i);
        }
        return Util.readableByteArray(bytes);
    }

    /**
     * Transforms an integer into a byte array of length 4, Little Endian.
     * @param n The number to turn into a byte array
     * @return The created byte array (Little Endian)
     */
    public static byte[] IntToByteArrayLE(int n) {
        byte[] res = new byte[Integer.BYTES];
        res[0] = (byte) n;
        res[1] = (byte) (n >>> 8);
        res[2] = (byte) (n >>> 16);
        res[3] = (byte) (n >>> 24);
        return res;
    }

    /**
     * Creates a directory if there is none.
     * @param dir The directory to create
     */
    public static void makeDirIfAbsent(String dir) {
        File file = new File(dir);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * Adds right padding given an initial String, padding character and maximum length. If the input String is longer
     * than the given maximum length, the String length is taken instead (effectively doing nothing, as there is
     * nothing to pad.
     * @param totalLength The total length the String should amount to
     * @param c The padding character
     * @param value The initial value of the String
     * @return The right padded String
     */
    public static String rightPaddedString(int totalLength, char c, String value) {
        totalLength = Math.max(totalLength, value.length());
        char[] chars = new char[totalLength];
        char[] valueChars = value.toCharArray();
        for (int i = 0; i < value.length() ; i++) {
            chars[i] = valueChars[i];
        }
        for(int i = value.length(); i < chars.length; i++) {
            chars[i] = c;
        }
        return new String(chars);
    }

    /**
     * Adds left padding given an initial String, padding character and maximum length. If the input String is longer
     * than the given maximum length, the String length is taken instead (effectively doing nothing, as there is
     * nothing to pad.
     * @param totalLength The total length the String should amount to
     * @param c The padding character
     * @param value The initial value of the String
     * @return The left padded String
     */
    public static String leftPaddedString(int totalLength, char c, String value) {
        totalLength = Math.max(totalLength, value.length());
        char[] chars = new char[totalLength];
        char[] valueChars = value.toCharArray();
        int pad = totalLength - value.length();
        int i;
        for (i = 0; i < pad ; i++) {
            chars[i] = c;
        }
        int j = 0;
        for(i = pad; i < chars.length; i++) {
            chars[i] = valueChars[j++];
        }
        return new String(chars);
    }

    /**
     * Gets a single element from a collection by using a predicate. Returns a random element if there are multiple
     * elements for which the predicate holds.
     * @param collection The collection the element should be gathered from
     * @param pred The predicate that should hold for the element
     * @param <T> The type of the collection's elements
     * @return An element for which the predicate holds, or null if there is none
     */
    public static <T> T findWithPred(java.util.Collection<T> collection, Predicate<T> pred) {
        return collection.stream().filter(pred).findAny().orElse(null);
    }

    /**
     * Gets a single element from an array by using a predicate. Returns a random element if there are multiple
     * elements for which the predicate holds.
     * @param arr The array the element should be gathered from
     * @param pred The predicate that should hold for the element
     * @param <T> The type of the collection's elements
     * @return An element for which the predicate holds, or null if there is none
     */
    public static <T> T findWithPred(T[] arr, Predicate<T> pred) {
        return findWithPred(Arrays.asList(arr), pred);
    }

    /**
     * Returns a formatted number, using English locale.
     *
     * @param number The number to be formatted
     * @return The formatted number
     */
    public static String formatNumber(String number) {
        return NumberFormat.getInstance(Locale.ENGLISH).format(Long.parseLong(number));
    }

    public static Class<?> convertBoxedToPrimitiveClass(Class<?> clazz) {
        return boxedToPrimClasses.getOrDefault(clazz, clazz);
    }

    /**
     * Tells us if a string is a BCrypt hash.
     *
     * @param password Password to check
     * @return Boolean value
     */
    public static boolean isStringBCrypt(String password) {
        return regexPattern.matcher(password).matches();
    }

    /**
     * Returns the long as an int, or Integer.MAX_VALUE if it exceeds the maximum int value.
     * @param num the number that should be capped at Integer.MAX_VALUE
     * @return <code>num</code> if the number is small enough, else Integer.MAX_VALUE
     */
    public static int maxInt(long num) {
        return (int) Math.min(Integer.MAX_VALUE, num);
    }

    public static <T> Set<T> makeSet(T... elems) {
        Set<T> set = new HashSet<>();
        for (T elem : elems) {
            set.add(elem);
        }
        return set;
    }

    public static boolean isDigitLetterString(String name) {
        return name != null && name.matches("[a-zA-Z0-9]+"); // maybe allow special characters?
    }

    public static boolean isValidString(String name) {
        return name != null && name.matches("[a-zA-Z0-9`~!@#$%^&*()_+-={}|\\\\;':\",./<>?]*");
    }

    public static boolean isInteger(String val) {
        if (val != null && val.matches("^-?[0-9]+") && val.length() <= 10) {
            long longVal = Long.parseLong(val);
            return longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE;
        }
        return false;
    }

    public static String getLeft(String str, int size) {
        return str.substring(0, size);
    }

    public static String getMid(String str, int from, int to) {
        return str.substring(from, to);
    }

    public int strStr(String haystack, String needle) {
        if(haystack==null || needle==null)
            return 0;

        if(needle.length() == 0)
            return 0;

        for(int i=0; i<haystack.length(); i++){
            if(i + needle.length() > haystack.length())
                return -1;

            int m = i;
            for(int j=0; j<needle.length(); j++){
                if(needle.charAt(j)==haystack.charAt(m)){
                    if(j==needle.length()-1)
                        return i;
                    m++;
                }else{
                    break;
                }

            }
        }

        return -1;
    }

    public static String quotes(String var)
    {
        return String.format("\"%s\"", var);
    }
}
