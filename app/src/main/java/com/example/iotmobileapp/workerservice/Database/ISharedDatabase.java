package com.example.iotmobileapp.workerservice.Database;

import java.util.List;

public interface ISharedDatabase<T>
{
    void insert(T data);

    T take();

    List<T> take(int number);

    int count();
}
