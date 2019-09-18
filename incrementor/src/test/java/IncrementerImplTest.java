import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexander Vdovenkov
 * Date: 018 18.09.19
 */
class IncrementerImplTest {

    /** Экземпляр инкрементера для тестов */
    private Incrementer incrementer;


    /** Инициализируем счетчик перед каждым тестом */
    @BeforeEach
    void initIncrementer() {
        incrementer = new IncrementerImpl();
    }


    /**
     * Проверяем {@code getNumber}. Сразу после инициализации метод должен возвращать 0.
     */
    @Test
    void getNumberTest() {
        assertEquals(0, incrementer.getNumber(), () ->
                MessageFormat.format("Ожидалось значение {0}, а метод getNumber вернул {1}!", 0, incrementer.getNumber()));
    }

    /**
     * Проверяем совместную работу {@code incrementNumber} и {@code getNumber}.
     */
    @Test
    void getNumberAndIncrementTest() {
        assertDoesNotThrow(() -> incrementer.incrementNumber());
        assertEquals(1, incrementer.getNumber(), () ->
                MessageFormat.format("Ожидалось значение {0}, метод getNumber() вернул {1}", 1, incrementer.getNumber()));
    }


    /**
     * Проверяем что метод {@code incrementNumber} не выбрасывает исключений
     */
    @Test
    void incrementNumberTest() {
        assertDoesNotThrow(() -> incrementer.incrementNumber());
    }

    /**
     * Проверяем работоспособность метода {@code setMaximumValue} с валидными параметрами
     */
    @Test
    void setMaximumValueTest() {
        incrementer.setMaximumValue(10);
    }

    /**
     * Проверяем метод {@code setMaximumValue} с невалидными параметрами.
     * Ожидаем {@link IllegalArgumentException}
     */
    @Test
    void setMaximumValueExceptionTest() {
        int negativeNumber = -1;
        assertThrows(IllegalArgumentException.class, () -> incrementer.setMaximumValue(negativeNumber));
    }

    /**
     * Проверяем обнуление значения счетчика, при достижении максимального значения.
     */
    @Test
    void setMaximumValueAndIncrementTest() {
        int maxCounterValue = 10;
        assertDoesNotThrow(() -> incrementer.setMaximumValue(maxCounterValue));
        for (int i = 0; i < maxCounterValue; i++) {
            assertDoesNotThrow(() -> incrementer.incrementNumber());
        }
        assertEquals(incrementer.getNumber(), maxCounterValue, () ->
                MessageFormat.format("Ожидалось значение {0}, getNumber() вернул {1}", maxCounterValue, incrementer.getNumber()));
        assertDoesNotThrow(() -> incrementer.incrementNumber());
        assertEquals(0, incrementer.getNumber(), () ->
                MessageFormat.format("Ожидалось значение {0}, getNumber() вернул {1}", 0, incrementer.getNumber()));
    }

    /**
     * Проверяем обнуление значения счетчика, если при смене максимального значения, текущее начинает его (максимальное) превышать.
     */
    @Test
    void changeMaximumValueTest() {
        int maxValue = 10;
        assertDoesNotThrow(() -> incrementer.setMaximumValue((maxValue)));
        for (int i = 0; i < 3; i++) {
            assertDoesNotThrow(() -> incrementer.incrementNumber());
        }
        assertDoesNotThrow(() -> incrementer.setMaximumValue(2));
        assertEquals(0, incrementer.getNumber(), () ->
                MessageFormat.format(("Ожидалось значение {0}, getNumber() вернул {1}"), 0, incrementer.getNumber()));
    }
}