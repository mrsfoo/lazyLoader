package com.zwb.lazyload.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.zwb.lazyload.ILoader;
import com.zwb.lazyload.LazyLoader;
import com.zwb.lazyload.Ptr;

public class LazyLoaderTest extends TestCase
{
	public static int intCnt;
	public static int stringCnt;
	public static int listCnt;
	
	public void testLoader()
	{
		Clazz c = new Clazz();
		for(int n=0; n<10; n++)
		{
			int i = c.getInt();
			String s = c.getString();
			List<String> l = c.getList();
			assertEquals(42, i);
			assertEquals("zweiundvierzig", s);
			List<String> comp = Arrays.asList("foo", "bar", "baz");
			assertEquals(comp.size(), l.size());
			for(int m=0; m<comp.size(); m++)
			{
				assertEquals(comp.get(m), l.get(m));
			}
		}
		assertEquals(1, intCnt);
		assertEquals(1, stringCnt);
		assertEquals(1, listCnt);
	}
}

class Clazz
{
	private Ptr<Integer> i = new Ptr<>();
	private Ptr<String> s = new Ptr<>();
	private Ptr<List<String>> l = new Ptr<>();
	
	public int getInt()
	{
		Integer in = LazyLoader.loadLazy(this.i, new LoaderClazzInt());
		return in;
	}
	
	public String getString()
	{
		return LazyLoader.loadLazy(this.s, new LoaderClazzString());
	}
	
	public List<String> getList()
	{
		return LazyLoader.loadLazy(this.l, new LoaderClazzList());		
	}
}

class LoaderClazzInt implements ILoader<Integer>
{
	@Override
	public Integer load() 
	{
		LazyLoaderTest.intCnt++;
		return 42;
	}
	
}

class LoaderClazzString implements ILoader<String>
{
	@Override
	public String load() 
	{
		LazyLoaderTest.stringCnt++;
		return "zweiundvierzig";
	}
	
}

class LoaderClazzList implements ILoader
{
	@Override
	public ArrayList<String> load() 
	{
		LazyLoaderTest.listCnt++;
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList("foo","bar","baz"));
		return list;
	}
	
}