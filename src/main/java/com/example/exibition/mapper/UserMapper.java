package com.example.exibition.mapper;

import com.example.exibition.bean.UserPrincipal;
import com.example.exibition.model.LocalUser;

public class UserMapper {

	private UserMapper() {}
	
	public static UserPrincipal userToPrincipal(LocalUser user) {
		if(user == null) {
			return null;
		}
        return new UserPrincipal(user);
	}
}
