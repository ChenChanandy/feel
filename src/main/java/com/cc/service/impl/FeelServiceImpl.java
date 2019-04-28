package com.cc.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cc.mapper.FeelMapper;
import com.cc.mapper.ImgMapper;
import com.cc.pojo.Feel;
import com.cc.pojo.Img;
import com.cc.service.FeelService;
import com.cc.utils.FtpUtil;
import com.cc.utils.IDUtils;

@Service
public class FeelServiceImpl implements FeelService {
	@Resource
	private FeelMapper feelMapper;
	@Resource
	private ImgMapper imgMapper;
	
	@Value("${ftpclient.host}")
	private String host;
	@Value("${ftpclient.port}")
	private int port;
	@Value("${ftpclient.username}")
	private String username;
	@Value("${ftpclient.password}")
	private String password;
	@Value("${ftpclient.basePath}")
	private String basePath;
	@Value("${ftpclient.filePath}")
	private String filePath;
	
	@Override
	public Map<String, Object> upload(MultipartFile imgFile) throws IOException {
		String fileName = UUID.randomUUID()+imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf("."));
		boolean flag = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, imgFile.getInputStream());
		Map<String,Object> map = new HashMap<>();
		if(flag) {
			map.put("error", 0);
			map.put("url", "http://192.168.182.128/"+fileName);
		}else {
			map.put("error", 1);
			map.put("message", "图片上传失败！！！");
		}
		return map;
	}

	@Override
	public int insFeel(Feel feel, List<String> imgs) {
		//无Selective,所有列都操作
		//有Selective,只操作不为null的值
		long id = IDUtils.genItemId();
		feel.setId(id);
		int index = feelMapper.insert(feel);
		if(index>0) {
			for (String string : imgs) {
				Img img = new Img();
				img.setFid(id);
				img.setPath(string);
				index+=imgMapper.insertSelective(img);
			}
			if(index==imgs.size()+1) {
				return 1;
			}
		}
		return 0;
	}



}
