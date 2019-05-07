package pl.samouczekprogramisty.asd.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SimpleHashSetTest {

    private SimpleSet<String> set;

    @BeforeEach
    void setUp() {
        set = new SimpleHashSet<>();
    }

    @Test
    void shouldReportEmptySet() {
        assertThat(set.size(), is(0));
    }

    @Test
    void shouldntContainAnyItems() {
        assertThat(set.contains("dummy"), is(false));
    }

    @Test
    void shouldContainPreviouslyAddedItem() {
        set.add("dummy");
        assertThat(set.contains("dummy"), is(true));
    }

    @Test
    void shouldReportNewlyAddedItem() {
        assertThat(set.add("dummy"), is(true));
    }

    @Test
    void shouldReportExistingItemDuringAddition() {
        set.add("dummy");
        assertThat(set.add("dummy"), is(false));
    }

    @Test
    void shouldAddSingleItem() {
        set.add("dummy");
        set.add("dummy");
        assertThat(set.size(), is(1));
    }

    @Test
    void shouldReportMissingItemDuringDeletion() {
        assertThat(set.remove("dummy"), is(false));
    }

    @Test
    void shouldReportItemRemoval() {
        set.add("dummy");
        assertThat(set.remove("dummy"), is(true));
    }

    @Test
    void shouldReturnSetSize() {
        for (int i = 0; i < 10; i++) {
            set.add("dummy" + i);
        }
        assertThat(set.size(), is(10));
    }

    @Test
    void shouldRemoveItem() {
        set.add("dummy");
        set.remove("dummy");
        assertThat(set.contains("dummy"), is(false));
    }

}