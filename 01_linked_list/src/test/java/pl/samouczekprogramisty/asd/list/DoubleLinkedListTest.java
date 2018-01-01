package pl.samouczekprogramisty.asd.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DoubleLinkedListTest {

    private DoubleLinkedList<String> list;

    @Before
    public void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Test
    public void shouldSayThatEmptyListIsEmpty() {
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void shouldSayThatListWithOneElementIsntEmpty() {
        list.add("12");
        assertThat(list.isEmpty(), is(false));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenFetchingFromNegativeIndex() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenFetchingFromEmptyList() {
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldTronwExceptionWhenRemovingFromEmptyList() {
        list.remove(0);
    }

    @Test
    public void shouldSayThatEmptyListHasSize0() {
        assertThat(list.size(), is(0));
    }

    @Test
    public void shouldGetValidSizeForListWithOneElement() {
        list.add("something)");
        assertThat(list.size(), is(1));
    }

    @Test
    public void shouldFetchElementFromList() {
        list.add("something");
        assertThat(list.get(0), is("something"));
    }

    @Test
    public void shouldGetSizeForListWithMoreElements() {
        list.add("something");
        list.add("else");
        list.add("is");
        list.add("here");
        assertThat(list.size(), is(4));
    }

    @Test
    public void shouldGetValidElementFromListWithMoreElements() {
        list.add("something");
        list.add("else");
        list.add("is");
        list.add("here");

        assertThat(list.get(1), is("else"));
        assertThat(list.get(2), is("is"));
        assertThat(list.get(3), is("here"));
    }

    @Test
    public void shouldBeAbleToClearList() {
        list.add("abc");
        list.clear();

        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnOldValueWhileSettingNew() {
        list.add("abc");
        String oldValue = list.set(0, "cab");

        assertThat(oldValue, is("abc"));
    }

    @Test
    public void shouldReplaceOldValueWithNew() {
        list.add("abc");
        list.set(0, "cab");

        assertThat(list.get(0), is("cab"));
    }

    @Test
    public void shouldRemoveFirstElement() {
        list.add("abc");
        list.add("bc");
        list.add("c");

        String removed = list.remove(0);

        assertThat(removed, is("abc"));
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is("bc"));
    }

    @Test
    public void shouldRemoveMiddleElement() {
        list.add("abc");
        list.add("bc");
        list.add("c");

        String removed = list.remove(1);

        assertThat(removed, is("bc"));
        assertThat(list.size(), is(2));
        assertThat(list.get(1), is("c"));
    }

    @Test
    public void shouldRemoveLastElement() {
        list.add("abc");
        list.add("bc");
        list.add("c");

        String removed = list.remove(2);

        assertThat(removed, is("c"));
        assertThat(list.size(), is(2));
        assertThat(list.get(1), is("bc"));
    }

    @Test
    public void shouldBeAbleToAddAtIndex() {
        list.add(0, "a");

        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("a"));
    }

    @Test
    public void shouldKeepAddingAtIndex0() {
        list.add(0, "a");
        list.add(0, "b");
        list.add(0, "c");

        assertThat(list.size(), is(3));
        assertThat(list.get(0), is("c"));
        assertThat(list.get(1), is("b"));
        assertThat(list.get(2), is("a"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowAnExceptionWhenAddingOnNonExistingIndex() {
        list.add(1, "a");
    }

    @Test
    public void shouldThrowAnExceptionWhenAddingOnNonExistingIndexWithAlreadyFilledList() {
        list.add(0, "a");

        try {
            list.add(2, "nope");
            fail("IndexOutOfBountsException should have been thrown!");
        } catch (IndexOutOfBoundsException e) {
            // that's expected
        }
    }

    @Test
    public void shouldKeepAddingAtTheEnd() {
        list.add(0, "a");
        list.add(1, "b");
        list.add(2, "c");

        assertThat(list.size(), is(3));
        assertThat(list.get(0), is("a"));
        assertThat(list.get(1), is("b"));
        assertThat(list.get(2), is("c"));
    }

}