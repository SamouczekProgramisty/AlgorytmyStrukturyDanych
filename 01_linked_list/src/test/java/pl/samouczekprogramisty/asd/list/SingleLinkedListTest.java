package pl.samouczekprogramisty.asd.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleLinkedListTest {

    private SingleLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new SingleLinkedList<>();
    }

    @Test
    void shouldSayThatEmptyListIsEmpty() {
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    void shouldSayThatListWithOneElementIsntEmpty() {
        list.add("12");
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    void shouldThrowExceptionWhenFetchingFromNegativeIndex() {
        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.get(-1)
        );
    }

    @Test
    void shouldThrowExceptionWhenFetchingFromEmptyList() {
        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.get(0)
        );
    }

    @Test
    void shouldTronwExceptionWhenRemovingFromEmptyList() {
        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.remove(0)
        );
    }

    @Test
    void shouldSayThatEmptyListHasSize0() {
        assertThat(list.size(), is(0));
    }

    @Test
    void shouldGetValidSizeForListWithOneElement() {
        list.add("something)");
        assertThat(list.size(), is(1));
    }

    @Test
    void shouldFetchElementFromList() {
        list.add("something");
        assertThat(list.get(0), is("something"));
    }

    @Test
    void shouldGetSizeForListWithMoreElements() {
        list.add("something");
        list.add("else");
        list.add("is");
        list.add("here");
        assertThat(list.size(), is(4));
    }

    @Test
    void shouldGetValidElementFromListWithMoreElements() {
        list.add("something");
        list.add("else");
        list.add("is");
        list.add("here");

        assertThat(list.get(1), is("else"));
        assertThat(list.get(2), is("is"));
        assertThat(list.get(3), is("here"));
    }

    @Test
    void shouldBeAbleToClearList() {
        list.add("abc");
        list.clear();

        assertThat(list.isEmpty(), is(true));
    }

    @Test
    void shouldReturnOldValueWhileSettingNew() {
        list.add("abc");
        String oldValue = list.set(0, "cab");

        assertThat(oldValue, is("abc"));
    }

    @Test
    void shouldReplaceOldValueWithNew() {
        list.add("abc");
        list.set(0, "cab");

        assertThat(list.get(0), is("cab"));
    }

    @Test
    void shouldRemoveFirstElement() {
        list.add("abc");
        list.add("bc");
        list.add("c");

        String removed = list.remove(0);

        assertThat(removed, is("abc"));
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is("bc"));
    }

    @Test
    void shouldRemoveMiddleElement() {
        list.add("abc");
        list.add("bc");
        list.add("c");

        String removed = list.remove(1);

        assertThat(removed, is("bc"));
        assertThat(list.size(), is(2));
        assertThat(list.get(1), is("c"));
    }

    @Test
    void shouldRemoveLastElement() {
        list.add("abc");
        list.add("bc");
        list.add("c");

        String removed = list.remove(2);

        assertThat(removed, is("c"));
        assertThat(list.size(), is(2));
        assertThat(list.get(1), is("bc"));
    }

    @Test
    void shouldBeAbleToAddAtIndex() {
        list.add(0, "a");

        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("a"));
    }

    @Test
    void shouldKeepAddingAtIndex0() {
        list.add(0, "a");
        list.add(0, "b");
        list.add(0, "c");

        assertThat(list.size(), is(3));
        assertThat(list.get(0), is("c"));
        assertThat(list.get(1), is("b"));
        assertThat(list.get(2), is("a"));
    }

    @Test
    void shouldThrowAnExceptionWhenAddingOnNonExistingIndex() {
        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.add(1, "a")
        );
    }

    @Test
    void shouldThrowAnExceptionWhenAddingOnNonExistingIndexWithAlreadyFilledList() {
        list.add(0, "a");

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.add(2, "nope")
        );
    }

    @Test
    void shouldKeepAddingAtTheEnd() {
        list.add(0, "a");
        list.add(1, "b");
        list.add(2, "c");

        assertThat(list.size(), is(3));
        assertThat(list.get(0), is("a"));
        assertThat(list.get(1), is("b"));
        assertThat(list.get(2), is("c"));
    }

    @Test
    void shouldRemoveLastElementFromList() {
        list.add("a");
        list.remove(0);

        assertThat(list.first, is(nullValue()));
    }
}