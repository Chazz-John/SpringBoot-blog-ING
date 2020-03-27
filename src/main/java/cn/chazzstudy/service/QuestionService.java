package cn.chazzstudy.service;

import cn.chazzstudy.dto.PaginationDTO;
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
     * 功能描述:将数据库Question对象转换为QuestionDTO对象并封装成list
     *
     * @param
     * @param page
     * @param size
     * @return: java.util.List<cn.chazzstudy.dto.QuestionDTO>——QuestionDTO对象集
     * @Author: Chazz
     * @Date: 2020/3/26 20:26
     */
    public PaginationDTO list(Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList();
        PaginationDTO paginationDTO = new PaginationDTO();

        //查询数据库中所有数据总数
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        //对传入数据查询的page值进行容错处理
        if (page<1){
            page = 1;
        }
        if (page>=paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //计算当前页的第一条数据在数据库中的位置
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.list(offset,size);
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //BeanUtils.copyProperties进行对象之间属性的赋值
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
