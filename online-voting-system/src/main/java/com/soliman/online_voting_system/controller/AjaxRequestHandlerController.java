package com.soliman.online_voting_system.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soliman.online_voting_system.model.ElectionModel;
import com.soliman.online_voting_system.model.PositionListModel;
import com.soliman.online_voting_system.service.ApplicationUtilityService;


@Controller
@RequestMapping("/ajaxrequest")
public class AjaxRequestHandlerController {

	@Resource(name = "myConstantMap")
	private Map<String, String> myConstantMap;
	
	@Autowired
	private ApplicationUtilityService appService;
	
	@RequestMapping(value = "/generateCandidateUI",
			method = RequestMethod.POST,
			 consumes = MediaType.ALL_VALUE,
		        produces = MediaType.ALL_VALUE,
		        headers = "Accept=*/*")
	public  String getCandidateByPost(
			@RequestBody String body,
			Model model
			) {
		
		ObjectMapper mapper = new ObjectMapper();
		ElectionModel election = null;
		try {
			election = mapper.readValue(body, ElectionModel.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PositionListModel positionObj = new PositionListModel(election.getElectionName(), election.getNumOfPosition());

		model.addAttribute("positionListModel",positionObj);
		
		return "partial/candidate";
	}
	
	@PostMapping("/postFileUpload")
	 public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file)
	         throws IOException {

	      String imageDir = myConstantMap.get("image_dir");
	      String randomImageName = "default.png";
	      if (!file.getOriginalFilename().isEmpty()) {
	    	  randomImageName =appService.createSalt()+appService.createSalt()+"."+FilenameUtils.getExtension(file.getOriginalFilename());
	         BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(
	                     new File(imageDir, randomImageName)));
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();
	      }else{
	         return new ResponseEntity<>("Invalid file.",HttpStatus.BAD_REQUEST);
	      }
	      Map<String,String> map = new HashMap<>();
	      map.put("image_name", randomImageName);
	      map.put("status", "File Uploaded Successfully.");
	      String jsonResponse = appService.convertMapToJson(map);
	      return new ResponseEntity<>(jsonResponse,HttpStatus.OK);
	   }		
}
