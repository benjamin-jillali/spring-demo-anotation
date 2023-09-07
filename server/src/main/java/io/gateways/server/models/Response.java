package io.gateways.server.models;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;
//lombok
@Data
//extends builder creates private constructor with all fields
@SuperBuilder
//only want to include values that are not null
//will send for success or failure so not all values will be included so make sure to only send non null fields
@JsonInclude(Include.NON_NULL)
//class to send response messages for server operations
public class Response {
	protected LocalDateTime timeStamp;
	protected int statusCode;
	protected HttpStatus status;
	//error message response
	protected String reason;
	//success message response
	protected String message;
	//more technical message for devs or admins
	protected String developerMessage;
	//any type always sends data to front end
	protected Map<?, ?> data;
}
