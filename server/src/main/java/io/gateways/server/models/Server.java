package io.gateways.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import io.gateways.server.Enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//map to database annotation
//@Data comes from lombok for the constructors
//allargs and noargs constructors are lomboks 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//unique makes sure ipAddress column is unique
	@Column(unique = true)
	//@NotEmpty assures ipAddress isn't empty
	@NotEmpty(message = "IP address cannot be empty or null.")
	private String ipAddress;
	private String name;
	private String memory;
	private String type;
	private String imageUrl;
	private Status status;
	
	

}
