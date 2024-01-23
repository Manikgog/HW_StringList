package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StringArrayListTest {
    public static StringArrayList stringArrayList;

    @BeforeEach
    void initStringArrayList(){
        stringArrayList = new StringArrayList();
    }

    @Test
    public void positiveAdd_Test(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            String actualString = stringArrayList.add(Constants.arrayStrings[i]);
            Integer actualSize = stringArrayList.size();
            Assertions.assertEquals(actualString, Constants.arrayStrings[i]);
            Assertions.assertEquals(actualSize, i+1);
        }
    }

    @Test
    public void negativeAddTest(){
        Assertions.assertThrows(NullParameterException.class, ()->stringArrayList.add(null));
    }

    @Test
    public void positiveAddIndexTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        int beforeAddingSize = stringArrayList.size();
        String actualString = stringArrayList.add(0, "Роберт");
        int actualSize = stringArrayList.size();
        int expectedSize = beforeAddingSize + 1;
        Assertions.assertEquals("Роберт", actualString);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals("Роберт", stringArrayList.get(0));

        beforeAddingSize = stringArrayList.size();
        actualString = stringArrayList.add(5, "Дмитрий");
        actualSize = stringArrayList.size();
        expectedSize = beforeAddingSize + 1;
        Assertions.assertEquals("Дмитрий", actualString);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals("Дмитрий", stringArrayList.get(5));

        beforeAddingSize = stringArrayList.size();
        actualString = stringArrayList.add(stringArrayList.size()-1, "Дмитрий");
        actualSize = stringArrayList.size();
        expectedSize = beforeAddingSize + 1;
        Assertions.assertEquals("Дмитрий", actualString);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals("Дмитрий", stringArrayList.get(stringArrayList.size()-2));

    }

    @Test
    public void negativeAddByIndexTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.add(-1, "Игнат"));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.add(11, "Игнат"));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.add(12, "Игнат"));
        Assertions.assertThrows(NullParameterException.class, ()->stringArrayList.add(12, null));
    }

    @Test
    public void positiveSetTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        String actualString = stringArrayList.set(0, "Игнат");
        Assertions.assertEquals("Игнат", actualString);
        Assertions.assertEquals("Игнат", stringArrayList.get(0));

        actualString = stringArrayList.set(5, "Федор");
        Assertions.assertEquals("Федор", actualString);
        Assertions.assertEquals("Федор", stringArrayList.get(5));
    }

    @Test
    public void negativeSetTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.set(-1, "Игнат"));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.set(11, "Игнат"));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.set(12, "Игнат"));
        Assertions.assertThrows(NullParameterException.class, ()->stringArrayList.set(12, null));

    }

    @Test
    public void positiveRemove_Test() {
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        for (int i = 0, expectedSize = stringArrayList.size()-1; i < Constants.arrayStrings.length; i++, expectedSize--) {
            String actualString = stringArrayList.remove(Constants.arrayStrings[i]);
            Integer actualSize = stringArrayList.size();
            Assertions.assertEquals(expectedSize, actualSize);
        }
    }



    @Test
    public void negativeRemove_Test() {
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        Assertions.assertThrows(IllegalArgumentException.class, ()->stringArrayList.remove("Марина"));
        Assertions.assertThrows(IllegalArgumentException.class, ()->stringArrayList.remove("Наталья"));
        Assertions.assertThrows(IllegalArgumentException.class, ()->stringArrayList.remove("Елена"));
        Assertions.assertThrows(NullParameterException.class, ()->stringArrayList.remove(null));
    }

    @Test
    public void positiveRemoveByIndex_Test(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }

        String expectedString = Constants.arrayStrings[0];
        String actualString = stringArrayList.remove(0);
        int expectedSize = Constants.arrayStrings.length - 1;
        Assertions.assertEquals(expectedString, actualString);
        Assertions.assertEquals(expectedSize, stringArrayList.size());

        expectedString = Constants.arrayStrings[4];
        actualString = stringArrayList.remove(3);
        expectedSize = Constants.arrayStrings.length - 2;
        Assertions.assertEquals(expectedString, actualString);
        Assertions.assertEquals(expectedSize, stringArrayList.size());

        expectedString = Constants.arrayStrings[9];
        actualString = stringArrayList.remove(7);
        expectedSize = Constants.arrayStrings.length - 3;
        Assertions.assertEquals(expectedString, actualString);
        Assertions.assertEquals(expectedSize, stringArrayList.size());
    }

    @Test
    public void negativeRemoveByIndex_Test(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.remove(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.remove(11));
    }

    @Test
    public void containsTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        Assertions.assertTrue(stringArrayList.contains("Иван"));
        Assertions.assertTrue(stringArrayList.contains("Сергей"));
        Assertions.assertTrue(stringArrayList.contains("Василий"));

        Assertions.assertFalse(stringArrayList.contains("Елена"));
        Assertions.assertFalse(stringArrayList.contains("Наталья"));
        Assertions.assertFalse(stringArrayList.contains("Марина"));

        Assertions.assertThrows(NullParameterException.class, ()->stringArrayList.contains(null));
    }

    @Test
    public void indexOfTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }

        Assertions.assertEquals(0, stringArrayList.indexOf("Иван"));
        Assertions.assertEquals(4, stringArrayList.indexOf("Андрей"));
        Assertions.assertEquals(7, stringArrayList.indexOf("Илья"));

        Assertions.assertEquals(-1, stringArrayList.indexOf("Елена"));
        Assertions.assertEquals(-1, stringArrayList.indexOf("Наталья"));

        Assertions.assertThrows(NullParameterException.class, ()->stringArrayList.indexOf(null));
    }

    @Test
    public void lastIndexOfTest(){
        for (int i = 0; i < Constants.arrayStringsWithEqualsStrings.length; i++) {
            stringArrayList.add(Constants.arrayStringsWithEqualsStrings[i]);
        }

        Assertions.assertEquals(6, stringArrayList.lastIndexOf("Иван"));
        Assertions.assertEquals(10, stringArrayList.lastIndexOf("Сергей"));
        Assertions.assertEquals(8, stringArrayList.lastIndexOf("Юрий"));

        Assertions.assertEquals(-1, stringArrayList.lastIndexOf("Елена"));
        Assertions.assertEquals(-1, stringArrayList.lastIndexOf("Наталья"));
        Assertions.assertThrows(NullParameterException.class, ()->stringArrayList.lastIndexOf(null));
    }

    @Test
    public void getTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }

        Assertions.assertEquals("Сергей", stringArrayList.get(1));
        Assertions.assertEquals("Евгений", stringArrayList.get(3));
        Assertions.assertEquals("Алексей", stringArrayList.get(5));
    }

    @Test
    public void negativeGetTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.get(14));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->stringArrayList.get(11));
    }

    @Test
    public void sizeTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        Assertions.assertEquals(11, stringArrayList.size());
        stringArrayList.remove(0);
        Assertions.assertEquals(10, stringArrayList.size());
        stringArrayList.remove(0);
        stringArrayList.remove(0);
        Assertions.assertEquals(8, stringArrayList.size());
    }

    @Test
    public void isEmptyTest(){
        Assertions.assertTrue(stringArrayList.isEmpty());

        stringArrayList.add(Constants.STRING_1);
        Assertions.assertFalse(stringArrayList.isEmpty());

    }

    @Test
    public void clearTest(){
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }
        stringArrayList.clear();
        Assertions.assertTrue(stringArrayList.isEmpty());
    }

    @Test
    public void toArrayTest(){
        StringArrayList testList = new StringArrayList();

        Assertions.assertTrue(Arrays.equals(testList.toArray(), stringArrayList.toArray()));

        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
        }

        Assertions.assertTrue(Arrays.equals(Constants.arrayStrings, stringArrayList.toArray()));

        stringArrayList.clear();

        for (int i = 0; i < Constants.arrayStringsWithEqualsStrings.length; i++) {
            stringArrayList.add(Constants.arrayStringsWithEqualsStrings[i]);
        }

        Assertions.assertTrue(Arrays.equals(Constants.arrayStringsWithEqualsStrings, stringArrayList.toArray()));
    }

    @Test
    public void equals_Test(){
        StringList stringList = new StringArrayList();
        StringList stringList1 = new StringArrayList();
        for (int i = 0; i < Constants.arrayStrings.length; i++) {
            stringArrayList.add(Constants.arrayStrings[i]);
            stringList.add(Constants.arrayStrings[i]);
        }
        for (int i = 0; i < Constants.arrayStringsWithEqualsStrings.length; i++) {
            stringList1.add(Constants.arrayStringsWithEqualsStrings[i]);
        }

        Assertions.assertTrue(stringArrayList.equals(stringList));

        Assertions.assertFalse(stringArrayList.equals(stringList1));
    }

}
