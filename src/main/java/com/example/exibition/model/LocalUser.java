package com.example.exibition.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "username" }, name = "username_unique"),
		@UniqueConstraint(columnNames = { "email" }, name = "email_unique")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalUser implements Serializable {

	private static final long serialVersionUID = -6319767203201271940L;

	private static final String SEQUENCE_NAME = "LOCAL_USER_SEQ";

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String email;
	
	private String password;
	private Boolean enable;
	private LocalDateTime createdAt;
	
	@Builder.Default
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="user_role",
        joinColumns= {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> roles = new HashSet<>();
	 

//	public void addRole(Role role) {
//		this.roles.add(role);
//		role.getUsuarios().add(this);
//	}
//
//
//	public void removeRole(Role role) {
//		this.roles.remove(role);
//		role.getUsuarios().remove(this);
//	}
}
