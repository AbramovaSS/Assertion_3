import org.example.Main;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;


public class BasicJavaTest {

    @BeforeEach
    void logStart() {
        System.out.println("===================Test method start");
    }

    @Tag("smoke")
    @RepeatedTest(10)
    public void testIsEven() {
        Random random = new Random();

        int n = random.nextInt(1, 101);

        if (n % 2 == 0) {
            assertThat(Main.isEven(n))
                    .as("Число " + n + " четное >> true")
                    .isTrue();
        } else {
            assertThat(Main.isEven(n))
                    .as("Число " + n + " нечетное >> false")
                    .isFalse();
        }

    }

    @RepeatedTest(20)
    public void testCheckAccess() {
        Random random = new Random();

        int age = random.nextInt(100);

        String expected = (age > 18) ? "Allowed" : "Denied";

        String actual = Main.checkAccess(age);

        assertThat(actual)
                .as("Проверка доступа для возраста " + age)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 18, 0, -1, 99, -1000})
    public void testIsPositive(int n) {

        boolean expected = (n >= 0);

        boolean actual = Main.isPositive(n);

        assertThat(actual)
                .as("Проверка знака для числа " + n)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "15,  E",
            "30,  D",
            "55,  C",
            "70,  B",
            "95,  A",
            "-5,  Error",
            "105, Error"
    })
    public void testGetGradeWithCsv(int score, String expectedGrade) {

        String actualGrade = Main.getGrade(score);

        assertThat(actualGrade)
                .as("Проверка оценки для набранного балла " + score)
                .isEqualTo(expectedGrade);
    }

    @RepeatedTest(3)
    public void testBlastOff() {
        Random random = new Random();

        int start = random.nextInt(0, 13);

        String expected = "";
        for (int i = start; i >= 1; i--) {
            expected = expected + i + " ";
        }
        expected = expected + "Поехали!";

        String actual = Main.blastOff(start);

        assertThat(actual)
                .as("Проверка обратного отсчета: " + start)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 1",
            "3, 6",
            "4, 10",
            "5, 15"
    })
    public void testSumToN(int n, int expected) {

        int actual = Main.sumToN(n);

        assertThat(actual)
                .as("Проверка обратного отсчета: " + n)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @EnumSource(Status.class)
    public void testHasBug(Status status) {
        String word = status.name();

        String[] messages = new String[]{word};

        boolean expected = word.equals("BUG");

        boolean actual = Main.hasBug(messages);

        assertThat(actual)
                .as("Проверка баг для статуса: " + status)
                .isEqualTo(expected);
    }

    @RepeatedTest(5)
    public void testGetEvenInRange() {
        Random random = new Random();

        int start = random.nextInt(1, 20);
        int end = start + random.nextInt(1, 15);

        String expected = "";
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                expected = expected + i + " ";
            }
        }
        expected = expected.trim();

        String actual = Main.getEvenInRange(start, end);

        assertThat(actual)
                .as("Проверка четных чисел в диапазоне " + start + " - " + end)
                .isEqualTo(expected);
    }

    @Tag("smoke")
    @RepeatedTest(10)
    public void testFindMax() {
        Random random = new Random();

        int[] arr = new int[5];
        int expected = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100) - 50;

            if (arr[i] > expected) {
                expected = arr[i];
            }
        }

        int actual = Main.findMax(arr);

        assertThat(actual)
                .as("Поиск максимального числа в массиве: %s", Arrays.toString(arr))
                .isEqualTo(expected + 1);
    }

    @Test
    public void testReverse() {
        String[] arr = {"One", "Two", "Zero"};

        String[] actual = Main.reverse(arr);

        String[] expected = {"Zero", "Two", "One"};

        assertThat(actual)
                .as("Проверка реверса списка: %s", Arrays.toString(arr))
                .isEqualTo(expected);
    }

    @Test
    public void testCalcAverage() {
        Random random = new Random();

        int a = random.nextInt(1, 11);
        int b = random.nextInt(1, 11);
        int c = random.nextInt(1, 11);

        List<Integer> list = List.of(a, b, c);

        double expected = (a + b + c) / 3.0;

        double actual = Main.calcAverage(list);

        assertThat(actual)
                .as("Расчет среднего арифметического для списка: %s", list)
                .isCloseTo(expected, offset(0.0001));
    }

    @Tag("smoke")
    @RepeatedTest(10)
    public void testRemoveSpecificName() {
        List<String> list = List.of("Пьер", "Андрей", "Наташа", "Андрей");
        String nameToRemove = "Андрей";

        List<String> actual = Main.removeSpecificName(list, nameToRemove);

        List<String> expected = List.of("Пьер", "Наташа");

        assertThat(actual)
                .as("Удаление имени " + nameToRemove + " из списка %s", list)
                .isEqualTo(expected);

    }

    @AfterEach
    void logEnd() {
        System.out.println("Test method end");
        System.out.println("===================");

    }
}