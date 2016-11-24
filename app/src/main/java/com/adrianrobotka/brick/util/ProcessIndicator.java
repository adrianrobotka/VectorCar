package com.adrianrobotka.brick.util;

/**
 * Callback to indicate a process' status
 */
public interface ProcessIndicator {
    /**
     * Callback to set a process percentage
     *
     * @param percentage Process' percentage
     */
    void setProcessPercentage(int percentage);
}
