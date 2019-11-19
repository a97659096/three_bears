package com.quotorcloud.quotor.academy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.quotorcloud.quotor.academy.api.dto.CourseDTO;
import com.quotorcloud.quotor.academy.api.entity.Course;
import com.quotorcloud.quotor.academy.api.entity.Teacher;
import com.quotorcloud.quotor.academy.api.vo.CourseVO;
import com.quotorcloud.quotor.academy.mapper.CourseMapper;
import com.quotorcloud.quotor.academy.mapper.TeacherMapper;
import com.quotorcloud.quotor.academy.service.CourseService;
import com.quotorcloud.quotor.common.core.constant.CommonConstants;
import com.quotorcloud.quotor.common.core.constant.FileConstants;
import com.quotorcloud.quotor.common.core.constant.enums.ExceptionEnum;
import com.quotorcloud.quotor.common.core.exception.MyException;
import com.quotorcloud.quotor.common.core.util.ComUtil;
import com.quotorcloud.quotor.common.core.util.DateTimeUtil;
import com.quotorcloud.quotor.common.core.util.FileUtil;
import com.quotorcloud.quotor.common.core.util.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程信息表 服务实现类
 * </p>
 * @author tianshihao
 * @since 2019-10-28
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    private static String RICH_TEXT = "richText/";

    private static String COURSE_IMG = "courseImg/";


    /**
     * 查询课程下拉框
     * @return
     */
    @Override
    public List<JSONObject> listCourseListBox(Integer status) {
        List<Course> courses = courseMapper.selectList(new QueryWrapper<Course>()
                .eq("c_del_state", CommonConstants.STATUS_NORMAL)
                .eq(!ComUtil.isEmpty(status), "c_status", status)
                .orderByDesc("c_gmt_create"));
        return courses.stream().map(course -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", course.getId());
            jsonObject.put("name", course.getName());
            return jsonObject;
        }).collect(Collectors.toList());
    }

    /**
     * 按id查询课程信息
     * @param id
     * @return
     * @throws ParseException
     */
    @Override
    public CourseVO selectCourseById(String id) throws ParseException {
        Course course = courseMapper.selectById(id);
        return this.getCourseVO(course);
    }

    /**
     * 修改课程信息
     * @param courseDTO
     * @return
     */
    @Override
    public Boolean updateCourse(CourseDTO courseDTO) {
        //判断唯一标识是否存在
        if(ComUtil.isEmpty(courseDTO.getId())){
            throw new MyException(ExceptionEnum.NOT_FIND_ID);
        }
        //查询course
        Course course = courseMapper.selectById(courseDTO.getId());
        BeanUtils.copyProperties(courseDTO, course);
        //如果为发布则更新发布时间
        if(!ComUtil.isEmpty(course.getStatus()) && course.getStatus().equals(CommonConstants.ISSUE)){
            course.setIssueTime(LocalDateTime.now());
        }
        //处理日期
        if(!ComUtil.isEmpty(courseDTO.getDateRange())){
            setCourseDODate(course, courseDTO.getDateRange());
        }
        //是否有删除图片
        FileUtil.removeFileAndField(course, courseDTO, "img", FileConstants.FileType.FILE_COURSE_IMG_DIR_);
        //是否有新增图片
        FileUtil.saveFileAndField(course, courseDTO, "img",
                FileConstants.FileType.FILE_COURSE_IMG_DIR_,null);
        courseMapper.updateById(course);
        return Boolean.TRUE;
    }

    /**
     * 删除课程信息 （逻辑删除）
     * @param id 唯一标识
     * @return
     */
    @Override
    public Boolean removeCourse(String id) {
        if(ComUtil.isEmpty(id)){
            throw new MyException(ExceptionEnum.NOT_FIND_ID);
        }
        try {
            Course course = new Course();
            course.setId(id);
            course.setDelState(1);
            courseMapper.updateById(course);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ExceptionEnum.DELETE_IMG_FAILED);
        }
        return Boolean.TRUE;
    }

    /**
     * 查询课程列表
     * @param courseDTO
     * @return
     */
    @Override
    public JSONObject listCourse(CourseDTO courseDTO) throws ParseException {
        //定义返回文件前缀
        Page<Course> page = PageUtil.getPage(courseDTO.getPageNo(), courseDTO.getPageSize());
        setCourseDTODate(courseDTO);
        IPage<Course> iPage = courseMapper.selectCoursePage(page, courseDTO);
        List<Course> records = iPage.getRecords();
        List<CourseVO> courseVOS = Lists.newLinkedList();
        for (Course course : records){
            CourseVO courseVO = getCourseVO(course);
            courseVOS.add(courseVO);
        }
        return PageUtil.getPagePackage("courses", courseVOS, page);
    }

    //DO转VO
    private CourseVO getCourseVO(Course course) throws ParseException {
        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(course, courseVO);
        //给Img加上地址
        if(!ComUtil.isEmpty(course.getStartDate()) && !ComUtil.isEmpty(course.getEndDate())){
            //先转成String 再转成 时间戳
            String startStamp = DateTimeUtil.dateToStamp(DateTimeUtil.localDateToString(course.getStartDate()));
            //先转成String 再转成 时间戳
            String endStamp = DateTimeUtil.dateToStamp(DateTimeUtil.localDateToString(course.getEndDate()));

            courseVO.setDateRange(Lists.newArrayList(Long.valueOf(startStamp), Long.valueOf(endStamp)));
        }
        return courseVO;
    }

    /**
     * 插入课程信息
     * @param courseDTO
     * @return
     */
    @Override
    public Boolean saveCourse(CourseDTO courseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course,
                "img", "dateRange");
        //时间戳,时间戳 时间戳转化成时间并set进去
        setCourseDODate(course, courseDTO.getDateRange());

        course.setSurplusPoll(ComUtil.isEmpty(courseDTO.getTotalPoll())?0:courseDTO.getTotalPoll());
        //如果状态为发布，则把当前时间加进去
        if(course.getStatus().equals(CommonConstants.ISSUE)){
            course.setIssueTime(LocalDateTime.now());
        }

        if(!ComUtil.isEmpty(courseDTO.getTeacherId())){
            Teacher teacher = teacherMapper.selectById(courseDTO.getTeacherId());
            if(ComUtil.isEmpty(teacher)){
                throw new MyException(ExceptionEnum.NOT_FIND_TEACHER);
            }
            course.setTeacherId(courseDTO.getTeacherId());
            course.setTeacherName(courseDTO.getTeacherName());
        }else {
            throw new MyException(ExceptionEnum.NOT_FIND_TEACHER);
        }
        //图片存入
        if(!ComUtil.isEmpty(courseDTO.getImg())){
            try {
                String imgUrl = FileUtil.saveFile(courseDTO.getImg(),
                        FileConstants.FileType.FILE_COURSE_IMG_DIR_, COURSE_IMG);
                course.setImg(imgUrl);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ExceptionEnum.SAVE_FILE_FAILED);
            }
        }
        try{
            //插入
            courseMapper.insert(course);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ExceptionEnum.SAVE_COURSE_FAILED);
        }
        return Boolean.TRUE;
    }

    private void setCourseDTODate(CourseDTO course){
        List<String> date = DateTimeUtil.getStringDate(course.getDateRange());
        if(!ComUtil.isEmpty(date)){
            course.setStart(date.get(0));
            course.setEnd(date.get(1));
        }
    }

    private void setCourseDODate(Course course, String dateRange){
        List<String> date = DateTimeUtil.getStringDate(dateRange);
        if(!ComUtil.isEmpty(date)){
            course.setStartDate(DateTimeUtil.stringToLocalDate(date.get(0)));
            course.setEndDate(DateTimeUtil.stringToLocalDate(date.get(1)));
        }
    }

    /**
     * 插入富文本图片
     * @param multipartFile
     * @return
     */
    @Override
    public String saveCourseImg(MultipartFile multipartFile) {
        try {
            return FileUtil.saveFile(multipartFile,
                    FileConstants.FileType.FILE_COURSE_IMG_DIR_, RICH_TEXT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionEnum.SAVE_FILE_FAILED);
        }
    }


}
