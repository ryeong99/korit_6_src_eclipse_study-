package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;
import com.study.dvd.entity.Pro;
import com.study.dvd.util.DBConnectionMgr;

public class ProducerDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	public static List<Pro> searchDvdByProducerName(String searchText) {
		List<Pro> pros = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			StringBuilder sq = new StringBuilder();
			sq.append("select * from dvd_view ");
			sq.append("where producerName like ? limit 0,50");
			pstmt = con.prepareStatement(sq.toString());
			pstmt.setString(1, "%" + searchText + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Pro pro = Pro.builder()
						.producerId(rs.getInt(4))
						.producerName(rs.getString(5))
						.build();
				pros.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return pros;
	}
}
