package com.example.alex.colorapp;

/**
 * Created by alex on 11/25/15.
 */
public interface GetUserCallback {
    /**
     * Invoked when background task is completed
     */

    public abstract void done(User returnedUser);
}
