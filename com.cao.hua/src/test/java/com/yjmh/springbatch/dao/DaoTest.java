package com.yjmh.springbatch.dao;

import org.junit.Test;

import junit.framework.TestCase;

public class DaoTest extends TestCase {

	@Test
	public void testGetConnection() {
		Dao dao = new Dao();
		dao.getConnection();
	}

	@Test
	public void testQueryDataFromDBTable() {
		Dao dao = new Dao();
		
	}

}
