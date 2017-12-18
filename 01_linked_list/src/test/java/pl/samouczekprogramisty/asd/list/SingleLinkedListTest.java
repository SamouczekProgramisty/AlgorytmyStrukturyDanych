package pl.samouczekprogramisty.asd.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SingleLinkedListTest {

    @Test
    public void shouldSayThatEmptyListIsEmpty() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void shouldSayThatListWithOneElementIsntEmpty() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(12);
        assertThat(list.isEmpty(), is(false));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenFetchingFromNegativeIndex() {
        new SingleLinkedList().get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenFetchingFromEmptyList() {
        new SingleLinkedList().get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldTronwExceptionWhenRemovingFromEmptyList() {
        new SingleLinkedList().remove(0);
    }

    @Test
    public void shouldSayThatEmptyListHasSize0() {
        assertThat(new SingleLinkedList<Integer>().size(), is(0));
    }

    @Test
    public void shouldGetValidSizeForListWithOneElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
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
}