package life.majiang.community.mapper;

import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment (parent_id, content, type, gmt_create,gmt_modified, commentator) values (#{parentId}, #{content}, #{type}, #{gmtCreate}, #{gmtModified}, #{commentator})")
    void insert(Comment comment);
}
