package com.zwb.lazyload;

public class Ptr<T> 
{
	T value;
	
	public Ptr()
	{
		
	}
	
	public Ptr(T value)
	{
		this.value = value;
	}
	
	public T getValue()
	{
		return this.value;
	}
	
	public void setValue(T value)
	{
		this.value = value;
	}
}

