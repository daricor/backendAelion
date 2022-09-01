/**
 * 
 */
package com.aelion.suivi.services.exception;

import org.springframework.http.ResponseEntity;

/**
 * @author Jokey
 *
 */
public interface HttpErrorInterface{
		public ResponseEntity<String> send();
}
