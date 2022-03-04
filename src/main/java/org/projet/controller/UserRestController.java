package org.projet.controller;

import java.util.List;
import java.util.Optional;

import org.formation.controller.MemberNotFoundException;
import org.formation.controller.MemberViews;
import org.formation.model.Member;
import org.projet.model.User;
import org.projet.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
		@Autowired
		UserRepository userRepository;
		
		@GetMapping
		public List <User> findAllUsers(){
			return userRepository.findAll();
		}
		
		@GetMapping("/{idUser}")
		public Optional<User> findUserById(@PathVariable Long idUser) {
			return userRepository.findById(idUser);
		}
		
		@GetMapping("/search")
		public Optional<User> findUserByEmail(@RequestParam String email){
			return userRepository.findByEmail(email);
		}
		
		@PostMapping
		public ResponseEntity <User> createMember(@RequestBody User user) {
			if (userRepository.findByEmail(user.getEmail()) != null){
				String messageErreur = "Email déjà utilisé";
				return new ResponseEntity <User> (messageErreur, HttpStatus.NOT_ACCEPTABLE);
			};

			
			
			user = userRepository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		}
		
		@PutMapping
		@JsonView(MemberViews.OneMember.class)
		public Member updateMember(@RequestBody Member member) throws MemberNotFoundException {
			memberRepository.findById(member.getId()).orElseThrow(()-> new MemberNotFoundException());
			memberRepository.save(member);
			return member;
		}
}
