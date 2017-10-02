package com.bcubbo.dao;

import java.util.List;

import com.bcubbo.pojo.Role;

public interface RoleMapper {
	
	
	public void add(Role role);
	
	public void update(Role role);
	
	public void delete(Role role);
	
	public List<Role> getRoleList(Role role);

}
