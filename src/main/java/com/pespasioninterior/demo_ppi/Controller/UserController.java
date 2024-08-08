package com.pespasioninterior.demo_ppi.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pespasioninterior.demo_ppi.Dto.UserDto;
import com.pespasioninterior.demo_ppi.Security.Controller.Mensaje;
import com.pespasioninterior.demo_ppi.Security.Entity.User;
import com.pespasioninterior.demo_ppi.Service.UserService;

@RestController
@RequestMapping("/userDetail")
@CrossOrigin(origins = "localhost:4200")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable ("id") int id){
		User user = userService.getUser(id).get();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UserDto userDto){
		if(!userService.existsById(id)) {
			return new ResponseEntity<>(new Mensaje("El usuario no existe..."), HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.isBlank(userDto.getEmail()) && !StringUtils.isBlank(userDto.getUsername()) && StringUtils.isBlank(userDto.getPassword()) && 
				StringUtils.isBlank(userDto.getFirstname()) && StringUtils.isBlank(userDto.getLastname()) &&
				StringUtils.isBlank(userDto.getCountry()) && StringUtils.isBlank(userDto.getImg())) {
			return new ResponseEntity<>(new Mensaje("Complete todos los campos para actualizar..."), HttpStatus.BAD_REQUEST);
		}
		
		User user = userService.getUser(id).get();
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setCountry(userDto.getCountry());
		user.setImg(userDto.getImg());
		
		userService.saveUser(user);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}