package com.example.ec3milla;

import androidx.fragment.app.Fragment;

public interface NavigationHost
{
    void navigateTo(Fragment fragment, boolean addToBackstack);
}
