package cn.chazzstudy.service;

import cn.chazzstudy.dto.QuestionDTO;
import cn.chazzstudy.mapper.QuestionMapper;
import cn.chazzstudy.mapper.UserMapper;
import cn.chazzstudy.model.Question;
import cn.chazzstudy.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 将数据库Question对象转换为QuestionDTO对象并封装成list
 *
 * @Author Chazz
 * @date 2020/3/26 14:48
 * @Email 2741953539@qq.com
 **/
@Service
public class QuestionService {
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     *功能描述:将数据库Question对象转换为QuestionDTO对象并封装成list
     * @param
     * @return: java.util.List<cn.chazzstudy.dto.QuestionDTO>——QuestionDTO对象集
     * @Author: Chazz
     * @Date: 2020/3/26 20:26
     */
    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //BeanUtils.copyProperties进行对象之间属性的赋值
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
