package com.server.service.impl;

import com.server.dao.CourseMapper;
import com.server.entity.Course;
import com.server.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCourses() {
        return courseMapper.selectAll();
    }
}
