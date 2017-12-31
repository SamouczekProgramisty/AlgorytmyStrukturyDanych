package pl.samouczekprogramisty.asd.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DoubleLinkedListTest {

    @Test
    public void shouldSayThatEmptyListIsEmpty() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void shouldSayThatListWithOneElementIsntEmpty() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(12);
        assertThat(list.isEmpty(), is(false));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenFetchingFromNegativeIndex() {
        new DoubleLinkedList().get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenFetchingFromEmptyList() {
        new DoubleLinkedList().get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldTronwExceptionWhenRemovingFromEmptyList() {
        new DoubleLinkedList().remove(0);
    }

    @Test
    public void shouldSayThatEmptyListHasSize0() {
        assertThat(new DoubleLinkedList<Integer>().size(), is(0));
    }

    @Test
    public void shouldGetValidSizeForListWithOneElement() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("something)");
        assertThat(list.size(), is(1));
    }

    @Test
    public void shouldFetchElementFromList() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("something");
        assertThat(list.get(0), is("something"));
    }

    @Test
    public void shouldGetSizeForListWithMoreElements() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("something");
        list.add("else");
        list.add("is");
        list.add("here");
        assertThat(list.size(), is(4));
    }

    @Test
    public void shouldGetValidElementFromListWithMoreElements() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
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
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("abc");
        list.clear();

        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnOldValueWhileSettingNew() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("abc");
        String oldValue = list.set(0, "cab");

        assertThat(oldValue, is("abc"));
    }

    @Test
    public void shouldReplaceOldValueWithNew() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("abc");
        list.set(0, "cab");

        assertThat(list.get(0), is("cab"));
    }

    @Test
    public void shouldRemoveFirstElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
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
        SingleLinkedList<String> list = new SingleLinkedList<>();
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
        SingleLinkedList<String> list = new SingleLinkedList<>();
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
        SingleLinkedList<String> list = new SingleLinkedList<>();

        list.add(0, "a");

        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("a"));
    }

    @Test
    public void shouldKeepAddingAtIndex0() {
        SingleLinkedList<String> list = new SingleLinkedList<>();

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
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add(1, "a");
    }

    @Test
    public void shouldThrowAnExceptionWhenAddingOnNonExistingIndexWithAlreadyFilledList() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
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
        SingleLinkedList<String> list = new SingleLinkedList<>();

        list.add(0, "a");
        list.add(1, "b");
        list.add(2, "c");

        assertThat(list.size(), is(3));
        assertThat(list.get(0), is("a"));
        assertThat(list.get(1), is("b"));
        assertThat(list.get(2), is("c"));
    }

}