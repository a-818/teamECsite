package com.internousdev.radish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.radish.dto.DestinationInfoDTO;
import com.internousdev.radish.util.DBConnector;

public class DestinationInfoDAO {

	DBConnector dbConnector=new DBConnector();
	Connection connection=dbConnector.getConnection();

	//宛先入力SQL文
	public int insert(String userId,String familyName,String firstName,String familyNameKana,String firstNameKana,String email,String telNumber,String userAddress){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		int count=0;

	String sql="INSERT INTO destination_info (user_id,family_name,first_name,family_name_kana,first_name_kana,email,tel_number,user_address,regist_date,update_date) values(?,?,?,?,?,?,?,?,now(),now())";

	try{
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, familyName);
		ps.setString(3, firstName);
		ps.setString(4, familyNameKana);
		ps.setString(5, firstNameKana);
		ps.setString(6, email);
		ps.setString(7, telNumber);
		ps.setString(8, userAddress);
		count=ps.executeUpdate();
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return count;
	}

	//宛先選択SQL文
	public List<DestinationInfoDTO>getDestinationInfo(String userId){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		List<DestinationInfoDTO>destinationInfoDTOList=new ArrayList<DestinationInfoDTO>();

	String sql="SELECT id,family_name,first_name,family_name_kana,first_name_kana,email,tel_number,user_address FROM destination_info WHERE user_id=?";

	try{
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userId);
		ResultSet rs=ps.executeQuery();

		while(rs.next()){
			DestinationInfoDTO dto=new DestinationInfoDTO();
			dto.setId(rs.getInt("id"));
			dto.setFamilyName(rs.getString("family_name"));
			dto.setFirstName(rs.getString("first_name"));
			dto.setFamilyNameKana(rs.getString("family_name_kana"));
			dto.setFirstNameKana(rs.getString("first_name_kana"));
			dto.setEmail(rs.getString("email"));
			dto.setTelNumber(rs.getString("tel_number"));
			dto.setUserAddress(rs.getString("user_address"));
			destinationInfoDTOList.add(dto);
		}
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return destinationInfoDTOList;
	}

	//宛先削除SQL文
	public int deleteDestination(String id){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

	String sql="DELETE FROM destination_info WHERE id=?";
	int count=0;

	try{
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		count=ps.executeUpdate();
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		try{
		con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return count;
	}

}
