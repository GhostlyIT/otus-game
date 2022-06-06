package org.sunsetcode.gameobject;

public interface UObject
{
    <T> T getProperty(String key);
    void setProperty(String key, Object newValue);
}
