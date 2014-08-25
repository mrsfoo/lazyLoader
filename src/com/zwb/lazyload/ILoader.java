package com.zwb.lazyload;

public interface ILoader<T>
{
	public <T> T load();
}
