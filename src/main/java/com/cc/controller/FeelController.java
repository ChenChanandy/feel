package com.cc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cc.pojo.Feel;
import com.cc.service.FeelService;

@Controller
public class FeelController {
	@Resource
	private FeelService feelServiceImpl;
	
	@RequestMapping("upload")
	@ResponseBody
	public Map<String,Object> upload(MultipartFile imgFile){	
		try {
			return feelServiceImpl.upload(imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("insert")
	public String insert(Feel feel, @RequestParam("imgs") List<String> imgs) {
		int index = feelServiceImpl.insFeel(feel, imgs);
		if(index>0) {
			return "/success.jsp";
		}else {
			return "/error.jsp";
		}		
	}
}
