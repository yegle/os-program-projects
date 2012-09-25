/**
 * interface that we need to implement
 */
public interface Message {
    /**
     * set the counts into internal member variables
     */
    public void setCounts();

    /**
     * get the character count
     *
     * @return number of characters in this instance
     */
    public int getCharacterCount();

    /**
     * get the digit count
     *
     * @return number of digit in this instance
     */
    public int getDigitCount();
}
