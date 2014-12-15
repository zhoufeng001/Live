package com.zf.live.service.impl;

import com.zf.live.client.service.VideoService;
import com.zf.live.dao.mapper.VideoMapperExt;
import org.springframework.beans.factory.annotation.Autowired;

public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapperExt videoMapper;
}