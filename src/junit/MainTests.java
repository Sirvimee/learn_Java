package junit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class MainTests {

    @Test
    public void findsSpecialNumbers() {
        assertTrue(Code.isSpecial(0));
        assertTrue(Code.isSpecial(1));
        assertTrue(Code.isSpecial(2));
        assertTrue(Code.isSpecial(3));
        assertFalse(Code.isSpecial(4));
        assertTrue(Code.isSpecial(11));
        assertFalse(Code.isSpecial(15));
        assertTrue(Code.isSpecial(36));
        assertFalse(Code.isSpecial(37));
    }

    @Test
    public void findsLongestStreak() {
        assertThat(Code.longestStreak(null), is(0));
        assertThat(Code.longestStreak(""), is(0));
        assertThat(Code.longestStreak("aab"), is(2));
        assertThat(Code.longestStreak("abb"), is(2));
        assertThat(Code.longestStreak("abbcccaaaad"), is(4));
    }

    @Test
    public void findsModeFromCharactersInString() {
        assertThat("", Code.mode(""), is(nullValue()));
        assertThat(null, Code.mode(null), is(nullValue()));
        assertThat("abcb", Code.mode("abcb"), is('b'));
        assertThat("cbbc", Code.mode("cbbc"), is('c'));
    }

    @Test
    public void countingCharactersInString() {
        assertThat("abcb", Code.getCharacterCount("abcb", 'a'), is(1));
        assertThat("abcb", Code.getCharacterCount("abcb", 'b'), is(2));
        assertThat("cbbc", Code.getCharacterCount("cbbc", 'c'), is(2));
    }

    @Test
    public void checkNumberIsInString() {
        assertTrue(Code.numberIsInString("1235 556 423", "556"));
        assertFalse(Code.numberIsInString("1235", "4"));
        assertFalse(Code.numberIsInString("1235", "1"));
    }

    @Test
    public void removesDuplicates() {
        assertThat(Code.removeDuplicates(arrayOf(1, 1)), is(arrayOf(1)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 1, 3, 2)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 3)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(100, 0, 3, 100, 0, 4, 562, 4)),
                is(arrayOf(100, 0, 3, 4, 562)));
    }

    @Test
    public void sumsIgnoringDuplicates() {
        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 1)), is(1));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 1, 3, 2)), is(6));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 3)), is(6));
    }

    private int[] arrayOf(int... numbers) {
        return numbers;
    }

}
