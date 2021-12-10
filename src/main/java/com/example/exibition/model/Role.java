package com.example.exibition.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable {

	private static final long serialVersionUID = 1620850991685686210L;

	private static final String SEQUENCE_NAME = "ROLE_SEQ";

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	
	private RoleName name;

//	@Builder.Default
//	@ManyToMany(mappedBy = "roles")
//	private Set<LocalUser> usuarios = new HashSet<>();
	
//	public void addUser(LocalUser user) {
//		this.usuarios.add(user);
//		user.addRole(this);
//	}
//	
//	public void removeUser(LocalUser user) {
//		this.usuarios.remove(user);
//		user.removeRole(this);
//	}
}
