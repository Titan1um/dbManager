package com.example.demo.models;

import java.sql.ResultSet;

public interface models<T> {
	int id = 0;

	T parseResultSet(ResultSet resultSet);
	String parseJson(String jsonStr);
}
