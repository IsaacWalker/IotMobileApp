package com.example.iotmobileapp.workerservice.Database;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SharedDatabase<T> implements ISharedDatabase<T>
{
    private final Stack<T> m_stack = new Stack();


    @Override
    public void insert(T data) {
        m_stack.push(data);
    }

    @Override
    public T take() {
        return (m_stack.empty()) ? null : m_stack.pop();
    }

    @Override
    public List<T> take(int number)
    {
        List<T> list = new ArrayList<T>();

        while(!m_stack.empty() && number != 0)
        {
            list.add(m_stack.pop());
            number--;
        }

        return list;
    }

    @Override
    public int count() {
        return m_stack.size();
    }
}
