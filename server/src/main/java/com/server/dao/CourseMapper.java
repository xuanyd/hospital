package com.server.dao;

import com.server.entity.Course;

import java.util.List;

public interface CourseMapper {
    List<Course> selectAll();
}
