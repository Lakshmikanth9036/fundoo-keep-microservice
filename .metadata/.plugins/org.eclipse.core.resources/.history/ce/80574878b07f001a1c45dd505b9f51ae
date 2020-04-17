package com.bridgelabz.fundookeep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomeGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ResponseError> customeException(UserException ex){
		ResponseError error = new ResponseError();
		error.setError(ex.getErrorMsg());
		error.setStatus(ex.getErrorCode());
		return new ResponseEntity<ResponseError>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoteException.class)
	public ResponseEntity<ResponseError> noteException(NoteException ex){
		ResponseError error = new ResponseError();
		error.setError(ex.getErrorMsg());
		error.setStatus(ex.getErrorCode());
		return new ResponseEntity<ResponseError>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ResponseError> customeTokenException(TokenException ex){
		ResponseError error = new ResponseError();
		error.setError(ex.getErrorMsg());
		error.setStatus(ex.getErrorCode());
		return new ResponseEntity<ResponseError>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidHeaderException.class)
	public ResponseEntity<ResponseError> customeTokenFormateException(InvalidHeaderException ex){
		ResponseError error = new ResponseError();
		error.setError(ex.getErrorMsg());
		error.setStatus(ex.getErrorCode());
		return new ResponseEntity<ResponseError>(error,HttpStatus.BAD_REQUEST);
	}
	
}
