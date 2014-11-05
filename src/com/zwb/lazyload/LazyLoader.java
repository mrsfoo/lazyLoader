package com.zwb.lazyload;

public class LazyLoader
{
    public static <T> T loadLazy(Ptr<T> member, ILoader<T> loader)
    {
	if (member.getValue() == null)
	{
	    member.setValue(loader.load());
	}
	return member.getValue();
    }
    
}
