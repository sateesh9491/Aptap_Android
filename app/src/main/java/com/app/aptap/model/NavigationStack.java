package com.app.aptap.model;

/**
 * Created by ry41071 on 24-11-2015.
 */
public class NavigationStack {
    String name;
    boolean isActive;
    int selectedThumb;
    int unselectedThumb;
    int value;

    public NavigationStack(String name, boolean isActive, int selectedThumb, int unselectedThumb, int value) {
        this.name = name;
        this.isActive = isActive;
        this.selectedThumb = selectedThumb;
        this.unselectedThumb = unselectedThumb;
        this.value = value;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getSelectedThumb() {
        return selectedThumb;
    }

    public void setSelectedThumb(int selectedThumb) {
        this.selectedThumb = selectedThumb;
    }

    public int getUnselectedThumb() {
        return unselectedThumb;
    }

    public void setUnselectedThumb(int unselectedThumb) {
        this.unselectedThumb = unselectedThumb;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}