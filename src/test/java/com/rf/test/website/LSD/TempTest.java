package com.rf.test.website.LSD;

import com.rf.core.website.constants.dbQueries.DBQueries_RFO;

public class TempTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = DBQueries_RFO.GET_ALL_PC_CUSTOMER_INFO;
		x = x.replace("\n","").replace("\r","");
		System.out.println(x);
	}

}
