/**
 * @author Alexander Vdovenkov
 * Date: 018 18.09.19
 */
public class IncrementerImpl implements Incrementer {

    /** Исходное потокобезопасное значение счетчика. */
    private int number = 0;

    /** Максимальное значение по умолчанию */
    private int maximumValue = Integer.MAX_VALUE;


    public int getNumber() {
        return number;
    }

    public void incrementNumber() {
        if (number == this.maximumValue) {
            number = 0; // сброс на 0
        } else {
            number++;
        }
    }

    /**
     * @param maximumValue - максимальное значение счетчика
     * @throws IllegalArgumentException в случае если переданное значение меньше нуля.
     */
    public void setMaximumValue(int maximumValue) throws IllegalArgumentException {
        if (maximumValue < 0) {
            throw new IllegalArgumentException("Максимальное значение не может быть отрицательным");
        }
        if (number > maximumValue) {
            number = 0;
        }
        this.maximumValue = maximumValue;
    }
}
