package com.licnosoft.linkedin.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "chats")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
