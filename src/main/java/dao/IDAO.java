package dao;

import java.util.ArrayList;

public interface IDAO <T> {
	public boolean ajout(T object);
	
public ArrayList<T> read();
}