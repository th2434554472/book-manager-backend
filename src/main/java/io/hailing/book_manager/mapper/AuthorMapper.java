package io.hailing.book_manager.mapper;

import io.hailing.book_manager.entity.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2023-10-19
 */
@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

}
