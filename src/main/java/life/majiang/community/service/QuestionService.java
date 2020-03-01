package life.majiang.community.service;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list(); //拿到所有的Question
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            //通过PublishController的question.setCreator(user.getId())取到了用户的id
            //findById通过creator=user.id去找id，找到返回整个User对象回来
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            //将遍历的每个question的数据copy到questionDTO里
            questionDTO.setUser(user); //组装最后一个数据
            questionDTOList.add(questionDTO); //放进List里，完成了数据的组装
        }
        return questionDTOList;
    }
}
