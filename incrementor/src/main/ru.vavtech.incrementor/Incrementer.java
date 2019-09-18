/**
 * @author Alexander Vdovenkov
 * Date: 018 18.09.19
 */
public interface Incrementer {

    /**
     * Возвращает текущее значение счетчика. В самом начале равно нулю.
     * @return - текущее значение счетчика.
     */
    int getNumber();

    /**
     * Инкрементирует текущее значение. После каждого вызова этого
     * метода {@code getNumber()} будет возвращать увеличенное значение.
     */
    void incrementNumber();

    /**
     * Устанавливает максимальное значение счетчика.
     * Когда при вызове {@code incrementNumber()} текущее значение достигает
     * этого, оно обнуляется, т.е. {@code getNumber()} начинает
     * снова возвращать ноль, и снова один после следующего
     * вызова {@code incrementNumber()} и так далее.
     * По умолчанию максимум -- максимальное значение типа int.
     * Если при смене максимального значения число начинает
     * превышать максимальное значение, то обнуляем значение.
     * Нельзя позволять установить тут число меньше нуля.
     * @param maximumValue - максимальное значение счетчика
     */
    void setMaximumValue(int maximumValue);
}
