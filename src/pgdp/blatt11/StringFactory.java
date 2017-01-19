
package pgdp.blatt11;

public class StringFactory {
    public static String shouldIsError(int should, int is, String subject) {
        return "Error: The password does not contain enough " + subject + ". It has " + is + " " + subject + ", while at least " + should + " " + subject + " are required.";
    }
}
