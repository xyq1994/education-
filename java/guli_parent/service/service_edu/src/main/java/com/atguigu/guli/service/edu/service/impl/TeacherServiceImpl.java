package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.feign.OssFileService;
import com.atguigu.guli.service.edu.mapper.TeacherMapper;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-06-09
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private OssFileService ossFileService;

    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {
        // 1.根据sort字段排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        //2.根据条件查询
        if (teacherQueryVo == null){
            return baseMapper.selectPage(pageParam,null);
        }
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.likeRight("name",name);
        }

        if (level != null){
            queryWrapper.eq("level",level);
        }

        if (!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("joinDateBegin",joinDateBegin);
        }

        if (!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("joinDateEnd",joinDateEnd);
        }


        return baseMapper.selectPage(pageParam,queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameList(String key) {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        //构建条件查询
        queryWrapper.select("name");
        queryWrapper.likeRight("name",key);

        List<Map<String, Object>> mapList = baseMapper.selectMaps(queryWrapper);
        return mapList;

    }

    @Override
    public Boolean removeAvatarById(String id) {

        //根据id获取讲师头像地址
        Teacher teacher = baseMapper.selectById(id); //先查询到讲师的基本信息
        if (teacher != null){ //如果当前查询讲师存在
            //获取头像
            String avatar = teacher.getAvatar();
            if (!StringUtils.isEmpty(avatar)){
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }
        return false;
    }
}
