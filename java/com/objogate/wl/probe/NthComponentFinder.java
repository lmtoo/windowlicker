package com.objogate.wl.probe;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Description;
import com.objogate.wl.ComponentFinder;
import com.objogate.wl.ComponentSelector;

public class NthComponentFinder<T extends Component> implements ComponentFinder<T>, ComponentSelector<T> {
    private ComponentFinder<T> parentOrOwnerFinder;
    private int whichToChoose;

    public NthComponentFinder(ComponentFinder<T> parentOrOwnerFinder, int whichToChoose) {
        this.parentOrOwnerFinder = parentOrOwnerFinder;
        this.whichToChoose = whichToChoose;
    }

    public List<T> components() {
        return Arrays.asList(parentOrOwnerFinder.components().get(whichToChoose));
    }

    public T component() {
        return parentOrOwnerFinder.components().get(whichToChoose);
    }

    public void probe() {
        parentOrOwnerFinder.probe();
    }

    public boolean isSatisfied() {
        return parentOrOwnerFinder.isSatisfied() && parentOrOwnerFinder.components().size() > whichToChoose;
    }

    public void describeTo(Description description) {
        description.appendText("The " );
        description.appendValue(whichToChoose);
        description.appendText(" component from those matching ");
        description.appendDescriptionOf(parentOrOwnerFinder);
    }
}
